package id.sehatibe.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemRequest {
    private String idOrder;
    private String idProduct;
    private Double amount;
    private String note;
}
