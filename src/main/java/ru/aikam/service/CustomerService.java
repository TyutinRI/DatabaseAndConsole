package ru.aikam.service;

import ru.aikam.dto.stat.output.CustomerStatDTO;
import ru.aikam.entity.Customer;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface CustomerService {
    void save(Customer customer);

    List<Customer> findByLastName(String lastName);

    List<Customer> findByGoodNameAndQuantity(String goodName, Integer quantity);

    List<Customer> findByMinMaxSpendedMoney(BigDecimal min, BigDecimal max);

    List<Customer> findBadCustomers(Integer badCustomers);

    List<CustomerStatDTO> findStat(Date startDate, Date endDate);
}
