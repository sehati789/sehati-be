package id.sehatibe.controller;

import id.sehatibe.model.Product;
import id.sehatibe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping("/admin/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product result = service.save(product);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/admin/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        Product result = service.save(product);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id){
        Product result = service.getById(id);
        if (result == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id){
        service.deleteById(id);
        String message = "Produk dengan ID " + id + " telah berhasil dihapus";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/categories/list")
    public ResponseEntity<List<String>> getAllCategories(){
        return new ResponseEntity<>(service.getAllCategory(), HttpStatus.OK);
    }
}
