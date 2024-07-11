package id.sehatibe.dto;

import id.sehatibe.model.Order;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemResponseDto {
    private Double amount;

    private String productName;
    private int productPrice;

    private String orderId;
    private Double totalPrice;
    private Double profit;
    private String notes;
}
