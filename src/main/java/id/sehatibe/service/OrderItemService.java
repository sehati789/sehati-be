package id.sehatibe.service;

import id.sehatibe.dto.OrderItemResponseDto;
import id.sehatibe.model.OrderItem;

import java.util.List;

public interface OrderItemService{
    public List<OrderItemResponseDto> getByOrderId(String orderId);
    public OrderItemResponseDto save(OrderItem orderItem);

    public OrderItemResponseDto edit(OrderItem orderItem);
    public OrderItemResponseDto getById(String id);
}
