package id.sehatibe.service;

import id.sehatibe.dto.OrderItemRequest;
import id.sehatibe.dto.OrderItemResponseDto;
import id.sehatibe.model.OrderItem;

import java.util.List;

public interface OrderItemService{
    public List<OrderItem> getByOrderId(String orderId);
    public OrderItem save(OrderItem orderItem);

    public OrderItem edit(OrderItem orderItem);
    public OrderItem getById(String id);
    public void  deleteById(String id);

    public  OrderItem create(OrderItemRequest request);


}
