package ru.aikam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aikam.entity.Good;

public interface GoodRepository extends JpaRepository<Good, Long> {
}
