package id.sehatibe.repository;

import id.sehatibe.model.Cart;
import id.sehatibe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{

    @Query(value = "SELECT p.* FROM products p " +
            "JOIN products_cart pc ON p.product_id = pc.product_id " +
            "JOIN cart c ON c.user_id = pc.cart_id " +
            "WHERE c.user_id = :userId", nativeQuery = true)
    List<Product> getProductInUserCart(@Param("userId") String userId);
}
