package ru.aikam.dto.stat.input;

import java.sql.Date;

public class StatInputDTO {
    private Date startDate;
    private Date endDate;

    public StatInputDTO() {
    }

    public StatInputDTO(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "StatInputDTO{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
