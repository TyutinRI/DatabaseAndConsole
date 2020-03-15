package ru.aikam.dto.stat.output;

import java.math.BigDecimal;
import java.util.List;

public class CustomerStatDTO {
    private String name;
    private List<PurchaseDTO> purchases;
    private BigDecimal totalExpenses;

    public CustomerStatDTO() {
    }

    public CustomerStatDTO(String name, List<PurchaseDTO> purchases) {
        this.name = name;
        this.purchases = purchases;
        this.totalExpenses = purchases.stream().
                map(purchaseDTO -> purchaseDTO.getExpenses()).
                reduce((x, y) -> x.add(y)).orElse(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PurchaseDTO> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<PurchaseDTO> purchases) {
        this.purchases = purchases;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    @Override
    public String toString() {
        return "CustomerStatDTO{" +
                "name='" + name + '\'' +
                ", purchases=" + purchases +
                ", totalExpenses=" + totalExpenses +
                '}';
    }
}
