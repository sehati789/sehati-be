package id.sehatibe.controller;

import id.sehatibe.dto.OrderItemRequest;
import id.sehatibe.dto.OrderItemResponseDto;
import id.sehatibe.dto.OrderResponseDto;
import id.sehatibe.model.Order;
import id.sehatibe.model.OrderItem;
import id.sehatibe.model.Product;
import id.sehatibe.service.OrderItemService;
import id.sehatibe.service.OrderService;
import id.sehatibe.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orderItem")
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final OrderService orderService;
    @PutMapping()
    public OrderItemResponseDto editAmountAndPriceOrderItem(@RequestBody OrderItem orderItem){
       return toOrderItemResponse(orderItemService.edit(orderItem)) ;
    }
    @GetMapping
    public List<OrderItemResponseDto> getByOrder(@RequestParam(value = "order_id") String orderId){
        List<OrderItem> orderItems= orderItemService.getByOrderId(orderId);
        return orderItems.stream().map(this::toOrderItemResponse).toList();
    }

    @DeleteMapping("/{idOrderItem}")
    public ResponseEntity<String> deleteById(@PathVariable("idOrderItem") String id){
        orderItemService.deleteById(id);
        return ResponseEntity.ok("Success deleting order item with id "+id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public OrderItemResponseDto create(@RequestBody OrderItemRequest request){
        Order order = orderService.getById(request.getIdOrder());
        OrderItem orderItem= orderItemService.create(request);
        order.addOrderItem(orderItem);
        order.setTotal(order.getTotal()+orderItem.getTotalPrice());
        orderItemService.save(orderItem);
        orderService.save(order);
        return toOrderItemResponse(orderItem);
    }

    private OrderItemResponseDto toOrderItemResponse(OrderItem orderItem){
        return OrderItemResponseDto.builder()
                .id(orderItem.getId())
                .orderId(orderItem.getOrder().getId())
                .notes(orderItem.getNotes())
                .amount(orderItem.getAmount())
                .productName(orderItem.getProductName())
                .finalProductPrice(orderItem.getFinalProductPrice())
                .profit(orderItem.getProfit())
                .baseProductPrice(orderItem.getBaseProductPrice())
                .totalPrice(orderItem.getTotalPrice())
                .build();
    }



}
