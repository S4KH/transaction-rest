package com.transaction.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {
	
	@JsonProperty("amount")
	private double amount;
	@JsonProperty("timestamp")
	private long timestamp;
	
	public Transaction(){}
	public Transaction(double amt, long tp){
		this.amount = amt;
		this.timestamp = tp;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
