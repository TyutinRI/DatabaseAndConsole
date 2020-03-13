package ru.aikam.dto.search.input;

public class CriteriaOrErrorDTO {
    private String message;
    private CriteriaDTO criteriaDTO;

    public CriteriaOrErrorDTO(){}

    public CriteriaOrErrorDTO(String message, CriteriaDTO criteriaDTO) {
        this.message = message;
        this.criteriaDTO = criteriaDTO;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CriteriaDTO getCriteriaDTO() {
        return criteriaDTO;
    }

    public void setCriteriaDTO(CriteriaDTO criteriaDTO) {
        this.criteriaDTO = criteriaDTO;
    }
}
