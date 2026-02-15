package in.edu.eec.easwari.Grocery.Delivery.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.edu.eec.easwari.Grocery.Delivery.App.entity.DeliveryPerson;

@Repository
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Long> {}