package id.sehatibe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    OrderItem orderItem;
}
