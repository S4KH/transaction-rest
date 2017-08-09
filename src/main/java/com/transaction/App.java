package com.transaction;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.transaction.controller.TransactionController;

/**
 * 
 * @author SKH
 *
 */

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    	
    	// Start statistics calculator
    	TimerTask task = new StatisticTask(TransactionController.getTransactions());
    	Timer timer = new Timer();
    	timer.schedule(task, 1000,2000);
    }
}
