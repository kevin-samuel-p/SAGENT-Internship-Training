package in.edu.eec.easwari.Grocery.Delivery.App.dto.response;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long productId;
    private String productName;
    private Double price;
    private Integer stockQuantity;
    private String categoryName;
}