package id.sehatibe.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class OrderResponseDto {
    private String id;
    private Double total;
    private Date deliveryDate;
    private int ShippingFee;
}
