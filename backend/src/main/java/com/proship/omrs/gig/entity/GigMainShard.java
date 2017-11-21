package com.proship.omrs.gig.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proship.omrs.base.entity.BaseEntity;
import com.proship.omrs.user.entity.User;
import javax.persistence.*;
import java.util.Date;

@Entity
public class GigMainShard extends BaseEntity{

    @Id
    private Long id  ;
    private String label  ;
    private Long room ;
    private Double minSalaryAmount;
    private String minSalaryCurrency;
    private Boolean minSalaryGross;
    private Integer minSalaryRecurrencePeriodNumerator;
    private Integer minSalaryRecurrencePeriodDenominator ;
    private String minSalaryRecurrenceUnit  ;
    private Double maxSalaryAmount;
    private Double maxSalaryCurrency  ;
    private Boolean maxSalaryGross ;
    private Integer maxSalaryRecurrencePeriodNumerator ;
    private Integer maxSalaryRecurrencePeriodDenominator ;
    private String maxSalaryRecurrenceUnit  ;
    private Boolean exclusive     ;

    @ManyToOne
    @JoinColumn(name = "responsible")
    private User responsible ;

    private Long creatorId  ;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validstarttime   ;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validendtime  ;

    @OneToOne
    private Gig gig ;

    private Long gigType ;

    @Transient
    private BandType bandType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getRoom() {
        return room;
    }

    public void setRoom(Long room) {
        this.room = room;
    }

    public Double getMinSalaryAmount() {
        return minSalaryAmount;
    }

    public void setMinSalaryAmount(Double minSalaryAmount) {
        this.minSalaryAmount = minSalaryAmount;
    }

    public String getMinSalaryCurrency() {
        return minSalaryCurrency;
    }

    public void setMinSalaryCurrency(String minSalaryCurrency) {
        this.minSalaryCurrency = minSalaryCurrency;
    }

    public Boolean getMinSalaryGross() {
        return minSalaryGross;
    }

    public void setMinSalaryGross(Boolean minSalaryGross) {
        this.minSalaryGross = minSalaryGross;
    }

    public Integer getMinSalaryRecurrencePeriodNumerator() {
        return minSalaryRecurrencePeriodNumerator;
    }

    public void setMinSalaryRecurrencePeriodNumerator(Integer minSalaryRecurrencePeriodNumerator) {
        this.minSalaryRecurrencePeriodNumerator = minSalaryRecurrencePeriodNumerator;
    }

    public Integer getMinSalaryRecurrencePeriodDenominator() {
        return minSalaryRecurrencePeriodDenominator;
    }

    public void setMinSalaryRecurrencePeriodDenominator(Integer minSalaryRecurrencePeriodDenominator) {
        this.minSalaryRecurrencePeriodDenominator = minSalaryRecurrencePeriodDenominator;
    }

    public String getMinSalaryRecurrenceUnit() {
        return minSalaryRecurrenceUnit;
    }

    public void setMinSalaryRecurrenceUnit(String minSalaryRecurrenceUnit) {
        this.minSalaryRecurrenceUnit = minSalaryRecurrenceUnit;
    }

    public Double getMaxSalaryAmount() {
        return maxSalaryAmount;
    }

    public void setMaxSalaryAmount(Double maxSalaryAmount) {
        this.maxSalaryAmount = maxSalaryAmount;
    }

    public Double getMaxSalaryCurrency() {
        return maxSalaryCurrency;
    }

    public void setMaxSalaryCurrency(Double maxSalaryCurrency) {
        this.maxSalaryCurrency = maxSalaryCurrency;
    }

    public Boolean getMaxSalaryGross() {
        return maxSalaryGross;
    }

    public void setMaxSalaryGross(Boolean maxSalaryGross) {
        this.maxSalaryGross = maxSalaryGross;
    }

    public Integer getMaxSalaryRecurrencePeriodNumerator() {
        return maxSalaryRecurrencePeriodNumerator;
    }

    public void setMaxSalaryRecurrencePeriodNumerator(Integer maxSalaryRecurrencePeriodNumerator) {
        this.maxSalaryRecurrencePeriodNumerator = maxSalaryRecurrencePeriodNumerator;
    }

    public Integer getMaxSalaryRecurrencePeriodDenominator() {
        return maxSalaryRecurrencePeriodDenominator;
    }

    public void setMaxSalaryRecurrencePeriodDenominator(Integer maxSalaryRecurrencePeriodDenominator) {
        this.maxSalaryRecurrencePeriodDenominator = maxSalaryRecurrencePeriodDenominator;
    }

    public String getMaxSalaryRecurrenceUnit() {
        return maxSalaryRecurrenceUnit;
    }

    public void setMaxSalaryRecurrenceUnit(String maxSalaryRecurrenceUnit) {
        this.maxSalaryRecurrenceUnit = maxSalaryRecurrenceUnit;
    }

    public Boolean getExclusive() {
        return exclusive;
    }

    public void setExclusive(Boolean exclusive) {
        this.exclusive = exclusive;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getValidstarttime() {
        return validstarttime;
    }

    public void setValidstarttime(Date validstarttime) {
        this.validstarttime = validstarttime;
    }

    public Date getValidendtime() {
        return validendtime;
    }

    public void setValidendtime(Date validendtime) {
        this.validendtime = validendtime;
    }

    public Gig getGig() {
        return gig;
    }

    public void setGig(Gig gig) {
        this.gig = gig;
    }

    public Long getGigType() {
        return gigType;
    }

    public void setGigType(Long gigType) {
        this.gigType = gigType;
    }

    public BandType getBandType() {
        return GigTypeMap.getBandType(this.gigType);
    }

    public void setBandType(BandType bandType) {
        this.bandType = bandType;
        this.gigType = bandType.getId();
    }
}
