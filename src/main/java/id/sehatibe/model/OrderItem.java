package id.sehatibe.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
//TODO: bikin api buat put order item skaligus ganti total di order
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Double amount;

    private String productName;
    private int finalProductPrice;

    private  int baseProductPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    private Double totalPrice;
    private Double profit;
    private String notes;


}
