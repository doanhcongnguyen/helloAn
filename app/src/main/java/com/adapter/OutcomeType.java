package com.adapter;

/**
 * Created by alpha on 4/22/2017.
 */

public class OutcomeType {

    private Long no;
    private Long outcomeTypeId;
    private String outcomeTypeName;
    private Long isActive;
    private String note;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Long getOutcomeTypeId() {
        return outcomeTypeId;
    }

    public void setOutcomeTypeId(Long outcomeTypeId) {
        this.outcomeTypeId = outcomeTypeId;
    }

    public String getOutcomeTypeName() {
        return outcomeTypeName;
    }

    public void setOutcomeTypeName(String outcomeTypeName) {
        this.outcomeTypeName = outcomeTypeName;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
