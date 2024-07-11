package id.sehatibe.controller;

import id.sehatibe.model.*;
import id.sehatibe.service.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Order create(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        List<CartProduct> cartProducts = cartProductService.getByCart(currentUser);
        Order order = new Order();
        orderService.save(order);
        userService.addOrder(currentUser.getPhoneNumber(), order);
        for (CartProduct cartProduct: cartProducts){
            Product product = cartProduct.getProduct();
            int finalProductPrice = product.getRetailPrice()-product.getDiscountPrice();
            Double amount = cartProduct.getQuantity();
            //TODO benerin logicnya
            OrderItem orderItem = OrderItem.builder()
                    .amount(amount)
                    .productName(product.getProductName())
                    .productPrice(finalProductPrice)
                    .notes(cartProduct.getNote())
                    .profit(finalProductPrice * amount - product.getBasePrice()*amount )
                    .totalPrice(finalProductPrice * amount)
                    .build();
            order.addOrderItem(orderItem);

            orderItemService.save(orderItem);

        }
        orderService.save(order);
        return order;
    }
}
