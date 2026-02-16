package in.edu.eec.easwari.Grocery.Delivery.App.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import in.edu.eec.easwari.Grocery.Delivery.App.dto.request.PlaceOrderRequestDTO;
import in.edu.eec.easwari.Grocery.Delivery.App.dto.response.OrderResponseDTO;
import in.edu.eec.easwari.Grocery.Delivery.App.entity.Cart;
import in.edu.eec.easwari.Grocery.Delivery.App.entity.CartItem;
import in.edu.eec.easwari.Grocery.Delivery.App.entity.Customer;
import in.edu.eec.easwari.Grocery.Delivery.App.entity.Order;
import in.edu.eec.easwari.Grocery.Delivery.App.entity.Product;
import in.edu.eec.easwari.Grocery.Delivery.App.entity.Store;
import in.edu.eec.easwari.Grocery.Delivery.App.enums.OrderStatus;
import in.edu.eec.easwari.Grocery.Delivery.App.enums.PaymentMethod;
import in.edu.eec.easwari.Grocery.Delivery.App.exception.InsufficientStockException;
import in.edu.eec.easwari.Grocery.Delivery.App.exception.InvalidOperationException;
import in.edu.eec.easwari.Grocery.Delivery.App.exception.ResourceNotFoundException;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.CartItemRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.CartRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.CustomerRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.OrderRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.StoreRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.service.DeliveryAssignmentService;
import in.edu.eec.easwari.Grocery.Delivery.App.service.NotificationService;
import in.edu.eec.easwari.Grocery.Delivery.App.service.OrderService;
import in.edu.eec.easwari.Grocery.Delivery.App.service.PaymentService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;
    private final DeliveryAssignmentService deliveryAssignmentService;
    private final PaymentService paymentService;
    private final NotificationService notificationService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            CartRepository cartRepository,
                            CartItemRepository cartItemRepository,
                            StoreRepository storeRepository,
                            CustomerRepository customerRepository,
                            DeliveryAssignmentService deliveryAssignmentService,
                            PaymentService paymentService,
                            NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.storeRepository = storeRepository;
        this.customerRepository = customerRepository;
        this.deliveryAssignmentService = deliveryAssignmentService;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }

    @Override
    @Transactional
    public OrderResponseDTO placeOrder(PlaceOrderRequestDTO request) {

        Cart cart = cartRepository.findByCustomer_CustomerId(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));

        List<CartItem> cartItems = cartItemRepository.findByCart_CartId(cart.getCartId());

        if (cartItems.isEmpty()) {
            throw new InvalidOperationException("Cart is empty");
        }

        // Validate stock before deduction
        for (CartItem item : cartItems) {
            Product product = item.getProduct();

            if (product.getStockQuantity() < item.getQuantity()) 
                throw new InsufficientStockException(
                        "Insufficient stock for product: " + product.getProductName());
        }

        // Deduct stock
        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            product.setStockQuantity(
                    product.getStockQuantity() 
                    - item.getQuantity());
        }

        // Create order
        Order order = new Order();
        order.setCart(cart);
        order.setCustomer(customer);
        order.setStore(store);
        order.setOrderDate(LocalDateTime.now());
        order.setDeliveryAddress(request.getDeliveryAddress());
        order.setStatus(OrderStatus.ORDER_CONFIRMED);
        order.setIsCancelled(false);

        double finalAmount = cart.getTotalValue() - cart.getDiscountApplied();
        order.setTotalAmount(finalAmount);

        Order savedOrder = orderRepository.save(order);

        // Assign delivery person
        deliveryAssignmentService.assignDeliveryPerson(savedOrder.getOrderId());

        // Process payment
        PaymentMethod method = PaymentMethod.valueOf(request.getPaymentMethod());
        paymentService.processPayment(savedOrder.getOrderId(), method);

        // Clear cart
        cartItems.clear();
        cart.setTotalValue(0.0);
        cart.setDiscountApplied(0.0);
        cartRepository.save(cart);

        // Send notification
        notificationService.sendOrderNotification(
                customer.getCustomerId(),
                savedOrder.getOrderId(),
                "Order Confirmed"
        );

        // Build response
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(savedOrder.getOrderId());
        dto.setCustomerId(customer.getCustomerId());
        dto.setStatus(savedOrder.getStatus().name());
        dto.setTotalAmount(savedOrder.getTotalAmount());
        dto.setDeliveryAddress(savedOrder.getDeliveryAddress());
        dto.setOrderDate(savedOrder.getOrderDate().toString());

        return dto;
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.setStatus(status);
        orderRepository.save(order);

        notificationService.sendOrderNotification(
                order.getCustomer().getCustomerId(),
                orderId,
                "Order status updated to: " + status.name()
        );
    }

    @Override
    public void cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new InvalidOperationException("Delivered orders cannot be cancelled");
        }

        order.setIsCancelled(true);
        order.setStatus(OrderStatus.CANCELLED);

        orderRepository.save(order);

        notificationService.sendOrderNotification(
                order.getCustomer().getCustomerId(),
                orderId,
                "Order Cancelled"
        );
    }
}