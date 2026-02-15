package in.edu.eec.easwari.Grocery.Delivery.App.dto.response;

import lombok.Data;

@Data
public class NotificationResponseDTO {
    private Long notificationId;
    private Long orderId;
    private String message;
    private String sentDate;
}