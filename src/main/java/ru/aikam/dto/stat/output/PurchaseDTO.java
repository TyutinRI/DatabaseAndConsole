package ru.aikam.dto.stat.output;

import java.math.BigDecimal;

public class PurchaseDTO {
    private String name;
    private BigDecimal expenses;

    public PurchaseDTO() {
    }

    public PurchaseDTO(String name, BigDecimal expenses) {
        this.name = name;
        this.expenses = expenses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "PurchaseDTO{" +
                "name='" + name + '\'' +
                ", expenses=" + expenses +
                '}';
    }
}
