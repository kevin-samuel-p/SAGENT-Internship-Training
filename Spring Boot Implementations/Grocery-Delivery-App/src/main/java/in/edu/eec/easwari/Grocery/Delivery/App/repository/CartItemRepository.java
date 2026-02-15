package in.edu.eec.easwari.Grocery.Delivery.App.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.edu.eec.easwari.Grocery.Delivery.App.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart_CartId(Long cartId);
    Optional<CartItem> findByCart_CartIdAndProduct_ProductId(Long cartId, Long productId);

    void deleteByCart_CartIdAndProduct_ProductId(Long cartId, Long productId);
}