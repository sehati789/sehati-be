package id.sehatibe.dto;

import id.sehatibe.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RegisterUserDto {
    private String phoneNumber;

    private String name;

    private String address;

    private String password;
    private String role;

}
