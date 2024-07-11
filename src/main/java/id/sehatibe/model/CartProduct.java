package id.sehatibe.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "products_cart")
@Getter
@Setter
@NoArgsConstructor
public class CartProduct {
    @EmbeddedId
    private CartProductId id;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private Double quantity;

    private String note;

    public CartProduct(Cart cart, Product product, Double quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.id = new CartProductId(cart.getUser().getPhoneNumber(), product.getProductId());
    }
}


