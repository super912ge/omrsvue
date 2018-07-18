package com.proship.omrs.msd.entity;


import javax.persistence.*;

@Entity
public class MsdBracket {

    @Id
    private Long id;

    private Long msd;

    private Float amount;

    private String currency;

    @ManyToOne
    @JoinColumn(name = "bracket_setting_id")
    private MsdBracketSettingType msdBracketSettingType;

    @OneToOne(mappedBy = "msdBracket")
    private UserMsdBracketShard userMsdBracketShard;

    public Long getId() {
        return id;
    }

    public UserMsdBracketShard getUserMsdBracketShard() {
        return userMsdBracketShard;
    }

    public void setUserMsdBracketShard(UserMsdBracketShard userMsdBracketShard) {
        this.userMsdBracketShard = userMsdBracketShard;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMsd() {
        return msd;
    }

    public void setMsd(Long msd) {
        this.msd = msd;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public MsdBracketSettingType getMsdBracketSettingType() {
        return msdBracketSettingType;
    }

    public void setMsdBracketSettingType(MsdBracketSettingType msdBracketSettingType) {
        this.msdBracketSettingType = msdBracketSettingType;
    }
}
