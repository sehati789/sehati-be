package id.sehatibe.service;

import id.sehatibe.model.OrderItem;

import java.util.List;

public interface OrderItemService{
    public List<OrderItem> getByOrderId(String orderId);
    public OrderItem save(OrderItem orderItem);
}
