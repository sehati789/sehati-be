package id.sehatibe.service;

import id.sehatibe.model.Cart;
import id.sehatibe.model.CartProduct;
import id.sehatibe.model.User;
import id.sehatibe.repository.CartProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartProductServiceImpl implements CartProductService{
    private CartProductRepository cartProductRepository;
    @Override
    public List<CartProduct> getByCart(User user) {
       return cartProductRepository.findByCart_User(user);
    }
}
