package com.proship.omrs.candidate.group.param;


import com.proship.omrs.candidate.group.entity.Act;

public class BandBrief {


    public BandBrief(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public BandBrief(Act act) {
        this.id = act.getId();
        this.name = act.getGroupActMainShard().getName();
        try {
            if (act.getGroupActMainShard().getBandType()!=null)
            this.type = act.getGroupActMainShard().getBandType().getName();
        }catch (Exception e){
            e.printStackTrace();

        }

    }

    private Long id;

    private String name;

    private String type;


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
}
