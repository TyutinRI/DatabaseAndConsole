package ru.aikam.dto.search.output.crirerias;

public class CustomerFirstAndLastNameDTO {
    String firstName;
    String lastName;

    public CustomerFirstAndLastNameDTO(){}

    public CustomerFirstAndLastNameDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "CustomerFirstAndLastNameDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
