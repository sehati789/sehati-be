package id.sehatibe.service;

import id.sehatibe.model.Cart;
import id.sehatibe.model.CartProduct;
import id.sehatibe.model.User;

import java.util.List;

public interface CartProductService {
    public List<CartProduct> getByCart(User user);
}
