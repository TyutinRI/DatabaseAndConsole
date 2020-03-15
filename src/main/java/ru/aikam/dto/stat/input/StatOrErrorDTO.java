package ru.aikam.dto.stat.input;

public class StatOrErrorDTO {
    private StatInputDTO statInputDTO;
    private String message;

    public StatOrErrorDTO() {
    }

    public StatOrErrorDTO(StatInputDTO statInputDTO, String message) {
        this.statInputDTO = statInputDTO;
        this.message = message;
    }

    public StatInputDTO getStatInputDTO() {
        return statInputDTO;
    }

    public void setStatInputDTO(StatInputDTO statInputDTO) {
        this.statInputDTO = statInputDTO;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "StatOrErrorDTO{" +
                "statInputDTO=" + statInputDTO +
                ", message='" + message + '\'' +
                '}';
    }
}
