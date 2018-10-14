import java.sql.Connection;
import java.util.Collection;

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
        	
        	/*****************INSERT************************/
        	Transaction trxn = new Transaction();
			trxn.setId(1);
			trxn.setNameOnCard("YANZEWEI");
			trxn.setCardNumber("1000010000");		
			trxn.setUnitPrice(3.2);
			trxn.setQuantity(50);
			trxn.setTotalPrice(160.0);
			trxn.setExpDate("03/2022");
			trxn.setCreditCardType(1);
			trxn.setPrefix(51);
			trxn.setCreditCardNumber("1234567891234567");
			trxn.setCreditCardExpire("03/2019");
			dao.createTransaction(trxn);
			/*************************************************/
			
			
			/*********************GET*************************/
			dao.getTransaction(1);
			/**********************************************/
			
			
			/*******************UPDATE****************************/
			trxn.setCreditCardExpire("05/2022");
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
