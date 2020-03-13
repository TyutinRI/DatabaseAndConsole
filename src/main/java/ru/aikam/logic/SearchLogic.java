package ru.aikam.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aikam.dto.search.input.CriteriaOrErrorDTO;
import ru.aikam.dto.search.input.criterias.LastNameSearchDTO;
import ru.aikam.dto.search.output.ErrorOutputDTO;
import ru.aikam.dto.search.output.OutputDTO;
import ru.aikam.dto.search.output.SearchOutputDTO;
import ru.aikam.dto.search.output.crirerias.CriteriaAndResultListDTO;
import ru.aikam.dto.search.output.crirerias.CustomerFirstAndLastNameDTO;
import ru.aikam.entity.Customer;
import ru.aikam.service.CartService;
import ru.aikam.service.CustomerService;
import ru.aikam.service.GoodService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Component
public class SearchLogic {
    private final CustomerService customerService;
    private final GoodService goodService;
    private final CartService cartService;

    @Autowired
    public SearchLogic(CustomerService customerService, GoodService goodService, CartService cartService) {
        this.customerService = customerService;
        this.goodService = goodService;
        this.cartService = cartService;
    }

        public OutputDTO makeLogic(CriteriaOrErrorDTO criteriaOrErrorDTO){
            if(!criteriaOrErrorDTO.getMessage().equalsIgnoreCase("ok")){
                return new ErrorOutputDTO("error", criteriaOrErrorDTO.getMessage());
            }
            List<CriteriaAndResultListDTO> resultList = new ArrayList<>();
            Object[] criterias = criteriaOrErrorDTO.getCriteriaDTO().getCriterias();
            for(Object o:criterias){
                LinkedHashMap<String, Object> lm = (LinkedHashMap<String, Object>) o;
                for (Map.Entry<String, Object> entry: lm.entrySet()){
                    if(entry.getKey().equalsIgnoreCase("lastName")){
                        List<Customer> resultCustomers =
                                customerService.findByLastName((String) entry.getValue());


                        System.out.println((String) entry.getValue());


                        List<CustomerFirstAndLastNameDTO> resultCustomerList =
                                new ArrayList<>();
                        for(Customer c:resultCustomers){
                            resultCustomerList.add(
                                    new CustomerFirstAndLastNameDTO(c.getFirstName(), c.getLastName())
                            );
                        }


                        System.out.println(resultCustomerList);


                        LastNameSearchDTO lastNameSearchDTO = new LastNameSearchDTO((String) entry.getValue());
                        CriteriaAndResultListDTO criteriaAndResultListDTO =
                                new CriteriaAndResultListDTO(lastNameSearchDTO, resultCustomerList);

                        System.out.println(criteriaAndResultListDTO);
                        resultList.add(criteriaAndResultListDTO);
                    }
                }
            }

            return new SearchOutputDTO("search", resultList);
        }









}
