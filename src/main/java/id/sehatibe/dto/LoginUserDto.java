package id.sehatibe.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
public class LoginUserDto {
    private String phoneNumber;
    private String password;
}
