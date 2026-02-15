package in.edu.eec.easwari.Grocery.Delivery.App.service;

import in.edu.eec.easwari.Grocery.Delivery.App.dto.request.NewCustomerRequestDTO;

public interface CustomerService {
    NewCustomerRequestDTO createCustomer(NewCustomerRequestDTO dto);
    NewCustomerRequestDTO updateCustomer(Long id, NewCustomerRequestDTO dto);
    NewCustomerRequestDTO getCustomer(Long id);
}