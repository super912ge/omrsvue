package com.proship.omrs.gig.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.proship.omrs.client.entity.Client;
import com.proship.omrs.client.entity.ClientMap;
import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.user.param.UserBrief;
import com.proship.omrs.venue.entity.Venue;
import com.proship.omrs.venue.entity.VenueMainShard;
import com.proship.omrs.venue.entity.VenueMainShardMap;
import com.proship.omrs.venue.param.VenueBrief;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GigBrief {

    private  Long id;

    private String name;

    private Client client;

    private VenueBrief venue;

    private UserBrief manager;

    private String room;

    private String chair;

    private String type;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String minSal;

    private UserBrief territory;

    public GigBrief(Gig gig){

        this.id = gig.getId();
        this.name = gig.getShard().getLabel();

        Long venueId = gig.getShard().getRoom().getVenue();

        VenueMainShard venueMainShard =  VenueMainShardMap.getVenueMainShard(venueId);

        String venueName = venueMainShard.getName();

        this.venue = new VenueBrief(venueId,venueName);
        this.client = new Client();
        this.client.setId(venueMainShard.getClientId());
        this.client.setCode(venueMainShard.getClientCode());

        if(gig.getChair()!=null) this.chair = gig.getChair().getLabel();
        this.type = gig.getShard().getBandType().getName();
        this.room = gig.getShard().getRoom().getName();
        if(gig.getPeriod()!=null) {
            this.startDate = gig.getPeriod().getValidstarttime();
            this.endDate = gig.getPeriod().getValidendtime();
        }
        this.minSal = gig.getShard().getMinSalaryAmount()+gig.getShard().getMinSalaryCurrency();

        if (gig.getTerritory()!=null) {
            this.territory = new UserBrief(gig.getTerritory().getUser());
        }
        this.manager = new UserBrief(gig.getShard().getResponsible());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public VenueBrief getVenue() {
        return venue;
    }

    public void setVenue(VenueBrief venue) {
        this.venue = venue;
    }

    public UserBrief getManager() {
        return manager;
    }

    public void setManager(UserBrief manager) {
        this.manager = manager;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getChair() {
        return chair;
    }

    public void setChair(String chair) {
        this.chair = chair;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getMinSal() {
        return minSal;
    }

    public void setMinSal(String minSal) {
        this.minSal = minSal;
    }

    public UserBrief getTerritory() {
        return territory;
    }

    public void setTerritory(UserBrief territory) {
        this.territory = territory;
    }
}