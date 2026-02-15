package in.edu.eec.easwari.Grocery.Delivery.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.edu.eec.easwari.Grocery.Delivery.App.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByCustomer_CustomerId(Long customerId);
    List<Notification> findByOrder_OrderId(Long orderId);
}