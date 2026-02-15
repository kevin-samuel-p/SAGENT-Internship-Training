package in.edu.eec.easwari.Grocery.Delivery.App.service.impl;

import org.springframework.stereotype.Service;

import in.edu.eec.easwari.Grocery.Delivery.App.dto.request.NewCustomerRequestDTO;
import in.edu.eec.easwari.Grocery.Delivery.App.entity.Cart;
import in.edu.eec.easwari.Grocery.Delivery.App.entity.Customer;
import in.edu.eec.easwari.Grocery.Delivery.App.exception.ResourceNotFoundException;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.CartRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.repository.CustomerRepository;
import in.edu.eec.easwari.Grocery.Delivery.App.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CartRepository cartRepository,
                               CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public NewCustomerRequestDTO createCustomer(NewCustomerRequestDTO dto) {

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setAddress(dto.getAddress());
        customer.setMobile(dto.getMobile());

        Customer saved = customerRepository.save(customer);

        Cart cart = new Cart();
        cart.setCustomer(saved);
        cart.setTotalValue(0.0);
        cart.setDiscountApplied(0.0);

        cartRepository.save(cart);

        dto.setCustomerId(saved.getCustomerId());
        return dto;
    }

    @Override
    public NewCustomerRequestDTO updateCustomer(Long id, NewCustomerRequestDTO dto) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customer.setName(dto.getName());
        customer.setAddress(dto.getAddress());
        customer.setMobile(dto.getMobile());

        customerRepository.save(customer);

        dto.setCustomerId(id);
        return dto;
    }

    @Override
    public NewCustomerRequestDTO getCustomer(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        NewCustomerRequestDTO dto = new NewCustomerRequestDTO();
        dto.setCustomerId(customer.getCustomerId());
        dto.setName(customer.getName());
        dto.setAddress(customer.getAddress());
        dto.setMobile(customer.getMobile());

        return dto;
    }
}