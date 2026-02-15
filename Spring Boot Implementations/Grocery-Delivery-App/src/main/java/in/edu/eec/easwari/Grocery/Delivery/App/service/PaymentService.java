package in.edu.eec.easwari.Grocery.Delivery.App.service;

import in.edu.eec.easwari.Grocery.Delivery.App.dto.response.PaymentResponseDTO;
import in.edu.eec.easwari.Grocery.Delivery.App.enums.PaymentMethod;

public interface PaymentService {
    PaymentResponseDTO processPayment(Long orderId, PaymentMethod method);
}