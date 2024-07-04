package id.sehatibe.service;

import id.sehatibe.dto.LoginUserDto;
import id.sehatibe.dto.RegisterUserDto;
import id.sehatibe.enums.Role;
import id.sehatibe.model.User;
import id.sehatibe.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User exist = userRepository.findById(input.getPhoneNumber()).orElse(null);
        if(exist!=null){
            if (exist.getPassword()!=null){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone Number already used, please use another");
            }
        }
        User user = User.builder()
                .address(input.getAddress())
                .role(Role.valueOf(input.getRole()))
                .phoneNumber(input.getPhoneNumber())
                .name(input.getName())
                .password(passwordEncoder.encode(input.getPassword()))
                .build();

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getPhoneNumber(),
                        input.getPassword()
                )
        );

        return userRepository.findById(input.getPhoneNumber())
                .orElseThrow();
    }
}