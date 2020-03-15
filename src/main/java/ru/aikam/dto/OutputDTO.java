package ru.aikam.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import ru.aikam.dto.search.output.SearchOutputDTO;
import ru.aikam.dto.stat.output.StatOutputDTO;

@JsonSubTypes({
        @JsonSubTypes.Type(value = StatOutputDTO.class, name = "statOutputDTO"),
        @JsonSubTypes.Type(value = ErrorOutputDTO.class, name = "errorOutputDTO"),
        @JsonSubTypes.Type(value = SearchOutputDTO.class, name = "searchOutputDTO")
})
public abstract class OutputDTO {
    private String type;

    public OutputDTO(){}

    public OutputDTO(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "OutputDTO{" +
                "name='" + type + '\'' +
                '}';
    }
}
