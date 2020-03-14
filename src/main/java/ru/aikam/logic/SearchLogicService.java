package ru.aikam.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aikam.dto.search.input.CriteriaOrErrorDTO;
import ru.aikam.dto.search.input.criterias.GoodAndNumberTimesDTO;
import ru.aikam.dto.search.input.criterias.LastNameSearchDTO;
import ru.aikam.dto.search.input.criterias.MinAndMaxExpensesDTO;
import ru.aikam.dto.search.output.ErrorOutputDTO;
import ru.aikam.dto.search.output.OutputDTO;
import ru.aikam.dto.search.output.SearchOutputDTO;
import ru.aikam.dto.search.output.crirerias.CriteriaAndResultListDTO;
import ru.aikam.dto.search.output.crirerias.CustomerFirstAndLastNameDTO;
import ru.aikam.entity.Customer;
import ru.aikam.service.CartService;
import ru.aikam.service.CustomerService;
import ru.aikam.service.GoodService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Component
public class SearchLogicService {
    private final CustomerService customerService;
    private final GoodService goodService;
    private final CartService cartService;

    @Autowired
    public SearchLogicService(CustomerService customerService, GoodService goodService, CartService cartService) {
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

            //Объявление результирующего списка покупателей
            List<Customer> resultCustomers = null;
            Object criteria = null;

            //провека на критерий "Фамилия"
            if(lm.keySet().contains("lastName")){
                //проверка явяется ли критерий "Фамилия" строкой
                if(lm.get("lastName") instanceof String){
                    resultCustomers =
                            customerService.findByLastName((String) lm.get("lastName"));
                } else {

                    //Сообщение об ошибке при нестроковом параметре критерия
                    return new ErrorOutputDTO("error", "Неверный параметр критерия!");
                }
                //формирование критерия для ответа по поиску по Фамилии
                criteria = new LastNameSearchDTO((String) lm.get("lastName"));
            }


            //провека на критерий "Название товара и число раз"
            if(lm.keySet().contains("productName") && lm.keySet().contains("minTimes")){
                //проверка яаляется ли название продукта строкой, а минимальное число покупок -
                // натуральным числом
                if(!(lm.get("productName") instanceof String) ||
                !(lm.get("minTimes") instanceof Integer)
                || ((Integer) lm.get("minTimes") <= 0)){

                    //Сообщение об ошибке при неверном параметре критерия
                    return new ErrorOutputDTO("error", "Неверный параметр критерия!");
                } else {
                    resultCustomers =
                            customerService.findByGoodNameAndQuantity(
                                    (String) lm.get("productName"),
                                    (Integer) lm.get("minTimes")
                            );
                }
                //формирование критерия для ответа по поиску по количеству покупок
                criteria = new GoodAndNumberTimesDTO((String) lm.get("productName"),
                        (Integer) lm.get("minTimes"));
            }


            //провека на критерий "Минимальная и максимальная стоимость всех покупок"
            if(lm.keySet().contains("minExpenses") && lm.keySet().contains("maxExpenses")) {
                //проверка типа максимального и минимального значения потрачнных денег
                if (!(lm.get("minExpenses") instanceof Double)
                        || !(lm.get("maxExpenses") instanceof Double)){

                    //Сообщение об ошибке при неверном параметре критерия
                    return new ErrorOutputDTO("error", "Неверный параметр критерия!5656");
                } else if(((Double) lm.get("minExpenses")).
                        compareTo((Double) lm.get("maxExpenses")) >= 0){

                    return new ErrorOutputDTO("error", "Минимальное значение " +
                            "потраченных денег больше максимального!");
                } else{
                    resultCustomers = customerService.findByMinMaxSpendedMoney(
                            BigDecimal.valueOf((Double) lm.get("minExpenses")),
                            BigDecimal.valueOf((Double) lm.get("maxExpenses"))
                    );
                }
                //формирование критерия для ответа по поиску по количеству покупок
                criteria = new MinAndMaxExpensesDTO(BigDecimal.valueOf((Double) lm.get("minExpenses")),
                        BigDecimal.valueOf((Double) lm.get("maxExpenses")));
            }

            if (resultCustomers == null){
                //Сообщение об ошибке при несовпадении критерия ни одному из обрабатываемых
                return new ErrorOutputDTO("error", "Неверный критерий!");
            }

            //Формирование списка клиентов с заданной фамилией
            List<CustomerFirstAndLastNameDTO> resultCustomerList =
                    new ArrayList<>();
            for (Customer c : resultCustomers) {
                resultCustomerList.add(
                        new CustomerFirstAndLastNameDTO(c.getFirstName(), c.getLastName())
                );
            }


            CriteriaAndResultListDTO criteriaAndResultListDTO =
                    new CriteriaAndResultListDTO(criteria, resultCustomerList);

            //Добавление ответа по поиску по Фамилии в список результатов поиска по критериям
            resultList.add(criteriaAndResultListDTO);

        }

        return new SearchOutputDTO("search", resultList);
    }


}
