package ru.aikam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.aikam.dto.search.output.crirerias.CustomerFirstAndLastNameDTO;
import ru.aikam.entity.Customer;

import java.math.BigDecimal;
import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //Метод для работы с критерием "Фамилия"
    List<Customer> findByLastNameIgnoreCaseStartingWith(String lastName);

    //Метод для работы с критерием "Название товара и число раз"
    @Query(
            value = "SELECT cus.* FROM carts c " +
                    "JOIN cart_good cg on c.id=cg.cart_id " +
                    "JOIN goods g on g.id = cg.good_id " +
                    "JOIN customers cus on cus.id = c.customer_id " +
                    "WHERE upper(g.name) = upper(:goodName) " +
                    "GROUP BY cus.id, cus.first_name, cus.last_name " +
                    "HAVING COUNT(cus.id) >= :quantity ;",
            nativeQuery = true
    )
    List<Customer> findByByingGoodNotLessIntTimes(@Param("goodName") String goodName,
                                                  @Param("quantity")Integer quantity);



    //Метод для работы с критерием "Минимальная и максимальная стоимость всех покупок"
    @Query(value = "SELECT cus.* FROM customers cus " +
            "JOIN carts c on cus.id = c.customer_id " +
            "JOIN cart_good cg on c.id=cg.cart_id " +
            "JOIN goods g on g.id = cg.good_id " +
            "GROUP BY cus.id, cus.first_name, cus.last_name " +
            "HAVING SUM(g.price) BETWEEN  :minExpenses AND  :maxExpenses ;",
    nativeQuery = true)
    List<Customer> findBySpendedMoneyBetween(@Param("minExpenses")BigDecimal minExpenses,
                                             @Param("maxExpenses")BigDecimal maxExpenses);



    //Метод для работы с критерием "Число пассивных покупателей"
    @Query(value = "SELECT cus.* FROM customers cus " +
            "JOIN carts c on cus.id = c.customer_id " +
            "JOIN cart_good cg on c.id=cg.cart_id JOIN " +
            "goods g on g.id = cg.good_id " +
            "GROUP BY cus.id, cus.first_name, cus.last_name " +
            "ORDER BY COUNT(g.id) " +
            "LIMIT :badCustomers ;",
    nativeQuery = true)
    List<Customer> findBadCuctomers(@Param("badCustomers") Integer badCustomers);
}
