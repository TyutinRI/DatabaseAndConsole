package ru.aikam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aikam.entity.Customer;
import ru.aikam.repository.CustomerRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> findByLastName(String lastName) {
        return customerRepository.findByLastNameIgnoreCaseStartingWith(lastName);
    }

    @Override
    public List<Customer> findByGoodNameAndQuantity(String goodName, Integer quantity) {
        return customerRepository.findByByingGoodNotLessIntTimes(goodName, quantity);
    }

    @Override
    public List<Customer> findByMinMaxSpendedMoney(BigDecimal min, BigDecimal max) {
        return customerRepository.findBySpendedMoneyBetween(min, max);
    }
}
