package com.proship.omrs.contract.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pay {

    @Id
    private Long id ;
    private Long payeeId ;
    private Double salaryAmount ;
    private String salaryCurrency ;
    private Boolean gross ;
    private Integer salaryRecurrencePeriodNumerator ;
    private Integer salaryRecurrencePeriodDenominator ;
    private String salaryRecurrenceUnit ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    public Double getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(Double salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public String getSalaryCurrency() {
        return salaryCurrency;
    }

    public void setSalaryCurrency(String salaryCurrency) {
        this.salaryCurrency = salaryCurrency;
    }

    public Boolean getGross() {
        return gross;
    }

    public void setGross(Boolean gross) {
        this.gross = gross;
    }

    public Integer getSalaryRecurrencePeriodNumerator() {
        return salaryRecurrencePeriodNumerator;
    }

    public void setSalaryRecurrencePeriodNumerator(Integer salaryRecurrencePeriodNumerator) {
        this.salaryRecurrencePeriodNumerator = salaryRecurrencePeriodNumerator;
    }

    public Integer getSalaryRecurrencePeriodDenominator() {
        return salaryRecurrencePeriodDenominator;
    }

    public void setSalaryRecurrencePeriodDenominator(Integer salaryRecurrencePeriodDenominator) {
        this.salaryRecurrencePeriodDenominator = salaryRecurrencePeriodDenominator;
    }

    public String getSalaryRecurrenceUnit() {
        return salaryRecurrenceUnit;
    }

    public void setSalaryRecurrenceUnit(String salaryRecurrenceUnit) {
        this.salaryRecurrenceUnit = salaryRecurrenceUnit;
    }
}
