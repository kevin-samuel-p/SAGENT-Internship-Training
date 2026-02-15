package in.edu.eec.easwari.Grocery.Delivery.App.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class CartResponseDTO {
    private Long cartId;
    private Long customerId;
    private List<CartItemResponseDTO> items;
    private Double totalValue;
    private Double discountApplied;
    private Double finalAmount;
}