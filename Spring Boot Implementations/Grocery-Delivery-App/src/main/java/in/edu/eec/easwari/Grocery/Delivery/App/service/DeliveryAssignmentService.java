package in.edu.eec.easwari.Grocery.Delivery.App.service;

import in.edu.eec.easwari.Grocery.Delivery.App.entity.DeliveryPerson;

public interface DeliveryAssignmentService {
    DeliveryPerson assignDeliveryPerson(Long orderId);
}