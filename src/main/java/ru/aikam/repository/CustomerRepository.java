package ru.aikam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.aikam.entity.Customer;

import java.math.BigDecimal;
import java.sql.Date;
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




    //Метод для сбора статистики
    @Query(value = "SELECT g.name, SUM(g.price) " +
            "FROM carts c " +
            "JOIN cart_good cg ON c.id=cg.cart_id " +
            "JOIN goods g ON cg.good_id=g.id " +
            "JOIN customers cus ON cus.id = c.customer_id " +
            "WHERE cus.id = :customerId " +
            "AND c.date BETWEEN :startDate AND :endDate " +
            "GROUP BY cus.id, g.name;",
    nativeQuery = true)
    List<Object[]> findStat(@Param("startDate")Date startDate, @Param("endDate")Date endDate,
    @Param("customerId")Long customerId);
}
