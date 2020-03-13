package ru.aikam.service;

import ru.aikam.dto.search.output.crirerias.CustomerFirstAndLastNameDTO;
import ru.aikam.entity.Customer;

import java.util.List;

public interface CustomerService {
    void save(Customer customer);

    List<Customer> findByLastName(String lastName);
}
