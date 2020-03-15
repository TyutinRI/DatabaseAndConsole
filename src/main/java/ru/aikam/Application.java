package ru.aikam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.aikam.dto.ErrorOutputDTO;
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
import ru.aikam.service.CustomerService;


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

        if(args.length<3){
            System.err.println("Неверное число аргументов!!!");
            OutputHandler.writeResult("Error.JSON",
                    new ErrorOutputDTO("ERROR!!!", "Неверное число аргументов!!!"));
        }else {
            if(!args[0].equalsIgnoreCase("search") && !args[0].equalsIgnoreCase("stat")){
                System.err.println("Неверно введен первый аргумент! Нужно ввести либо \"search\", " +
                        "либо \"stat\"");
                OutputHandler.writeResult("Error.JSON",
                        new ErrorOutputDTO("ERROR!!!", "Неверно введен первый аргумент! " +
                                "Нужно ввести либо \"search\", либо \"stat\""));
            }else {
                if(args[0].equalsIgnoreCase("stat")) {
                    StatOrErrorDTO s = StatInputHandler.inputHandle(args[1]);
                    OutputHandler.writeResult(args[2], statLogicService.makeLogic(s));
                } else {
                    CriteriaOrErrorDTO c = SearchInputHandler.inputHandle(args[1]);
                    OutputHandler.writeResult(args[2], searchLogicService.makeLogic(c));
                }
            }
        }
    }
}
