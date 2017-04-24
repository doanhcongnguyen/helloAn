package com.adapter;

import java.util.Date;

/**
 * Created by alpha on 4/22/2017.
 */

public class Outcome {

    private Long outcomeId;
    private String outcomeName;
    private Long outcomeType;
    private Double amount;
    private String note;
    private Date createdDate;
    private Long month;
    private Long year;

    public Long getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(Long outcomeId) {
        this.outcomeId = outcomeId;
    }

    public String getOutcomeName() {
        return outcomeName;
    }

    public void setOutcomeName(String outcomeName) {
        this.outcomeName = outcomeName;
    }

    public Long getOutcomeType() {
        return outcomeType;
    }

    public void setOutcomeType(Long outcomeType) {
        this.outcomeType = outcomeType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }
}
