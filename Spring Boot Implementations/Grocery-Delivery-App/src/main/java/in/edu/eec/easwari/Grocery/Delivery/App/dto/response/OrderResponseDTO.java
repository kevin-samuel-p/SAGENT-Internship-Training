package in.edu.eec.easwari.Grocery.Delivery.App.dto.response;

import lombok.Data;

@Data
public class OrderResponseDTO {
    private Long orderId;
    private Long customerId;
    private String status;
    private Double totalAmount;
    private String deliveryAddress;
    private String orderDate;
}
