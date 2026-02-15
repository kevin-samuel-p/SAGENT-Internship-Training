package in.edu.eec.easwari.Grocery.Delivery.App.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import in.edu.eec.easwari.Grocery.Delivery.App.dto.response.PaymentResponseDTO;
import in.edu.eec.easwari.Grocery.Delivery.App.entity.Order;
import in.edu.eec.easwari.Grocery.Delivery.App.entity.Payment;
import in.edu.eec.easwari.Grocery.Delivery.App.enums.PaymentMethod;
import in.edu.eec.easwari.Grocery.Delivery.App.enums.PaymentStatus;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.OrderRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.PaymentRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public PaymentResponseDTO processPayment(Long orderId, PaymentMethod method) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotalAmount());
        payment.setPaymentMethod(method);
        payment.setPaymentDate(LocalDateTime.now());

        if (method == PaymentMethod.CASH_ON_DELIVERY) {
            payment.setPaymentStatus(PaymentStatus.PENDING);
        } else {
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
        }

        Payment saved = paymentRepository.save(payment);

        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setPaymentId(saved.getPaymentId());
        dto.setOrderId(orderId);
        dto.setAmount(saved.getAmount());
        dto.setPaymentMethod(saved.getPaymentMethod().name());
        dto.setPaymentStatus(saved.getPaymentStatus().name());
        dto.setPaymentDate(saved.getPaymentDate().toString());

        return dto;
    }
}