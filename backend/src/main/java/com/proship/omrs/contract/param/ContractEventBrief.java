package com.proship.omrs.contract.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.contract.entity.ContractEvent;
import com.proship.omrs.contract.entity.ContractMainShard;
import com.proship.omrs.contract.entity.ReasonCodeType;
import com.proship.omrs.contract.repository.ContractShardRepository;
import com.proship.omrs.gig.param.GigBrief;
import com.proship.omrs.jsonviews.UserSerializer;
import com.proship.omrs.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContractEventBrief {


    public ContractEventBrief(ContractEvent event){


        if (event.getComment()!=null&&event.getComment().getCommentDataShard()!=null&& event.getComment()
                .getCommentDataShard().getText()!=null&&!event.getComment().getCommentDataShard().getText()
                .trim().equals("")) {
            this.comment = event.getComment().getCommentDataShard().getText();
        }
        if (event.getReasonCode()!=null)
        this.reasonCodeType = event.getReasonCode().getReasonCodeType();

        this.creator = event.getCreator();

        if (event.getContractEventTerritoryTts()!=null)
        this.territory = event.getContractEventTerritoryTts().getUser();

        this.formDate = event.getContractEventFormDateTts().getValue();

        this.eventDate = event.getContractEventDateTts().getValue();

        this.transactionTime = event.getTransactionTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if(event.getName().equals("Salary")) {
            this.previousStatus = event.getPreviousShards().get(0).getPay();
            this.currentStatus = event.getCurrentShards().get(0).getPay();
        }
        if(event.getName().equals("Gig")){

            this.previousStatus = new GigBrief(event.getPreviousShards().get(0).getGig());
            this.currentStatus = new GigBrief(event.getCurrentShards().get(0).getGig());
        }
        if (event.getName().equals("Embark")){

            this.previousStatus = sdf.format(event.getPreviousShards().get(0).getValidstarttime());
            this.currentStatus = sdf.format(event.getCurrentShards().get(0).getValidstarttime());
        }
        if(event.getName().equals("Disembark")){
            this.previousStatus = sdf.format(event.getPreviousShards().get(0).getValidendtime());
            this.currentStatus = sdf.format(event.getCurrentShards().get(0).getValidendtime());
        }
        if(event.getName().equals("Rank")){
            this.previousStatus = event.getPreviousShards().get(0).getRank();
            this.currentStatus = event.getCurrentShards().get(0).getRank();
        }
        this.name = event.getName();

    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date eventDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date formDate;

    @JsonSerialize(using = UserSerializer.class)
    private User territory;
    @JsonSerialize(using = UserSerializer.class)
    private User creator;

    private String name;

    private ReasonCodeType reasonCodeType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date transactionTime;

    private String comment;

    private Object previousStatus;

    private Object currentStatus;

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Date getFormDate() {
        return formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public User getTerritory() {
        return territory;
    }

    public void setTerritory(User territory) {
        this.territory = territory;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public ReasonCodeType getReasonCodeType() {
        return reasonCodeType;
    }

    public void setReasonCodeType(ReasonCodeType reasonCodeType) {
        this.reasonCodeType = reasonCodeType;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Object getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(ContractMainShard previousStatus) {
        this.previousStatus = previousStatus;
    }

    public Object getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(ContractMainShard currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreviousStatus(Object previousStatus) {
        this.previousStatus = previousStatus;
    }

    public void setCurrentStatus(Object currentStatus) {
        this.currentStatus = currentStatus;
    }
}
