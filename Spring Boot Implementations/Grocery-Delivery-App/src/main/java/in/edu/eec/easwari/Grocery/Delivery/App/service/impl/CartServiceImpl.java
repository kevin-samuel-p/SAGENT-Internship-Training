package in.edu.eec.easwari.Grocery.Delivery.App.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import in.edu.eec.easwari.Grocery.Delivery.App.dto.request.AddToCartRequestDTO;
import in.edu.eec.easwari.Grocery.Delivery.App.dto.response.CartResponseDTO;
import in.edu.eec.easwari.Grocery.Delivery.App.entity.Cart;
import in.edu.eec.easwari.Grocery.Delivery.App.entity.CartItem;
import in.edu.eec.easwari.Grocery.Delivery.App.entity.Product;
import in.edu.eec.easwari.Grocery.Delivery.App.exception.ResourceNotFoundException;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.CartItemRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.CartRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.ProductRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.service.CartService;
import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartServiceImpl(CartRepository cartRepository,
                           ProductRepository productRepository,
                           CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartResponseDTO addToCart(AddToCartRequestDTO request) {

        Cart cart = cartRepository.findByCustomer_CustomerId(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        List<CartItem> cartItems = cartItemRepository.findByCart_CartId(cart.getCartId());

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(request.getQuantity());

        cartItems.add(
            cartItemRepository.save(item));

        double total = cartItems.stream()
                .mapToDouble(ci -> ci.getProduct().getPrice() * ci.getQuantity())
                .sum();

        double discount = total > 200 ? 25 : 0;

        cart.setTotalValue(total);
        cart.setDiscountApplied(discount);

        cartRepository.save(cart);

        // build response DTO
        CartResponseDTO dto = new CartResponseDTO();
        dto.setCartId(cart.getCartId());
        dto.setCustomerId(cart.getCustomer().getCustomerId());
        dto.setTotalValue(total);
        dto.setDiscountApplied(discount);
        dto.setFinalAmount(total - discount);
        dto.setItems(cartItems);

        return dto;
    }

    @Override
    public CartResponseDTO getCart(Long cartId) {
        Cart cart = cartRepository
                        .findById(cartId)
                                    .orElseThrow(
                            () -> new ResourceNotFoundException("Cart not found"));

        List<CartItem> cartItems = cartItemRepository.findByCart_CartId(cartId);

        Double total = cart.getTotalValue();
        Double discount = cart.getDiscountApplied();

        CartResponseDTO dto = new CartResponseDTO();
        dto.setCartId(cart.getCartId());
        dto.setCustomerId(cart.getCustomer().getCustomerId());
        dto.setTotalValue(total);
        dto.setDiscountApplied(discount);
        dto.setFinalAmount(total - discount);
        dto.setItems(cartItems);

        return dto;
    }

    @Override
    @Transactional
    public void removeItem(Long cartId, Long productId) {
        cartItemRepository.deleteByCart_CartIdAndProduct_ProductId(cartId, productId);
    }
}