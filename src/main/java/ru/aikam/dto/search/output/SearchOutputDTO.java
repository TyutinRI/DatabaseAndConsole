package ru.aikam.dto.search.output;

import com.fasterxml.jackson.annotation.JsonTypeName;
import ru.aikam.dto.OutputDTO;
import ru.aikam.dto.search.output.crirerias.CriteriaAndResultListDTO;

import java.util.List;

//@JsonTypeName("searchOutputDTO")
public class SearchOutputDTO extends OutputDTO {
    private List<CriteriaAndResultListDTO> results;

    public SearchOutputDTO() {
    }

    public SearchOutputDTO(String name, List<CriteriaAndResultListDTO> results) {
        super(name);
        this.results = results;
    }

    public List<CriteriaAndResultListDTO> getResults() {
        return results;
    }

    public void setResults(List<CriteriaAndResultListDTO> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return super.toString() +
                "results=" + results +
                '}';
    }
}
