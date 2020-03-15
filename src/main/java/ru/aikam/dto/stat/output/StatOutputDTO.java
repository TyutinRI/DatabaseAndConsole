package ru.aikam.dto.stat.output;

import com.fasterxml.jackson.annotation.JsonTypeName;
import ru.aikam.dto.OutputDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

//@JsonTypeName("statOutputDTO")
public class StatOutputDTO extends OutputDTO {
    private Integer totalDays;
    private List<CustomerStatDTO> customers;
    private BigDecimal totalExpenses;
    private BigDecimal avgExpenses;

    public StatOutputDTO() {
    }

    public StatOutputDTO(String type, Integer totalDays, List<CustomerStatDTO> customers) {
        super(type);
        this.totalDays = totalDays;
        this.customers = customers;
        this.totalExpenses = customers.stream().
                map(customerStatDTO -> customerStatDTO.getTotalExpenses()).
                reduce((x, y) -> x.add(y)).orElse(null);
        this.avgExpenses = this.totalExpenses.divide(BigDecimal.valueOf(customers.size()), 2, RoundingMode.HALF_UP);
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public List<CustomerStatDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerStatDTO> customers) {
        this.customers = customers;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public BigDecimal getAvgExpenses() {
        return avgExpenses;
    }

    public void setAvgExpenses(BigDecimal avgExpenses) {
        this.avgExpenses = avgExpenses;
    }

    @Override
    public String toString() {
        return "StatOutputDTO{" +
                "totalDays=" + totalDays +
                ", customers=" + customers +
                ", totalExpenses=" + totalExpenses +
                ", avgExpenses=" + avgExpenses +
                '}';
    }
}
