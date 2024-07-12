package id.sehatibe.service;

import id.sehatibe.dto.OrderItemRequest;
import id.sehatibe.dto.OrderItemResponseDto;
import id.sehatibe.model.Order;
import id.sehatibe.model.OrderItem;
import id.sehatibe.model.Product;
import id.sehatibe.repository.OrderItemRepository;
import id.sehatibe.repository.OrderRepository;
import id.sehatibe.repository.ProductRepository;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service

public class OderItemServiceImpl implements OrderItemService{
    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;
    @Override
    @Transactional
    public List<OrderItem> getByOrderId(String orderId) {
            List<OrderItem> orderItems = orderItemRepository.findAllByOrder_Id(orderId);
            if(orderItems.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order doesn't exist");

            }
            return orderItems;


    }
    @Override
    @Transactional
    public  OrderItem getById(String id){
        return orderItemRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Order Item not found"));

    }

    @Override
    public void deleteById(String id) {
        OrderItem orderItem =orderItemRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Order Item not found"));
        Order order = orderItem.getOrder();
        order.setTotal(order.getTotal()-orderItem.getTotalPrice());
        orderItemRepository.deleteById(id);
    }

    @Override
    @Transactional
    public OrderItem create(OrderItemRequest request) {
        Product product = productRepository.findById(request.getIdProduct()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));
        int finalProductPrice = product.getRetailPrice()-product.getDiscountPrice();
        Double amount = request.getAmount();
        OrderItem orderItem= OrderItem.builder()
                .amount(amount)
                .productName(product.getProductName())

                .finalProductPrice(finalProductPrice)
                .notes(request.getNote())
                .profit(finalProductPrice * amount - product.getBasePrice()*amount )
                .totalPrice(finalProductPrice * amount)
                .baseProductPrice(product.getBasePrice())
                .build();
        orderItemRepository.save(orderItem);
        return orderItem;
    }

    @Override
    @Transactional
    public OrderItem save(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }

    @Override
    @Transactional
    public OrderItem edit(OrderItem orderReq) {
        OrderItem orderItem = orderItemRepository.findById(orderReq.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Order Item not found"));
        Order order = orderItem.getOrder();
        order.setTotal(order.getTotal()-orderItem.getTotalPrice());

        double newTotalPrice = orderReq.getAmount() * orderItem.getFinalProductPrice();
        orderItem.setAmount(orderReq.getAmount());
        orderItem.setTotalPrice(newTotalPrice);
        orderItem.setProfit(newTotalPrice - (orderReq.getAmount() * orderItem.getBaseProductPrice()));
        order.setTotal(order.getTotal()+orderItem.getTotalPrice());

        orderRepository.save(order);
        return orderItemRepository.save(orderItem);

    }

}
