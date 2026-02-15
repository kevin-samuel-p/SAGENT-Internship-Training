package in.edu.eec.easwari.Grocery.Delivery.App.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "delivery_persons")
@Data
public class DeliveryPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryPersonId;

    private String name;
    private String mobileNumber;

    @OneToMany(mappedBy = "deliveryPerson")
    private List<Order> orders;
}

