package in.edu.eec.easwari.Grocery.Delivery.App.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.edu.eec.easwari.Grocery.Delivery.App.entity.Product;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getByCategory(@PathVariable Long categoryId) {
        return productRepository.findByCategory_CategoryId(categoryId);
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam String name) {
        return productRepository.findByProductNameContainingIgnoreCase(name);
    }
}