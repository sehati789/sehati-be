package id.sehatibe.service;

import id.sehatibe.model.Cart;
import id.sehatibe.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;

    @Override
    public Cart getById(String idUser) {
        return cartRepository.findById(idUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));
    }
}
