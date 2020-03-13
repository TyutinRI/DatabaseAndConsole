package ru.aikam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.aikam.dto.search.output.crirerias.CustomerFirstAndLastNameDTO;
import ru.aikam.entity.Customer;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}
