package ru.aikam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.aikam.dto.stat.input.StatOrErrorDTO;
import ru.aikam.dto.stat.output.CustomerStatDTO;
import ru.aikam.dto.stat.output.PurchaseDTO;
import ru.aikam.dto.stat.output.StatOutputDTO;
import ru.aikam.io.input.StatInputHandler;
import ru.aikam.logic.SearchLogicService;
import ru.aikam.service.CartService;
import ru.aikam.service.CustomerService;
import ru.aikam.service.GoodService;

import java.math.BigDecimal;
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
    private SearchLogicService searchLogicService;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... args){


        List<PurchaseDTO> purchases = new ArrayList<>();
        purchases.add(new PurchaseDTO("lala", BigDecimal.valueOf(1000)));
        purchases.add(new PurchaseDTO("laddda", BigDecimal.valueOf(100)));
        purchases.add(new PurchaseDTO("laddsfsda", BigDecimal.valueOf(10)));

        CustomerStatDTO customerStatDTO = new CustomerStatDTO("name", purchases);

        System.out.println(customerStatDTO);

        CustomerStatDTO customerStatDTO2 = new CustomerStatDTO("name", purchases);

        List<CustomerStatDTO> list = Arrays.asList(customerStatDTO, customerStatDTO2);

        StatOutputDTO statOutputDTO = new StatOutputDTO("tip", 2, list);

        System.out.println(statOutputDTO);


//        StatOrErrorDTO s = StatInputHandler.inputHandle("Stat.JSON");
//        System.out.println(s);





//        CriteriaOrErrorDTO c = SearchInputHandler.inputHandle("Criteria1.JSON");
//        SearchOutputHandler.writeResult("result.JSON", searchLogicService.makeLogic(c));













//        Customer customer1 = new Customer("Сергей", "Ионов");
//        Customer customer2 = new Customer("Анатолий", "Жуков");
//        Customer customer3 = new Customer("Иоанн", "Сухоруков");
////
//        Good good1 = new Good("Хлеб", BigDecimal.valueOf(2.90));
//        Good good2 = new Good("Соль", BigDecimal.valueOf(3.70));
//        Good good3 = new Good("Сахар", BigDecimal.valueOf(10.40));
//        Good good4 = new Good("Масло", BigDecimal.valueOf(14.20));
////
//        Cart cart1 = new Cart(new Date(2020, 3, 10), customer1,
//                Arrays.asList(good1, good1, good1, good4));
//        Cart cart2 = new Cart(new Date(2020, 3, 9), customer1,
//                Arrays.asList(good2, good1, good4, good4));
//        Cart cart3 = new Cart(new Date(2020, 3, 10), customer2,
//                Arrays.asList(good2, good2, good1, good3));
//        Cart cart4 = new Cart(new Date(2020, 3, 13), customer3,
//                Arrays.asList(good4, good4, good4, good4));
//        Cart cart5 = new Cart(new Date(2020, 3, 12), customer3,
//                Arrays.asList(good1, good1, good1, good4));
//        Cart cart6 = new Cart(new Date(2020, 3, 8), customer3,
//                Arrays.asList(good1, good2, good1, good4));
//        Cart cart7 = new Cart(new Date(2020, 3, 8), customer1,
//                Arrays.asList(good2, good2, good3, good4));
//        Cart cart8 = new Cart(new Date(2020, 3, 8), customer1,
//                Arrays.asList(good2, good1, good3, good4));
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
//        cartService.save(cart7);
//        cartService.save(cart8);

    }
}
