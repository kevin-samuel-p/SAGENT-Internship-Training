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
@Table(name = "stores")
@Data
public class Store {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    private String storeName;
    private String storeAddress;

    @OneToMany(mappedBy = "store")
    private List<Order> orders;
}
