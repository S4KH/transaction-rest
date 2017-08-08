package com.transaction;

import java.util.Date;
import java.util.LinkedList;
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

	private LinkedList<Transaction> trans;
	
	public StatisticCalc(LinkedList<Transaction> t) {
		this.trans = t;
	}

	public void run() {
		long count = 0;
		int sum = 0;
		Statistic statistic = new Statistic();
		ListIterator<Transaction> listIterator = trans.listIterator();
		Date now = new Date();
		long nowInSecs = now.getTime();
		while (listIterator.hasNext()) {
			Transaction t = listIterator.next();
			if ((nowInSecs-t.getTimestamp()) / 1000 <= TransactionController.OLD_TRX) {
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
