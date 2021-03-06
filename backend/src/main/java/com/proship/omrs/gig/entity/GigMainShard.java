package com.proship.omrs.gig.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.base.entity.MainShardEntity;
import com.proship.omrs.gig.param.RoomSerializer;
import com.proship.omrs.jsonviews.UserSerializer;
import com.proship.omrs.user.entity.User;
import com.proship.omrs.venue.entity.Room;
import com.proship.omrs.venue.entity.VenueBase;
import com.proship.omrs.venue.entity.VenueMainShard;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Where(clause = "nexttransactiontime > now()")

public class GigMainShard extends MainShardEntity{

    @Id
    private Long id;

    private String label ;

    @JsonSerialize(using = RoomSerializer.class)
    @ManyToOne
    @JoinColumn(name = "room")
    private Room room ;

    private Double minSalaryAmount;

    private String minSalaryCurrency;

    private Boolean minSalaryGross;

    private Integer minSalaryRecurrencePeriodNumerator;

    private Integer minSalaryRecurrencePeriodDenominator ;

    private String minSalaryRecurrenceUnit  ;

    private Double maxSalaryAmount;

    private String maxSalaryCurrency;

    private Boolean maxSalaryGross ;

    private Integer maxSalaryRecurrencePeriodNumerator ;

    private Integer maxSalaryRecurrencePeriodDenominator ;

    private String maxSalaryRecurrenceUnit  ;

    private Boolean exclusive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "responsible")
    @JsonSerialize(using = UserSerializer.class)
    private User responsible ;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Gig gig ;

    @JsonIgnore
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

    public Room getRoom() {

      if (room.getVenue().getVenueMainShard().getValidstarttime().after(this.getValidendtime())){
            VenueBase venue = room.getVenue();

            List<VenueMainShard> venueMainShards = venue.getVenueMainShards().stream()
                    .filter(i-> !i.getValidstarttime().after(this.getValidendtime()) &&
                            !i.getValidendtime().before(this.getValidstarttime())).collect(Collectors.toList());
            if (!venueMainShards.isEmpty()) venue.setVenueMainShard(venueMainShards.get(0));
            room.setVenue(venue);
        }
        return room;
    }

    public void setRoom(Room room) {
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

    public String getMaxSalaryCurrency() {
        return maxSalaryCurrency;
    }

    public void setMaxSalaryCurrency(String maxSalaryCurrency) {
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
        if (bandType== null) setBandType(GigTypeMap.getBandType(this.gigType));

        return bandType;
    }

    public void setBandType(BandType bandType) {
        this.bandType = bandType;
        this.gigType = bandType.getId();
    }

    public String getSalaryRange() {
        String str = "";


        if (this.minSalaryAmount!=null) str+=this.minSalaryAmount+" " + this.minSalaryCurrency
                + " /"+this.minSalaryRecurrenceUnit+ " - ";
        if (this.maxSalaryAmount!=null) str+=       this.getMaxSalaryAmount()+" "+ this.maxSalaryCurrency+
                " /"+this.maxSalaryRecurrenceUnit;

        return str;
    }
}
