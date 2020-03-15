package ru.aikam.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aikam.dto.OutputDTO;
import ru.aikam.dto.ErrorOutputDTO;
import ru.aikam.dto.stat.input.StatOrErrorDTO;
import ru.aikam.dto.stat.output.CustomerStatDTO;
import ru.aikam.service.CustomerService;
import ru.aikam.dto.stat.output.StatOutputDTO;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Component
public class StatLogicService {
    private final CustomerService customerService;

    @Autowired
    public StatLogicService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Метод для обратботки данных из входного файла с промежутком дат и выполнения манипуляций с базой данных с
     * целью формирования DTO объекта для записи в выходной файл
     * @param statOrErrorDTO - DTO объект полученный при маппинге входного файла с промежутком дат
     * @return {@link OutputDTO} - выходной DTO. В случае выполнения без ошибок возвращает
     * {@link StatOutputDTO} - объект для формирования выходного файла с результатами статистик за период,
     * между датами из входного файла.
     * В случае ошибки - возвращает {@link ErrorOutputDTO} - объект для формирования выходного файла
     * с сообщением об ошибке.
     */
    public OutputDTO makeLogic(StatOrErrorDTO statOrErrorDTO){

        //Проверка содержимого входного файла на наличие ошибок
        if (statOrErrorDTO.getMessage() != null) {
            return new ErrorOutputDTO("error", statOrErrorDTO.getMessage());
        }

        List<CustomerStatDTO> customerStatDTOList = customerService.findStat(
                statOrErrorDTO.getStatInputDTO().getStartDate(),
                Date.valueOf(statOrErrorDTO.getStatInputDTO().getEndDate().toLocalDate().plusDays(1))
        );

        Integer workDays = countOfWorkDays(statOrErrorDTO.getStatInputDTO().getStartDate(),
                statOrErrorDTO.getStatInputDTO().getEndDate());

        return new StatOutputDTO("stat", workDays, customerStatDTOList);
    }

    /**
     * Метод для определения количества рабочих дней между двумя датами(включая эти даты)
     */
    private int countOfWorkDays(Date first, Date second){

        LocalDate ld1 = first.toLocalDate();
        LocalDate ld2 = second.toLocalDate().plusDays(1);

        int workDaysCount = 0;
        for(LocalDate ld = ld1; ld.compareTo(ld2) <= 0 ;ld = ld.plusDays(1)){
            if(!ld.getDayOfWeek().equals(DayOfWeek.SATURDAY) &&
                    !ld.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                workDaysCount++;
            }
        }
        return workDaysCount;
    }
}
