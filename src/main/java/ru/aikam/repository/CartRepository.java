package ru.aikam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aikam.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
