package id.sehatibe.model;

import id.sehatibe.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="users")
public class User {

    @Id
    private Long phoneNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}
