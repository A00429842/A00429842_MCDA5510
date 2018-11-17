package com.mcda5510.service;

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
	
	public void test()
	{
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
