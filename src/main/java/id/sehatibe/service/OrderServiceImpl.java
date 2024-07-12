package id.sehatibe.service;

import id.sehatibe.dto.EditOrderRequestDto;
import id.sehatibe.dto.OrderResponseDto;
import id.sehatibe.model.Order;
import id.sehatibe.model.OrderItem;
import id.sehatibe.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    public Order save(Order order){
        return orderRepository.save(order);

    }

    @Override
    public Order getById(String id) {
        return orderRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Order Item not found"));
    }
    @Override
    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order editById(String id, EditOrderRequestDto request) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Order Item not found"));
        order.setShippingFee(request.getShippingFee());
        order.setDeliveryDate(request.getDeliveryDate());
        return orderRepository.save(order);
    }


}
