package id.sehatibe.controller;

import id.sehatibe.dto.UserDetailDto;
import id.sehatibe.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/users")
@RestController
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<UserDetailDto> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        UserDetailDto userDetailDto= UserDetailDto.builder()
                                    .role(currentUser.getRole().name())
                                    .address(currentUser.getAddress())
                                    .phoneNumber(currentUser.getPhoneNumber())
                                    .name(currentUser.getName())
                                    .build();
        return ResponseEntity.ok(userDetailDto);
    }


}