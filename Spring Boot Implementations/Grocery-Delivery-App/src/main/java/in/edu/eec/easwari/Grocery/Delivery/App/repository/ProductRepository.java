package in.edu.eec.easwari.Grocery.Delivery.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.edu.eec.easwari.Grocery.Delivery.App.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_CategoryId(Long categoryId);
    List<Product> findByProductNameContainingIgnoreCase(String name);
}