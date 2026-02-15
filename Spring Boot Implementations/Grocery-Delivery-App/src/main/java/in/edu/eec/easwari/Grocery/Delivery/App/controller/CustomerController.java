package in.edu.eec.easwari.Grocery.Delivery.App.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.edu.eec.easwari.Grocery.Delivery.App.dto.request.NewCustomerRequestDTO;
import in.edu.eec.easwari.Grocery.Delivery.App.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public NewCustomerRequestDTO createCustomer(@RequestBody NewCustomerRequestDTO dto) {
        return customerService.createCustomer(dto);
    }

    @PutMapping("/{id}")
    public NewCustomerRequestDTO updateCustomer(@PathVariable Long id,
                                      @RequestBody NewCustomerRequestDTO dto) {
        return customerService.updateCustomer(id, dto);
    }

    @GetMapping("/{id}")
    public NewCustomerRequestDTO getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }
}