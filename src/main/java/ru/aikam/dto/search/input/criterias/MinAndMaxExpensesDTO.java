package ru.aikam.dto.search.input.criterias;

public class MinAndMaxExpensesDTO {
    private Integer minExpenses;
    private Integer maxExpenses;

    public MinAndMaxExpensesDTO(){}

    public MinAndMaxExpensesDTO(Integer minExpenses, Integer maxExpenses) {
        this.minExpenses = minExpenses;
        this.maxExpenses = maxExpenses;
    }

    public Integer getMinExpenses() {
        return minExpenses;
    }

    public void setMinExpenses(Integer minExpenses) {
        this.minExpenses = minExpenses;
    }

    public Integer getMaxExpenses() {
        return maxExpenses;
    }

    public void setMaxExpenses(Integer maxExpenses) {
        this.maxExpenses = maxExpenses;
    }

    @Override
    public String toString() {
        return "MinAndMaxExpensesDTO{" +
                "minExpenses=" + minExpenses +
                ", maxExpenses=" + maxExpenses +
                '}';
    }
}
