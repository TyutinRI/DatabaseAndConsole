package ru.aikam.io.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.aikam.dto.OutputDTO;

import java.io.File;
import java.io.IOException;

public class SearchOutputHandler {
    public static void writeResult (String outputFileName, OutputDTO result){
        File file = new File(outputFileName);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(file, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
