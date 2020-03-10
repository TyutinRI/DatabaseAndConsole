package ru.aikam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aikam.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
