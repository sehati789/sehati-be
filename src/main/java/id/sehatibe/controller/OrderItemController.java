package id.sehatibe.controller;

import id.sehatibe.dto.OrderItemResponseDto;
import id.sehatibe.model.OrderItem;
import id.sehatibe.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orderItem")
public class OrderItemController {
    private final OrderItemService orderItemService;
    @PutMapping()
    public OrderItemResponseDto editOrderItem(@PathVariable("orderItemId") String id, @RequestBody OrderItem orderItem){
       return orderItemService.edit(orderItem);
    }
    @GetMapping
    public List<OrderItemResponseDto> getByOrder(@RequestParam(value = "order_id") String orderId){
        return orderItemService.getByOrderId(orderId);
    }





}
