package in.edu.eec.easwari.Grocery.Delivery.App.dto.request;

import lombok.Data;

@Data
public class AddToCartRequestDTO {
    private Long customerId;
    private Long productId;
    private Integer quantity;
}