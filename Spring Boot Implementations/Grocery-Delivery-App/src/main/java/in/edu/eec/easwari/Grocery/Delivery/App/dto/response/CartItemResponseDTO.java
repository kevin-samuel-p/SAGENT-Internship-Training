package in.edu.eec.easwari.Grocery.Delivery.App.dto.response;

import lombok.Data;

@Data
public class CartItemResponseDTO {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
    private Double totalPrice;
}