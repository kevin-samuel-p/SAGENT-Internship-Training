package in.edu.eec.easwari.Grocery.Delivery.App.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.edu.eec.easwari.Grocery.Delivery.App.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomer_CustomerId(Long customerId);
}