package in.edu.eec.easwari.Grocery.Delivery.App.dto.request;

import lombok.Data;

@Data
public class PlaceOrderRequestDTO {
    private Long customerId;
    private Long storeId;
    private String deliveryAddress;
    private String paymentMethod;
}