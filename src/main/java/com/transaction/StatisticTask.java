package com.transaction;

import java.util.Date;
import java.util.Iterator;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

import com.transaction.controller.TransactionController;
import com.transaction.model.Statistic;
import com.transaction.model.Transaction;

/**
 * Calculates the statistics of the transactions
 * 
 * @author SKH
 *
 */
public class StatisticTask extends TimerTask {

	private BlockingQueue<Transaction> trans;
	
	public StatisticTask(BlockingQueue<Transaction> t) {
		this.trans = t;
	}

	@Override
	public void run() {
		calculateStatistics();
	}

	private void calculateStatistics() {
		if(trans.isEmpty()){
			TransactionController.setStatistic(new Statistic());
			return;
		}
		long count = 0;
		int sum = 0;
		Statistic statistic = new Statistic();
		Iterator<Transaction> listIterator = trans.iterator();
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
		statistic.setAvg(sum, count);
		statistic.setCount(count);
		TransactionController.setStatistic(statistic);
	}
	
	

}
