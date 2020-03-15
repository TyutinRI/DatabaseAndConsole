package ru.aikam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.aikam.dto.OutputDTO;
import ru.aikam.dto.search.input.search.criterias.CriteriaOrErrorDTO;
import ru.aikam.dto.stat.input.StatOrErrorDTO;
import ru.aikam.dto.stat.output.PurchaseDTO;
import ru.aikam.dto.stat.output.StatOutputDTO;
import ru.aikam.entity.Cart;
import ru.aikam.entity.Customer;
import ru.aikam.entity.Good;
import ru.aikam.io.input.SearchInputHandler;
import ru.aikam.io.input.StatInputHandler;
import ru.aikam.io.output.OutputHandler;
import ru.aikam.logic.SearchLogicService;
import ru.aikam.logic.StatLogicService;
import ru.aikam.repository.CustomerRepository;
import ru.aikam.service.CartService;
import ru.aikam.service.CustomerService;
import ru.aikam.service.GoodService;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CartService cartService;
    @Autowired
    private GoodService goodService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SearchLogicService searchLogicService;
    @Autowired
    private StatLogicService statLogicService;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... args){



        StatOrErrorDTO s = StatInputHandler.inputHandle("Stat.JSON");
        OutputDTO st = statLogicService.makeLogic(s);
        System.out.println(st);
        OutputHandler.writeResult("result2.JSON", st);





//        CriteriaOrErrorDTO c = SearchInputHandler.inputHandle("Criteria1.JSON");
//        OutputHandler.writeResult("result.JSON", searchLogicService.makeLogic(c));













//        Customer customer1 = new Customer("Сергей", "Ионов");
//        Customer customer2 = new Customer("Анатолий", "Жуков");
//        Customer customer3 = new Customer("Иоанн", "Сухоруков");
////
//        Good good1 = new Good("Хлеб", BigDecimal.valueOf(2.90));
//        Good good2 = new Good("Соль", BigDecimal.valueOf(3.70));
//        Good good3 = new Good("Сахар", BigDecimal.valueOf(10.40));
//        Good good4 = new Good("Масло", BigDecimal.valueOf(14.20));
////
//        Cart cart1 = new Cart(Date.valueOf("2020-03-15"), customer1,
//                Arrays.asList(good1, good4));
//        Cart cart2 = new Cart(Date.valueOf("2020-03-11"), customer1,
//                Arrays.asList(good2, good4));
//        Cart cart3 = new Cart(Date.valueOf("2020-03-13"), customer2,
//                Arrays.asList(good2, good1, good3));
//        Cart cart4 = new Cart(Date.valueOf("2020-03-12"), customer3,
//                Arrays.asList(good4, good4, good4, good4));
//        Cart cart5 = new Cart(Date.valueOf("2020-03-16"), customer3,
//                Arrays.asList(good1, good1, good1));
//        Cart cart6 = new Cart(Date.valueOf("2020-03-05"), customer3,
//                Arrays.asList(good1, good2));
//
////
////
//        goodService.save(good1);
//        goodService.save(good2);
//        goodService.save(good3);
//        goodService.save(good4);
//
//        customerService.save(customer1);
//        customerService.save(customer2);
//        customerService.save(customer3);
////
//        cartService.save(cart1);
//        cartService.save(cart2);
//        cartService.save(cart3);
//        cartService.save(cart4);
//        cartService.save(cart5);
//        cartService.save(cart6);
;

    }
}
