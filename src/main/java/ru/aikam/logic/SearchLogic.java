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

    /**
     * Метод для обратботки данных из входного файла и выполнения манипуляций с базой данных с
     * целью формирования DTO объекта для записи в выходной файл
     * @param criteriaOrErrorDTO - DTO объект полученный при маппинге входного файла
     * @return {@link OutputDTO} - выходной DTO. В случае выполнения без ошибок возвращает
     * {@link SearchOutputDTO} - объект для формирования выходного файла.
     * В случае ошибки - возвращает {@link ErrorOutputDTO} - объект для формирования выходного файла
     * с сообщением об ошибке.
     */
    public OutputDTO makeLogic(CriteriaOrErrorDTO criteriaOrErrorDTO) {

        //Проверка содержимого входного файла на наличие ошибок
        if (!criteriaOrErrorDTO.getMessage().equalsIgnoreCase("ok")) {
            return new ErrorOutputDTO("error", criteriaOrErrorDTO.getMessage());
        }

        //Создание списка DTO для возвращения информации о критерии, и результате поиска
        List<CriteriaAndResultListDTO> resultList = new ArrayList<>();
        Object[] criterias = criteriaOrErrorDTO.getCriteriaDTO().getCriterias();
        for (Object o : criterias) {

            //Приведение каждого критерия к LinkedHashMap<String, Object> для расшифровки
            //параметров критерия
            LinkedHashMap<String, Object> lm = (LinkedHashMap<String, Object>) o;
            for (Map.Entry<String, Object> entry : lm.entrySet()) {

                //провека на критерий "Фамилия"
                if (entry.getKey().equalsIgnoreCase("lastName")) {
                    List<Customer> resultCustomers = null;
                    if (entry.getValue() instanceof String) {

                        //Поиск людей с заданной фамилией. Фамилию можно вводить частичною
                        //Регистр игнорируется
                        resultCustomers =
                                customerService.findByLastName((String) entry.getValue());
                    } else {

                        //Сообщение об ошибке при нестроковом параметре критерия
                        return new ErrorOutputDTO("error", "Неверный параметр критерия!");
                    }

                    //Форсирование списка клиентов с заданной фамилией
                    List<CustomerFirstAndLastNameDTO> resultCustomerList =
                            new ArrayList<>();
                    for (Customer c : resultCustomers) {
                        resultCustomerList.add(
                                new CustomerFirstAndLastNameDTO(c.getFirstName(), c.getLastName())
                        );
                    }

                    //формирование ответа по поиску по Фамилии
                    LastNameSearchDTO lastNameSearchDTO = new LastNameSearchDTO((String) entry.getValue());
                    CriteriaAndResultListDTO criteriaAndResultListDTO =
                            new CriteriaAndResultListDTO(lastNameSearchDTO, resultCustomerList);

                    //Добавление ответа по поиску по Фамилии в список результатов поиска по критериям
                    resultList.add(criteriaAndResultListDTO);
                }
            }
        }

        return new SearchOutputDTO("search", resultList);
    }


}
