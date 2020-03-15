package ru.aikam.io.input;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.aikam.dto.stat.input.StatInputDTO;
import ru.aikam.dto.stat.input.StatOrErrorDTO;

import java.io.File;
import java.io.IOException;

public class StatInputHandler {
    public static StatOrErrorDTO inputHandle(String inputFileName){
        File file = new File(inputFileName);
        ObjectMapper mapper = new ObjectMapper();
        StatInputDTO statInputDTO = null;
        String massage = null;

        try {
            statInputDTO = mapper.readValue(file, StatInputDTO.class);
        } catch (JsonProcessingException e) {
            massage = "Ошибка при расшифровке файла с входными данными! Неверное содержание.";
        }catch (IOException e) {
            massage = "Ошибка! Не существует такого файла с входными данными!";
        }

        System.out.println(statInputDTO);

        if(statInputDTO != null) {
            if (statInputDTO.getStartDate() == null || statInputDTO.getEndDate() == null) {
                massage = "Во входном файле отсутствует одна или обе даты!";
            }
        }


        return new StatOrErrorDTO(statInputDTO, massage);
    }
}
