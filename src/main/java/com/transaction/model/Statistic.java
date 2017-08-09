package com.transaction.model;

/**
 * Represents transaction statistics;
 * 
 * @author SKH
 *
 */

public class Statistic {

	private double sum;
	private double avg;
	private double max = 0;
	private double min = -1;
	private long count;

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double sum, long count) {
		if (count > 0) {
			this.avg = sum / count;
		} else {
			this.avg = 0;
		}
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

}
