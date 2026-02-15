package in.edu.eec.easwari.Grocery.Delivery.App.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.edu.eec.easwari.Grocery.Delivery.App.dto.request.AddToCartRequestDTO;
import in.edu.eec.easwari.Grocery.Delivery.App.dto.response.CartResponseDTO;
import in.edu.eec.easwari.Grocery.Delivery.App.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public CartResponseDTO addToCart(@RequestBody AddToCartRequestDTO dto) {
        return cartService.addToCart(dto);
    }

    @GetMapping("/{customerId}")
    public CartResponseDTO getCart(@PathVariable Long customerId) {
        return cartService.getCart(customerId);
    }

    @DeleteMapping("/{customerId}/product/{productId}")
    public void removeItem(@PathVariable Long customerId,
                           @PathVariable Long productId) {
        cartService.removeItem(customerId, productId);
    }
}