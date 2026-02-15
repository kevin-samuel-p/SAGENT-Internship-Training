package in.edu.eec.easwari.Grocery.Delivery.App.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.edu.eec.easwari.Grocery.Delivery.App.dto.request.PlaceOrderRequestDTO;
import in.edu.eec.easwari.Grocery.Delivery.App.dto.response.OrderResponseDTO;
import in.edu.eec.easwari.Grocery.Delivery.App.entity.Order;
import in.edu.eec.easwari.Grocery.Delivery.App.enums.OrderStatus;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.OrderRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public OrderController(OrderService orderService,
                           OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/place")
    public OrderResponseDTO placeOrder(@RequestBody PlaceOrderRequestDTO dto) {
        return orderService.placeOrder(dto);
    }

    @PutMapping("/{id}/cancel")
    public void cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
    }

    @PutMapping("/{id}/status")
    public void updateStatus(@PathVariable Long id,
                             @RequestParam OrderStatus status) {
        orderService.updateOrderStatus(id, status);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getCustomerOrders(@PathVariable Long customerId) {
        return orderRepository.findByCustomer_CustomerId(customerId);
    }
}