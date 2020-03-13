package ru.aikam.dto.search.input.criterias;

public class BadCustomerDTO {
    private Integer badCustomers;

    public BadCustomerDTO(){}

    public BadCustomerDTO(Integer badCustomers) {
        this.badCustomers = badCustomers;
    }

    public Integer getBadCustomers() {
        return badCustomers;
    }

    public void setBadCustomers(Integer badCustomers) {
        this.badCustomers = badCustomers;
    }

    @Override
    public String toString() {
        return "BadCustomerDTO{" +
                "badCustomers=" + badCustomers +
                '}';
    }
}
