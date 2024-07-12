package id.sehatibe.service;

import id.sehatibe.dto.OrderResponseDto;
import id.sehatibe.model.Order;


public interface OrderService {
    public OrderResponseDto save(Order order);

    public OrderResponseDto getById(String id);

}
