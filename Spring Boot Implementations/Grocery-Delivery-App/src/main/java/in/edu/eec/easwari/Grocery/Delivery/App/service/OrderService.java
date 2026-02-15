package in.edu.eec.easwari.Grocery.Delivery.App.service;

import in.edu.eec.easwari.Grocery.Delivery.App.dto.request.PlaceOrderRequestDTO;
import in.edu.eec.easwari.Grocery.Delivery.App.dto.response.OrderResponseDTO;
import in.edu.eec.easwari.Grocery.Delivery.App.enums.OrderStatus;

public interface OrderService {
    OrderResponseDTO placeOrder(PlaceOrderRequestDTO request);
    
    void updateOrderStatus(Long orderId, OrderStatus status);
    void cancelOrder(Long orderId);
}