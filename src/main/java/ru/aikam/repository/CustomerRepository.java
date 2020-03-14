package ru.aikam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.aikam.dto.search.output.crirerias.CustomerFirstAndLastNameDTO;
import ru.aikam.entity.Customer;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //Метод для работы с критерием "Фамилия"
    List<Customer> findByLastNameIgnoreCaseStartingWith(String lastName);

    //Метод для работы с критерием "//Метод для работы с критерием "Фамилия" "
    @Query(
            value = "SELECT cus.* FROM carts c JOIN cart_good cg on c.id=cg.cart_id JOIN" +
                    " goods g on g.id = cg.good_id JOIN customers cus on cus.id = c.customer_id " +
                    "WHERE upper(g.name) = upper(:goodName) GROUP BY cus.id, cus.first_name, cus.last_name " +
                    "HAVING COUNT(cus.id) >= :quantity ;",
            nativeQuery = true
    )
    List<Customer> findByByingGoodNotLessIntTimes(@Param("goodName") String goodName,
                                                  @Param("quantity")Integer quantity);

}
