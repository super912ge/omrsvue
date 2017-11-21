package com.proship.omrs.base.service;

import java.sql.Timestamp;

public interface BaseService {
	
	public final static Timestamp nextTransactionTime = Timestamp.valueOf("9999-12-31 24:00:00.0") ;
}
