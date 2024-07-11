package id.sehatibe.service;

import id.sehatibe.model.Cart;
import id.sehatibe.model.CartProduct;
import id.sehatibe.model.User;
import id.sehatibe.repository.CartProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CartProductServiceImpl implements CartProductService{
    @Autowired
    CartProductRepository cartProductRepository;
    @Override
    public List<CartProduct> getByCart(User user) {
       return cartProductRepository.findByCart_User(user);
    }
}
