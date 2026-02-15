package in.edu.eec.easwari.Grocery.Delivery.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.edu.eec.easwari.Grocery.Delivery.App.entity.Order;
import in.edu.eec.easwari.Grocery.Delivery.App.enums.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer_CustomerId(Long customerId);
    List<Order> findByStatus(OrderStatus status);
}