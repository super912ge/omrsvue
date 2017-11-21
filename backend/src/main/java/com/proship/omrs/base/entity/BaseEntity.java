package com.proship.omrs.base.entity;

import java.sql.Timestamp;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

	@JsonIgnore
	private Timestamp transactiontime;
	
	@JsonIgnore
	private Timestamp nexttransactiontime;

	public Timestamp getTransactiontime() {
		return transactiontime;
	}

	public void setTransactiontime(Timestamp transactiontime) {
		this.transactiontime = transactiontime;
	}

	public Timestamp getNexttransactiontime() {
		return nexttransactiontime;
	}

	public void setNexttransactiontime(Timestamp nexttransactiontime) {
		this.nexttransactiontime = nexttransactiontime;
	}

	
	
	
}
