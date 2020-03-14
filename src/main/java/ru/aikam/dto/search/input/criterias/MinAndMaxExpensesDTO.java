package ru.aikam.dto.search.input.criterias;

import java.math.BigDecimal;

public class MinAndMaxExpensesDTO {
    private BigDecimal minExpenses;
    private BigDecimal maxExpenses;

    public MinAndMaxExpensesDTO(){}

    public MinAndMaxExpensesDTO(BigDecimal minExpenses, BigDecimal maxExpenses) {
        this.minExpenses = minExpenses;
        this.maxExpenses = maxExpenses;
    }

    public BigDecimal getMinExpenses() {
        return minExpenses;
    }

    public void setMinExpenses(BigDecimal minExpenses) {
        this.minExpenses = minExpenses;
    }

    public BigDecimal getMaxExpenses() {
        return maxExpenses;
    }

    public void setMaxExpenses(BigDecimal maxExpenses) {
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
