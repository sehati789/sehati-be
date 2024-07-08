package id.sehatibe.repository;

import id.sehatibe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{
    @Query(value = "SELECT DISTINCT category from product", nativeQuery = true)
    public List<String> getAllCategories();
}
