package in.edu.eec.easwari.Grocery.Delivery.App.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import in.edu.eec.easwari.Grocery.Delivery.App.entity.DeliveryPerson;
import in.edu.eec.easwari.Grocery.Delivery.App.entity.Order;
import in.edu.eec.easwari.Grocery.Delivery.App.exception.ResourceNotFoundException;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.DeliveryPersonRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.OrderRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.service.DeliveryAssignmentService;

@Service
public class DeliveryAssignmentServiceImpl implements DeliveryAssignmentService {

    private final DeliveryPersonRepository deliveryPersonRepository;
    private final OrderRepository orderRepository;

    public DeliveryAssignmentServiceImpl(DeliveryPersonRepository deliveryPersonRepository,
                                         OrderRepository orderRepository) {
        this.deliveryPersonRepository = deliveryPersonRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public DeliveryPerson assignDeliveryPerson(Long orderId) {

        List<DeliveryPerson> persons = deliveryPersonRepository.findAll();

        if (persons.isEmpty()) {
            throw new ResourceNotFoundException("No delivery persons available");
        }

        // Simple random assignment
        DeliveryPerson assigned = persons.get(new Random().nextInt(persons.size()));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.setDeliveryPerson(assigned);
        orderRepository.save(order);

        return assigned;
    }
}