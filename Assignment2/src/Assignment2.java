import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
public class Assignment2 {
	
	public static void main(String[] args) {
        MySQLAccess dao = new MySQLAccess();
        
        try {
			//Connection connection = dao.connection();
			//Collection<Transaction> trxns = dao.getAllTransactions(connection);
			
//			for (Transaction trxn:trxns ){
//				System.out.println(trxn.toString());
//			}
////			
//			if (connection != null) {
//				connection.close();
//			}
        	/*
        	The format of the expiration date should be MM/YYYY, where MM means month and
        	YYYY means year. Both M and Y represent a single digit. The range of MM is between 01
        	and 12, and YYYY is between 2016 and 2031 (inclusively).*/
        	
  
        	/*****************INSERT************************/
        	Transaction trxn = new Transaction();
			trxn.setId(1);
			trxn.setNameOnCard("@YANZEWEI2");
			trxn.setCreditCardType(1);
			trxn.setCardNumber("5155555533252353");		
			trxn.setUnitPrice(3.2);
			trxn.setQuantity(50);
			//trxn.setTotalPrice(3.2);
			trxn.setExpDate("03/2031");
			
			dao.createTransaction(trxn);
			/*************************************************/
			
			
			/*********************GET*************************/
			dao.getTransaction(1);
	        
			/**********************************************/
			
			
			/*******************UPDATE****************************/
			trxn.setUnitPrice(34.5);
			dao.updateTransaction(trxn);
			/**********************************************/
			
			/*****************INSERT************************/
			dao.createTransaction(trxn);
			/*************************************************/
			
			/*******************REMOVE**********************/
			dao.removeTransaction(trxn.getId());
			/***********************************************/
			
			/*****************INSERT************************/
			dao.createTransaction(trxn);
			/*************************************************/
			dao.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}


