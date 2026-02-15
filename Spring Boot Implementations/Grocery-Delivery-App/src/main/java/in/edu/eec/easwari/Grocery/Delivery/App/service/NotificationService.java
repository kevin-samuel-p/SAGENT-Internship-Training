package in.edu.eec.easwari.Grocery.Delivery.App.service;

public interface NotificationService {
    void sendOrderNotification(Long customerId, Long orderId, String message);
}