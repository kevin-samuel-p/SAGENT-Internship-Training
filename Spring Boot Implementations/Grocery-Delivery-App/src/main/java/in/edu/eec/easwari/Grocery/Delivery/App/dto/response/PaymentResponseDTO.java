package in.edu.eec.easwari.Grocery.Delivery.App.dto.response;

import lombok.Data;

@Data
public class PaymentResponseDTO {
    private Long paymentId;
    private Long orderId;
    private String paymentMethod;
    private String paymentStatus;
    private Double amount;
    private String paymentDate;
}