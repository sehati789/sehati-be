package id.sehatibe.repository;

import id.sehatibe.model.CartProduct;
import id.sehatibe.model.CartProductId;
import id.sehatibe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, CartProductId> {
    List<CartProduct> findByCart_User(User user);
}
