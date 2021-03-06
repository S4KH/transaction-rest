package com.transaction.controller;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.model.Statistic;
import com.transaction.model.Transaction;

/**
 * Handles transaction related requests
 * @author SKH
 *
 */

@RestController
public class TransactionController implements ErrorController{
	
	private static final String PATH = "/error";
	private static Statistic statistics = new Statistic();
	private static BlockingQueue<Transaction> transactions = new LinkedBlockingQueue<Transaction>();
	public static final int OLD_TRX = 60;
	
    @RequestMapping(value = PATH)
    public String error() {
    	return "Unexpected error has happened.Please contact administrator!!!";
    }
	
	@PostMapping("/transaction")
	public ResponseEntity<?> transaction(@RequestBody Transaction t){
		Date now = new Date();
		long nowInSecs = now.getTime();
		
		//Checking if transaction is old
		if((nowInSecs-t.getTimestamp())/1000 <= OLD_TRX){
			try {
				transactions.put(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return new ResponseEntity(HttpStatus.CREATED);
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/statistics", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Statistic> statistic(){
		return new ResponseEntity<Statistic>(statistics, HttpStatus.OK);		
	}
	
	@RequestMapping("/")
	public String homePage(){
		return "Hello There";
	}

	public String getErrorPath() {
		return PATH;
	}
	
	public static void setStatistic(Statistic stat){
		statistics = stat;
	}
	
	public static BlockingQueue<Transaction> getTransactions(){
		return transactions;
	}

}

