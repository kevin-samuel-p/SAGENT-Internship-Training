package in.edu.eec.easwari.Grocery.Delivery.App.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.edu.eec.easwari.Grocery.Delivery.App.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrder_OrderId(Long orderId);
}