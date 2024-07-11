package id.sehatibe.dto;

import id.sehatibe.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDetailDto {
    private String phoneNumber;

    private String name;

    private String address;

    private String role;
}
