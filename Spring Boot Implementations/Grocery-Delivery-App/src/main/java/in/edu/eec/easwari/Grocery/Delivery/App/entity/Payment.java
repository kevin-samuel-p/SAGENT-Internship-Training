package in.edu.eec.easwari.Grocery.Delivery.App.entity;

import java.time.LocalDateTime;

import in.edu.eec.easwari.Grocery.Delivery.App.enums.PaymentMethod;
import in.edu.eec.easwari.Grocery.Delivery.App.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Double amount;
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}

