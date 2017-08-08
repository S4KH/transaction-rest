package com.transaction;

import java.util.ListIterator;

import com.transaction.controller.TransactionController;
import com.transaction.model.Statistic;
import com.transaction.model.Transaction;

/**
 * Calculates the statistics of the transactions
 * 
 * @author SKH
 *
 */
public class StatisticCalc implements Runnable {

	private Statistic statistic;
	
	public StatisticCalc(Statistic stat) {
		this.statistic = stat;
	}

	public void run() {
		long count = 0;
		int sum = 0;
		ListIterator<Transaction> listIterator = statistic.getTransactions().listIterator();
		while (listIterator.hasNext()) {
			Transaction t = listIterator.next();
			if (t.getTimestamp() / 1000 <= 60) {
				count++;
				statistic.setMax(Math.max(statistic.getMax(), t.getAmount()));
				statistic.setMin(Math.min(statistic.getMin(), t.getAmount()));
				sum += t.getAmount();
			} else {
				listIterator.remove();
			}
		}
		statistic.setSum(sum);
		statistic.setAvg(sum / count);
		statistic.setCount(count);
		TransactionController.setStatistic(statistic);
	}

}
