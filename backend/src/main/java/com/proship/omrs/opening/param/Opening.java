package com.proship.omrs.opening.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proship.omrs.base.entity.MainShardEntityWithGig;
import com.proship.omrs.contract.entity.ContractMainShard;
import com.proship.omrs.gig.param.GigBrief;

import java.util.Date;

public class Opening {


    public Opening(MainShardEntityWithGig shard, Boolean isGeneratingGap){

        if (isGeneratingGap){

            this.startDate = shard.getValidendtime();

            this.endDate = null;


        }else {

            this.endDate = shard.getValidendtime();

            this.startDate = shard.getValidstarttime();

        }

        this.gap = isGeneratingGap;

        this.gigBrief = new GigBrief(shard.getGig());

        if (shard instanceof ContractMainShard){

            ContractMainShard contract = (ContractMainShard) shard;

            if (contract.getAct()==null) System.out.println("-----no act------"+contract.getId());

            else if (contract.getAct().getParticipant()==null)System.out.println("------no participant-----"+contract.getAct().getId());

            else {

                this.replaceId = contract.getAct().getParticipant().getId();

                this.replaceName = contract.getAct().getParticipant().getName();
            }

        }
    }

    public Opening (MainShardEntityWithGig previous, MainShardEntityWithGig next){

        this.endDate = next.getValidstarttime();

        this.startDate = previous.getValidendtime();

        this.gap = true;

        this.gigBrief = new GigBrief(previous.getGig());

        if (previous instanceof ContractMainShard) {

            ContractMainShard contract= (ContractMainShard) previous;

            if (contract.getAct()==null) System.out.println("----no act-------"+contract.getId());

            else if (contract.getAct().getParticipant()==null)System.out.println("-----no participant------"+contract.getAct().getId());

            else {

                this.replaceId = contract.getAct().getParticipant().getId();

                this.replaceName = contract.getAct().getParticipant().getName();
            }

        }
    }


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private Boolean gap;

    private GigBrief gigBrief;

    private Long replaceId;

    private String replaceName;



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

    public Boolean getGap() {
        return gap;
    }

    public void setGap(Boolean gap) {
        this.gap = gap;
    }

    public GigBrief getGigBrief() {
        return gigBrief;
    }

    public void setGigBrief(GigBrief gigBrief) {
        this.gigBrief = gigBrief;
    }

    public Long getReplaceId() {
        return replaceId;
    }

    public void setReplaceId(Long replaceId) {
        this.replaceId = replaceId;
    }

    public String getReplaceName() {
        return replaceName;
    }

    public void setReplaceName(String replaceName) {
        this.replaceName = replaceName;
    }
}
