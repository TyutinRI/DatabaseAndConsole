package ru.aikam.dto.search.output.crirerias;

import java.util.Arrays;
import java.util.List;

public class CriteriaAndResultListDTO {
    private Object criteria;
    private List<CustomerFirstAndLastNameDTO> results;

    public CriteriaAndResultListDTO() {
    }

    public CriteriaAndResultListDTO(Object criteria, List<CustomerFirstAndLastNameDTO> results) {
        this.criteria = criteria;
        this.results = results;
    }

    public Object getCriteria() {
        return criteria;
    }

    public void setCriteria(Object criteria) {
        this.criteria = criteria;
    }

    public List<CustomerFirstAndLastNameDTO> getResults() {
        return results;
    }

    public void setResults(List<CustomerFirstAndLastNameDTO> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "CriteriaAndResultListDTO{" +
                "criteria=" + criteria +
                ", results=" + results +
                '}';
    }
}
