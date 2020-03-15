package ru.aikam.dto.search.input.search.criterias;

import java.util.Arrays;

public  class CriteriaDTO {

    private Object[] criterias;
    public CriteriaDTO(){
    }

    public CriteriaDTO(Object[] criterias) {
        this.criterias = criterias;
    }

    public Object[] getCriterias() {
        return criterias;
    }

    public void setCriterias(Object[] criterias) {
        this.criterias = criterias;
    }

    @Override
    public String toString() {
        return "CriteriaDTO{" +
                "criterias=" + Arrays.toString(criterias) +
                '}';
    }
}
