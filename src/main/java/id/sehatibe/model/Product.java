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
    Long retailPrice;
    Long basePrice;
    Long stock;
    String description;
    Long discountPrice = 0L;
    String image;
}
