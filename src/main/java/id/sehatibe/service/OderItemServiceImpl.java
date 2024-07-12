package id.sehatibe.service;

import id.sehatibe.dto.OrderItemResponseDto;
import id.sehatibe.model.Order;
import id.sehatibe.model.OrderItem;
import id.sehatibe.repository.OrderItemRepository;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Override
    public List<OrderItemResponseDto> getByOrderId(String orderId) {
            List<OrderItem> orderItems = orderItemRepository.findAllByOrder_Id(orderId);
            return orderItems.stream().map(this::toOrderItemResponse).toList();

    }

    public  OrderItemResponseDto getById(String id){
        OrderItem orderItem=  orderItemRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Order Item not found"));
        return toOrderItemResponse(orderItem);
    }
    public OrderItemResponseDto save(OrderItem orderItem){
        orderItemRepository.save(orderItem);
        return toOrderItemResponse(orderItem);
    }

    public OrderItemResponseDto edit(OrderItem orderReq) {
        OrderItem orderItem = orderItemRepository.findById(orderReq.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Order Item not found"));

        double newTotalPrice = orderReq.getAmount() * orderItem.getFinalProductPrice();
        orderItem.setAmount(orderReq.getAmount());
        orderItem.setTotalPrice(newTotalPrice);
        orderItem.setProfit(newTotalPrice - (orderReq.getAmount() * orderItem.getBaseProductPrice()));
        orderItemRepository.save(orderItem);
        return toOrderItemResponse(orderItem);

    }
    private OrderItemResponseDto toOrderItemResponse(OrderItem orderItem){
        return OrderItemResponseDto.builder()
                .orderId(orderItem.getOrder().getId())
                .notes(orderItem.getNotes())
                .amount(orderItem.getAmount())
                .productName(orderItem.getProductName())
                .finalProductPrice(orderItem.getFinalProductPrice())
                .profit(orderItem.getProfit())
                .baseProductPrice(orderItem.getBaseProductPrice())
                .totalPrice(orderItem.getTotalPrice())
                .build();
    }
}
