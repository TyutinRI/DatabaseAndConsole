package ru.aikam.dto.stat.output;

import com.fasterxml.jackson.annotation.JsonTypeName;
import ru.aikam.dto.OutputDTO;

import java.math.BigDecimal;
import java.util.List;

@JsonTypeName("statOutputDTO")
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
        this.avgExpenses = this.totalExpenses.divide(BigDecimal.valueOf(customers.size()));
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
