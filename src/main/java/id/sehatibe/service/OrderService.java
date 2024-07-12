package id.sehatibe.service;

import id.sehatibe.dto.EditOrderRequestDto;
import id.sehatibe.dto.OrderResponseDto;
import id.sehatibe.model.Order;


public interface OrderService {
    public OrderResponseDto save(Order order);

    public OrderResponseDto getById(String id);
    public void deleteById(String id);

    public OrderResponseDto editById(String id, EditOrderRequestDto request);

}
