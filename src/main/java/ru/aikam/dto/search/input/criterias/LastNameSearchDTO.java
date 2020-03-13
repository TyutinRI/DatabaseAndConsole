package ru.aikam.dto.search.input.criterias;

public class LastNameSearchDTO {
    private String lastName;

    public LastNameSearchDTO(){}

    public LastNameSearchDTO(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "LastNameSearchDTO{" +
                "lastName='" + lastName + '\'' +
                '}';
    }
}
