package id.sehatibe.service;

import id.sehatibe.model.Product;
import id.sehatibe.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(String id) {
        Optional<Product> result = productRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public String deleteById(String id) {
        productRepository.deleteById(id);
        return id;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
