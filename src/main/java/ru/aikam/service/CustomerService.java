package ru.aikam.service;

import ru.aikam.dto.search.output.crirerias.CustomerFirstAndLastNameDTO;
import ru.aikam.entity.Customer;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerService {
    void save(Customer customer);

    List<Customer> findByLastName(String lastName);

    List<Customer> findByGoodNameAndQuantity(String goodName, Integer quantity);

    List<Customer> findByMinMaxSpendedMoney(BigDecimal min, BigDecimal max);

    List<Customer> findBadCustomers(Integer badCustomers);
}
