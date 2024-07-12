package id.sehatibe.service;

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
    public OrderResponseDto save(Order order){
        return orderRepository.save(order);
    }

    @Override
    public OrderResponseDto getById(String id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Order Item not found"));
        return toOrderResponseDto(order);
    }
    @Override
    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

    private OrderResponseDto toOrderResponseDto(Order order){
        return OrderResponseDto.builder()
                .id(order.getId())
                .total(order.getTotal())
                .build();
    }
}
