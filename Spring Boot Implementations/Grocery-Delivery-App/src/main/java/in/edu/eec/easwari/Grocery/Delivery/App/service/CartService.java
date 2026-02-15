package in.edu.eec.easwari.Grocery.Delivery.App.service;

import in.edu.eec.easwari.Grocery.Delivery.App.dto.request.AddToCartRequestDTO;
import in.edu.eec.easwari.Grocery.Delivery.App.dto.response.CartResponseDTO;

public interface CartService {
    CartResponseDTO addToCart(AddToCartRequestDTO request);
    CartResponseDTO getCart(Long cartId);
    
    void removeItem(Long cartId, Long productId);
}