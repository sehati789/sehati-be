package id.sehatibe.service;

import id.sehatibe.dto.EditOrderRequestDto;
import id.sehatibe.dto.OrderResponseDto;
import id.sehatibe.model.Order;


public interface OrderService {
    public Order save(Order order);

    public Order getById(String id);
    public void deleteById(String id);

    public Order editById(String id, EditOrderRequestDto request);

}
