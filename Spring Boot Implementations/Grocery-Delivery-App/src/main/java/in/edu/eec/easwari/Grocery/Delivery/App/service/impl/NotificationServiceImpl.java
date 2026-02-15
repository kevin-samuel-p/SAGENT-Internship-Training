package in.edu.eec.easwari.Grocery.Delivery.App.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import in.edu.eec.easwari.Grocery.Delivery.App.entity.Customer;
import in.edu.eec.easwari.Grocery.Delivery.App.entity.Notification;
import in.edu.eec.easwari.Grocery.Delivery.App.entity.Order;
import in.edu.eec.easwari.Grocery.Delivery.App.exception.ResourceNotFoundException;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.CustomerRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.NotificationRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.OrderRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   CustomerRepository customerRepository,
                                   OrderRepository orderRepository) {
        this.notificationRepository = notificationRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void sendOrderNotification(Long customerId, Long orderId, String message) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        Notification notification = new Notification();
        notification.setCustomer(customer);
        notification.setOrder(order);
        notification.setMessage(message);
        notification.setSentDate(LocalDateTime.now());

        notificationRepository.save(notification);
    }
}