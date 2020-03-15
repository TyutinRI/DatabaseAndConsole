package ru.aikam.dto.search.input.search.criterias;

public class GoodAndNumberTimesDTO {
    private String productName;
    private Integer minTimes;

    public GoodAndNumberTimesDTO(){}

    public GoodAndNumberTimesDTO(String productName, Integer minTimes) {
        this.productName = productName;
        this.minTimes = minTimes;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getMinTimes() {
        return minTimes;
    }

    public void setMinTimes(Integer minTimes) {
        this.minTimes = minTimes;
    }

    @Override
    public String toString() {
        return "GoodAndNumberTimesDTO{" +
                "productName='" + productName + '\'' +
                ", minTimes=" + minTimes +
                '}';
    }
}
