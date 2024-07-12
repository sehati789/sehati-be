package id.sehatibe.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class EditOrderRequestDto {
    private Date deliveryDate;
    private int ShippingFee;

}
