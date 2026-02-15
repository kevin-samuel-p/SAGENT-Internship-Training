package in.edu.eec.easwari.Grocery.Delivery.App.dto.request;

import lombok.Data;

@Data
public class NewCustomerRequestDTO {
    private Long customerId;
    private String name;
    private String address;
    private String mobile;
}