package com.proship.omrs.document.base.param;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;
@Getter
@Setter

public class DocumentSearchTerm {

    List<Long> ids;

    Date expireDate;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
