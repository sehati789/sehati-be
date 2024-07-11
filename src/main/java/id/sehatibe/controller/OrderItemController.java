package id.sehatibe.controller;

import id.sehatibe.model.OrderItem;
import id.sehatibe.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orderItem")
public class OrderItemController {
    private final OrderItemService orderItemService;
    @GetMapping
    public List<OrderItem> getByOrder(@RequestParam(value = "order_id") String orderId){
        return orderItemService.getByOrderId(orderId);
    }


}
