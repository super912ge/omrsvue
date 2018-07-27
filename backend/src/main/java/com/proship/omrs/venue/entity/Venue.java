package com.proship.omrs.venue.entity;

import com.proship.omrs.client.entity.ClientMap;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;


@Entity
@Subselect("select v.id as id, m.name as name, m.clientId as clientId, m.type as type, m.validstarttime as validStartTime" +
        "p.validstarttime as startDate, p.validendtime as endDate from venue v join venue_main_shard m" +
        " on v.id = m.venue_id join venue_period_shard p on v.id = p.venue_id where m.validendtime > now() and " +
        " m.nexttransactiontime > now() and p.nexttransactiontime > now()")

@Synchronize({"venue","venue_main_shard","venue_period_shard"})
public class Venue {

    @Id
    private Long id;

    private String name;

    private String type;

    private Date startDate;

    private Date endDate;

    private Date validStartTime;

    @Transient
    private String clientCode;

    private Long clientId;

    public Date getValidStartTime() {
        return validStartTime;
    }

    public void setValidStartTime(Date validStartTime) {
        this.validStartTime = validStartTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getClientCode() {

        if (clientCode==null) setClientCode();

        return clientCode;
    }

    private void setClientCode() {
        this.clientCode = ClientMap.getClient(clientId).getCode();
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
