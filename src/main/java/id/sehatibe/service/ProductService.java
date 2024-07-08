package id.sehatibe.service;

import id.sehatibe.model.Product;

import java.util.List;

public interface ProductService {
    public Product save(Product product);
    public Product getById(String id);
    public void deleteById(String id);
    public List<Product> getAll();
    public List<String> getAllCategory();
}
