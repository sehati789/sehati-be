package id.sehatibe.controller;

import id.sehatibe.dto.OrderItemResponseDto;
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
    public List<OrderItemResponseDto> getByOrder(@RequestParam(value = "order_id") String orderId){
        List<OrderItem> orderItems= orderItemService.getByOrderId(orderId);
        return orderItems.stream().map(this::toOrderItemResponse).toList();
    }

    private OrderItemResponseDto toOrderItemResponse(OrderItem orderItem){
        return OrderItemResponseDto.builder()
                .orderId(orderItem.getOrder().getId())
                .notes(orderItem.getNotes())
                .amount(orderItem.getAmount())
                .productName(orderItem.getProductName())
                .productPrice(orderItem.getProductPrice())
                .profit(orderItem.getProfit())
                .totalPrice(orderItem.getTotalPrice())
                .build();
    }


}
