package in.edu.eec.easwari.Grocery.Delivery.App.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.edu.eec.easwari.Grocery.Delivery.App.dto.response.PaymentResponseDTO;
import in.edu.eec.easwari.Grocery.Delivery.App.enums.PaymentMethod;
import in.edu.eec.easwari.Grocery.Delivery.App.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{orderId}")
    public PaymentResponseDTO processPayment(@PathVariable Long orderId,
                                             @RequestParam PaymentMethod method) {
        return paymentService.processPayment(orderId, method);
    }
}