package com.transaction.controller;

import java.util.Date;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.StatisticCalc;
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
	private static final int SUCCESS = 201;
	private static final int FAILED_OLD = 204;
	private static Statistic statistics;
	private static LinkedList<Transaction> transactions;
	
    @RequestMapping(value = PATH)
    public String error() {
    	return "Unexpected error has happened.Please contact administrator!!!";
    }
	
	@RequestMapping(value = "/transaction", method = RequestMethod.POST, 
            consumes = "application/json")
	public void transaction(@RequestBody Transaction t, HttpServletResponse response){
		Date now = new Date();
		long nowInSecs = now.getTime();
		Thread calcer = null;
		
		//Checking if transaction is old
		if((nowInSecs-t.getTimestamp())/1000 <= 60){
			transactions.add(t);
			calcer = new Thread(new StatisticCalc(transactions));
			calcer.start();
			response.setStatus(SUCCESS);			
		}else{
			response.setStatus(FAILED_OLD);
		}
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

}

