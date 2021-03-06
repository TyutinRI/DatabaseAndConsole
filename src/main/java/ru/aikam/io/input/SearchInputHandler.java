package ru.aikam.io.input;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.aikam.dto.search.input.search.criterias.CriteriaDTO;
import ru.aikam.dto.search.input.search.criterias.CriteriaOrErrorDTO;

import java.io.File;
import java.io.IOException;


public class SearchInputHandler {

    /**
     * Метод для обработки входного файла с описание критериев.
     */
    public static CriteriaOrErrorDTO inputHandle(String inputFileName) {
        File file = new File(inputFileName);
        ObjectMapper mapper = new ObjectMapper();
        CriteriaDTO criteriaDTO = null;
        String massage = null;
        try {
            criteriaDTO = mapper.readValue(file, CriteriaDTO.class);
            massage = "ok";
        } catch (JsonProcessingException e) {
            massage = "Ошибка при расшифровке файла с входными данными! Неверное содержание.";
        }  catch (IOException e) {
            massage = "Ошибка! Не существует такого файла с входными данными!";
        }
        if(massage.equals("ok") && criteriaDTO.getCriterias() == null){
            massage = "Файл с входными данными не содержит критериев поиска!";
        }
        return new CriteriaOrErrorDTO(massage, criteriaDTO);
    }
}
