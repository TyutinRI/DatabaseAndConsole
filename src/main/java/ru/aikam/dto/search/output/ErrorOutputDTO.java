package ru.aikam.dto.search.output;

import com.fasterxml.jackson.annotation.JsonTypeName;
import ru.aikam.dto.OutputDTO;

@JsonTypeName("errorOutputDTO")
public class ErrorOutputDTO extends OutputDTO {
    private String message;

    public ErrorOutputDTO() {
    }

    public ErrorOutputDTO(String name, String message) {
        super(name);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return super.toString() +
                "message='" + message + '\'' +
                '}';
    }
}
