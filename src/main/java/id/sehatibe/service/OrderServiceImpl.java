package id.sehatibe.service;

import id.sehatibe.model.Order;
import id.sehatibe.model.OrderItem;
import id.sehatibe.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    public Order save(Order order){
        return orderRepository.save(order);
    }
}
