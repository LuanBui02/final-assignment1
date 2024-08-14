package com.finalassignment.assignment.service.impl;

import com.finalassignment.assignment.dto.CustomerDto;
import com.finalassignment.assignment.exception.DuplicateNameException;
import com.finalassignment.assignment.exception.ItemDuplicatedException;
import com.finalassignment.assignment.exception.NotFoundInListException;
import com.finalassignment.assignment.exception.ValidateTypeException;
import com.finalassignment.assignment.mapper.CustomerMapper;
import com.finalassignment.assignment.model.Customer;
import com.finalassignment.assignment.repository.CustomerRepo;
import com.finalassignment.assignment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;


    @Override
    public void addUser(CustomerDto customerDto) {
        List<Customer> customerList = customerRepo.findAll();
        for (Customer customer : customerList) {
            if (Objects.equals(customer.getUsername(), customerDto.getUsername())) {
                throw new DuplicateNameException();
            }
            if (customerDto.getType() > 1 || customerDto.getType() < 0) {
                throw new ValidateTypeException();
            }
        }
        customerRepo.save(CustomerMapper.INSTANCE.toModel(customerDto));
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> customerList = customerRepo.findAll();
        return CustomerMapper.INSTANCE.toListDto(customerList);
    }

    @Override
    public CustomerDto getCustomer(String username, String password) {
        List<Customer> customerList = customerRepo.findAll();
        CustomerDto customerDto = new CustomerDto();
        for (Customer customer : customerList) {
            if (Objects.equals(customer.getUsername(), username)
                    && Objects.equals(customer.getPassword(), password)) {
                List<Customer> customers = customerRepo.findCustomerByUsernameAndPassword(username, password);
                customerDto = CustomerMapper.INSTANCE.toDto(customer);
                return customerDto;
            }
        }
        throw new NotFoundInListException();

    }
}

//@Override
//public void addUser(CustomerDto customerDto) {
//    List<Customer> customerList = customerRepo.findAll();
//    for (Customer customer : customerList) {
//        if (Objects.equals(customer.getUsername(), customerDto.getUsername())) {
//            throw new DuplicateNameException();
//        }
//    }
//    customerRepo.save(CustomerMapper.INSTANCE.toModel(customerDto));
//}
//
//@Override
//public List<CustomerDto> getAllCustomer() {
//    List<Customer> customerList = customerRepo.findAll();
//    return CustomerMapper.INSTANCE.toListDto(customerList);
//}
//
//@Override
//public CustomerDto getCustomer(String username, String password) {
//    List<Customer> customerList = customerRepo.findAll();
//    CustomerDto customerDto = new CustomerDto();
//    for (Customer customer : customerList) {
//        if (customer.getUsername().equals(username)
//                && customer.getPassword().equals(password)) {
//            List<Customer> customers = customerRepo.findCustomerByUsernameAndPassword(username, password);
//            customerDto= CustomerMapper.INSTANCE.toDto(customer);
//            return customerDto;
//        }
//    }
//    throw new NotFoundInListException();
//}
