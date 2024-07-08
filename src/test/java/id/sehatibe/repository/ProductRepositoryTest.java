package id.sehatibe.repository;

import id.sehatibe.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testGetAllCategorySuccess() {
        Product product1 = new Product();
        product1.setProductName("Apel");
        product1.setDescription("merah");
        product1.setImage("https://th.bing.com/th/id/R.9d24e1528d7ee3c412d6711744221414?rik=5X%2fhugoJOfiwDA&pid=ImgRaw&r=0");
        product1.setRetailPrice(1500000);
        product1.setBasePrice(1000000);
        product1.setCategory("Buah");
        product1.setStock(99);

        Product savedProduct = productRepository.save(product1);

        List<String> result = productRepository.getAllCategories();
        assert result != null;
        assertEquals(result.getFirst(), savedProduct.getCategory());
    }
}