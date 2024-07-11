package id.sehatibe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Entity
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue
    String productId;
    String productName;
    String category;
    int retailPrice;
    int basePrice;
    int stock;
    String description;
    int discountPrice = 0;
    String image;

    @ManyToMany(mappedBy = "products")
    private Set<Cart> carts = new HashSet<>();
}
