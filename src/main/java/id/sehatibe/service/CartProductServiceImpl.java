package id.sehatibe.service;

import id.sehatibe.model.Cart;
import id.sehatibe.model.CartProduct;
import id.sehatibe.model.User;
import id.sehatibe.repository.CartProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CartProductServiceImpl implements CartProductService{
    @Autowired
    CartProductRepository cartProductRepository;
    @Override
    @Transactional
    public List<CartProduct> getByCartUser(User user) {
       return cartProductRepository.findByCart_User(user);
    }

    @Override
    @Transactional
    public void deleteByCartId(String id){
        cartProductRepository.deleteCartProductByCart_Id(id);
    }
}
