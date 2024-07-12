package id.sehatibe.controller;

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
        List<CartProduct> cartProducts = cartProductService.getByCart(currentUser);
        Order order = new Order();
        orderService.save(order);
        userService.addOrder(currentUser.getPhoneNumber(), order);
        Double total=0.0;
        for (CartProduct cartProduct: cartProducts){
            Product product = cartProduct.getProduct();
            int finalProductPrice = product.getRetailPrice()-product.getDiscountPrice();
            Double amount = cartProduct.getQuantity();
            //TODO benerin logicnya
            OrderItem orderItem = OrderItem.builder()
                    .amount(amount)
                    .productName(product.getProductName())
                    .finalProductPrice(finalProductPrice)
                    .notes(cartProduct.getNote())
                    .profit(finalProductPrice * amount - product.getBasePrice()*amount )
                    .totalPrice(finalProductPrice * amount)
                    .baseProductPrice(product.getBasePrice())
                    .build();
            order.addOrderItem(orderItem);
            total+= orderItem.getTotalPrice();
            orderItemService.save(orderItem);

        }
        order.setTotal(total);
        orderService.save(order);
        return OrderResponseDto.builder()
                .id(order.getId())
                .total(order.getTotal())
                .build();
    }

    @GetMapping("/{idOrder}")
    public OrderResponseDto getById(@PathVariable("idOrder") String id){
        return orderService.getById(id);
    }
    @DeleteMapping("/{idOrder}")
    public ResponseEntity<String> deleteById(@PathVariable("idOrder") String id){
        orderService.deleteById(id);
        return ResponseEntity.ok("Success deleting order with id "+id);
    }


}
