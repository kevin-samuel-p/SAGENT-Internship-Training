package in.edu.eec.easwari.Grocery.Delivery.App.dto.response;

import java.util.List;

import in.edu.eec.easwari.Grocery.Delivery.App.entity.CartItem;
import lombok.Data;

@Data
public class CartResponseDTO {
    private Long cartId;
    private Long customerId;
    private Double totalValue;
    private Double discountApplied;
    private Double finalAmount;

    private List<CartItem> items;
}