package com.mcda5510;

import java.rmi.RemoteException;

import com.mcda5510.entity.Transaction;
import com.mcda5510.dao.MySQLAccess;
import com.mcda5510.service.TrxnWebServiceProxy;

public class TestClient {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		TrxnWebServiceProxy proxy = new TrxnWebServiceProxy();
        proxy.setEndpoint("http://dev.cs.smu.ca:8283/Assignment3/services/TrxnWebService");//defined in wsdl
        //proxy.setEndpoint("http://localhost:8080/Assignment3/services/TrxnWebService");//defined in wsdl

        try {
			MySQLAccess dao = new MySQLAccess();
			proxy.setDao(dao);
        	Transaction trxn = new Transaction();
			trxn.setId(2);
			trxn.setNameOnCard("@YANZEWEI2");
			trxn.setCreditCardType(1);
		    trxn.setCardNumber("5155555533252353");
			trxn.setUnitPrice(3.2);
			trxn.setQuantity(50);
			trxn.setExpDate("03/2031");
			//proxy.removeTransaction(3);
			proxy.createTransaction(trxn);
			Transaction trx = proxy.getTransaction(1);
			System.out.println(trx.getCreatedBy());

//			/*************************************************/
//			
//			
//			/*********************GET*************************/
//			proxy.getTransaction(1);
//	        
//			/**********************************************/
//			
//			
//			/*******************UPDATE****************************/
//			trxn.setUnitPrice(34.5);
//			proxy.updateTransaction(trxn);
//			/**********************************************/
//			
//			/*****************INSERT************************/
//			proxy.createTransaction(trxn);
//			/*************************************************/
//			
//			/*******************REMOVE**********************/
//			proxy.removeTransaction(trxn.getId());
//			/***********************************************/
//			
//			/*****************INSERT************************/
//			proxy.createTransaction(trxn);
//			/*************************************************/
//			proxy.closeConnection();
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
