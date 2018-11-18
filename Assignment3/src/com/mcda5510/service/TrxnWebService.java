package com.mcda5510.service;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.mcda5510.connection.ConnectionSingleton;
import com.mcda5510.dao.*;
import com.mcda5510.entity.Transaction;

public class TrxnWebService {
	
	 public static MySQLAccess dao;
	 
	
	 public MySQLAccess getDao()
	 {
		 return this.dao;
	 }
	 
	 public void setDao(MySQLAccess dao) {
		 this.dao = dao;
	 }
	 
	 public Transaction getTransaction( Integer tid) {
		 return dao.getTransaction(tid);
	 }
	 
	 public boolean createTransaction( Transaction trxn) throws IllegalArgumentException, IllegalAccessException, Exception {
		 //return false;
		 return dao.createTransaction(trxn);
	 }
    
	 
	 public boolean updateTransaction( Transaction trxn) throws IllegalArgumentException, IllegalAccessException, Exception {
		 return dao.updateTransaction(trxn);

	 }
	 
	 public boolean removeTransaction( Integer tid) {
		 return dao.removeTransaction(tid);
	 }
	
			
	public void closeConnection() {
		dao.closeConnection();
	}
	
	public void test() throws IOException
	{
		Logger logger = Logger.getLogger("MyLog");  
	    FileHandler fh;  

	    try {  

	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler("/home/student_2018_fall/z_yan/A00429842_MCDA5510/Assignment3/test.log");  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  

	        // the following statement is used to log any messages  
	        logger.info("My first log");  

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } 
	    logger.info("Hi How r u?");  
		System.out.println("test1");
	}
	
	
	public static void main(String[] args) throws Exception {
		/*TrxnWebService proxy = new TrxnWebService();
		MySQLAccess dao = new MySQLAccess();
		proxy.setDao(dao);
		Transaction trxn = new Transaction();
		trxn.setId(1);
		trxn.setNameOnCard("@YANZEWEI2");
		trxn.setCreditCardType(1);
		trxn.setCardNumber("5155555533252353");		
		trxn.setUnitPrice(3.2);
		trxn.setQuantity(50);
		trxn.setExpDate("03/2031");
		proxy.createTransaction(trxn);*/
	}
}
