package id.sehatibe.service;

import id.sehatibe.model.OrderItem;
import id.sehatibe.repository.OrderItemRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OderItemServiceImpl implements OrderItemService{
    private final OrderItemRepository orderItemRepository;
    @Override
    public List<OrderItem> getByOrderId(String orderId) {
        return orderItemRepository.findAllByOrder_Id(orderId);
    }

    public OrderItem save(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }
}
