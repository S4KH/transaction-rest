package com.transaction.model;

import java.util.LinkedList;

/**
 * Represents transaction statistics;
 * @author SKH
 *
 */

public class Statistic {
	
	private double sum;
	private double avg;
	private double max;
	private double min;
	private long count;
	private LinkedList<Transaction> transactions;
	
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public LinkedList<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(LinkedList<Transaction> t) {
		this.transactions = t;
	}

}
