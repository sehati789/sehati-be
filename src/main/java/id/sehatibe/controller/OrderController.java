package id.sehatibe.controller;

import id.sehatibe.dto.EditOrderRequestDto;
import id.sehatibe.dto.OrderItemRequest;
import id.sehatibe.dto.OrderResponseDto;
import id.sehatibe.model.*;
import id.sehatibe.service.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")

public class OrderController {
    @Autowired
    CartProductService cartProductService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    UserService userService;

    @PostMapping()
    public OrderResponseDto create(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        List<CartProduct> cartProducts = cartProductService.getByCartUser(currentUser);

        Order order = new Order();
        orderService.save(order);

        userService.addOrder(currentUser.getPhoneNumber(), order);

        Double total=0.0;
        for (CartProduct cartProduct: cartProducts){
            Product product = cartProduct.getProduct();
            OrderItemRequest orderItemRequest= OrderItemRequest.builder()
                    .idOrder(order.getId())
                    .idProduct(product.getProductId())
                    .amount(cartProduct.getQuantity())
                    .note(cartProduct.getNote())
                    .build();

            OrderItem orderItem = orderItemService.create(orderItemRequest );
            order.addOrderItem(orderItem);
            total+= orderItem.getTotalPrice();
            orderItemService.save(orderItem);

        }
        order.setTotal(total);
        orderService.save(order);

        cartProductService.deleteByCartId(currentUser.getPhoneNumber());

        return toOrderResponseDto(order);
    }

    @GetMapping("/{idOrder}")
    public OrderResponseDto getById(@PathVariable("idOrder") String id){
        return toOrderResponseDto(orderService.getById(id));
    }
    @DeleteMapping("/{idOrder}")
    public ResponseEntity<String> deleteById(@PathVariable("idOrder") String id){
        orderService.deleteById(id);
        return ResponseEntity.ok("Success deleting order with id "+id);
    }

    @PatchMapping("/{idOrder}")
    public OrderResponseDto editDeliveryDateAndShippingFeeById(@PathVariable("idOrder") String id, @RequestBody EditOrderRequestDto request){
        return toOrderResponseDto(orderService.editById(id, request));
    }

    private OrderResponseDto toOrderResponseDto(Order order){
        return OrderResponseDto.builder()
                .id(order.getId())
                .total(order.getTotal())
                .deliveryDate(order.getDeliveryDate())
                .ShippingFee(order.getShippingFee())
                .build();
    }
}
