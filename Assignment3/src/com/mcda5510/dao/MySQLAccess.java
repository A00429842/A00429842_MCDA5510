package com.mcda5510.dao;

/**
 * Original source code from 
 * http://www.vogella.com/tutorials/MySQLJava/article.html
 * 
 **/

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.Gson;
import com.mcda5510.connection.ConnectionSingleton;
import com.mcda5510.entity.*;

public class MySQLAccess {
	private static Connection connection;
	public MySQLAccess() {
		try {
			connection = ConnectionSingleton.getConnection();
			setupLogpath();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setupLogpath() {
		String relativePath = System.getProperty("user.dir");
		int lastIndex = relativePath.lastIndexOf("Assignment3")+"Assignment3".length();	
		relativePath = relativePath.substring(0, lastIndex);
		String logPath = relativePath + "/logging.properties";
		System.setProperty("java.util.logging.config.file",
		        			logPath);
	}

	
	
	public void validation(Transaction trxn) throws IllegalArgumentException, IllegalAccessException, Exception
	{
		Field[] declaredFields = trxn.getClass().getDeclaredFields();
        for(Field field : declaredFields) {
            //System.out.println(field.getName());
            Annotation annotation = field.getAnnotation(Required.class);
            
            if (annotation != null) {

                Required required = (Required) annotation;

                /**
                 *  Check if it says this field is required
                 */
                if (required.value()) {
                    /**
                     *  Now we make sure we can access the private
                     *  fields also, so we need to call this method also
                     *  other wise we would get a {@link java.lang.IllegalAccessException}
                     */
                    field.setAccessible(true);
                    /**
                     *  If this field is required, then it should be present
                     *  in the declared fields array, if it is throw the
                     *  {@link RequiredFieldException}
                     */

                    if (field.get(trxn) == null) {
            			Logger.getLogger("Main").log(Level.SEVERE,field.getName()+" is NULL");
                    	throw new Exception(field.getName()+" is NULL");
                    }else {
                    	try {
                    		String regEx="[; : ! @ # $ % ^ * + ? < >]";  
                            Pattern   p   =   Pattern.compile(regEx);     
                            Matcher   m   =   p.matcher(String.valueOf(field.get(trxn)));   
                            field.set(trxn, m.replaceAll("").trim());
                    	}catch(IllegalArgumentException e) {
                    		
                    	}
                    	 
                    }
                }
            }
        }
	}
	
	public boolean createTransaction( Transaction trxn) throws IllegalArgumentException, IllegalAccessException, Exception 
	{
		boolean result = false;
		if(getTransaction(trxn.getId()) != null)
		{
			System.out.println("ID exists in DB and you should invoke updateTransaction");
			Logger.getLogger("Main").log(Level.SEVERE, "try to insert the same id:"+trxn.getId());
			return result;
		}
		validation(trxn);
		
		String insertTableSQL = "insert into transaction"
				+ "(`transaction`.`ID`, `transaction`.`NameOnCard`, `transaction`.`CardNumber`, `transaction`.`UnitPrice`, `transaction`.`Quantity`,"
				+ " `transaction`.`TotalPrice`, `transaction`.`ExpDate`, `transaction`.`CreatedOn`, `transaction`.`CreatedBy`, "
				+ "`transaction`.`CreditCardType`, `CreditCardTypeName`) VALUES "
				+ "(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, trxn.getId());
			preparedStatement.setString(2, trxn.getNameOnCard());
			preparedStatement.setString(3, trxn.getCardNumber());
			preparedStatement.setDouble(4, trxn.getUnitPrice());
			preparedStatement.setDouble(5, trxn.getQuantity());
			preparedStatement.setDouble(6, trxn.getTotalPrice());
			preparedStatement.setString(7, trxn.getExpDate());
			preparedStatement.setString(8, trxn.getCreatedOn());
			preparedStatement.setString(9, trxn.getCreatedBy());
			preparedStatement.setInt(10, trxn.getCreditCardType());
			preparedStatement.setString(11, trxn.getCreditCardTypeName());
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
			result = true;
			Gson gson = new Gson();
			String info = gson.toJson(trxn);
			Logger.getLogger("Main").log(Level.INFO, "saves a row in the database successfully: " + info);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logger.getLogger("Main").log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} catch(NullPointerException e) {
			Logger.getLogger("Main").log(Level.SEVERE, e.getMessage());
		}
		
		return result;
	}
	
	public boolean updateTransaction(Transaction trxn) throws IllegalArgumentException, IllegalAccessException, Exception
	{
		boolean result = false;
		if(getTransaction(trxn.getId()) != null)
		{
			System.out.println("ID do not exist in DB and you should invoke createTransaction");
			Logger.getLogger("Main").log(Level.SEVERE, "try to update with id:"+trxn.getId());
			return result;
		}
		validation(trxn);
		String insertTableSQL = "replace into transaction"
				+ "(`transaction`.`ID`, `transaction`.`NameOnCard`, `transaction`.`CardNumber`, `transaction`.`UnitPrice`, `transaction`.`Quantity`,"
				+ " `transaction`.`TotalPrice`, `transaction`.`ExpDate`, `transaction`.`CreatedOn`, `transaction`.`CreatedBy`, "
				+ "`transaction`.`CreditCardType`,`CreditCardTypeName`) VALUES "
				+ "(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, trxn.getId());
			preparedStatement.setString(2, trxn.getNameOnCard());
			preparedStatement.setString(3, trxn.getCardNumber());
			preparedStatement.setDouble(4, trxn.getUnitPrice());
			preparedStatement.setDouble(5, trxn.getQuantity());
			preparedStatement.setDouble(6, trxn.getTotalPrice());
			preparedStatement.setString(7, trxn.getExpDate());
			preparedStatement.setString(8, trxn.getCreatedOn());
			preparedStatement.setString(9, trxn.getCreatedBy());
			preparedStatement.setInt(10, trxn.getCreditCardType());
			preparedStatement.setString(11, trxn.getCreditCardTypeName());
			// execute insert SQL stetement
			preparedStatement .executeUpdate();
			result = true;
			Gson gson = new Gson();
			String info = gson.toJson(trxn);
			Logger.getLogger("Main").log(Level.INFO, "update a row in the database successfully: " + info);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logger.getLogger("Main").log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			Logger.getLogger("Main").log(Level.SEVERE, e.getMessage());
		}
		
		return result;
	}
	
	public boolean removeTransaction(Integer trxnID)
	{
		Transaction trxn = null;
		Statement statement = null;
		boolean result = false;
		try {
			// Statements allow to issue SQL queries to the database
			statement = connection.createStatement();
			result = statement.execute("delete from z_yan.transaction where ID = " + trxnID);

			if (statement != null) {
				statement.close();
			}

			Logger.getLogger("Main").log(Level.INFO, "delete successfully with id:" + trxnID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger("Main").log(Level.SEVERE, e.getMessage());
		} finally {
			statement = null;
		}
		return result;
	}

	public Transaction getTransaction(Integer trxnID)
	{
		Transaction trxn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			// Statements allow to issue SQL queries to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from z_yan.transaction where ID = " + trxnID);
			while (resultSet.next()) {
				trxn = new Transaction();
				trxn.setId(resultSet.getInt("ID"));
				trxn.setNameOnCard(resultSet.getString("NameOnCard"));
				trxn.setCreditCardType(resultSet.getInt("CreditCardType"));
				trxn.setCreditCardTypeName(resultSet.getString("CreditCardTypeName"));
				trxn.setCardNumber(resultSet.getString("CardNumber"));		
				trxn.setUnitPrice(resultSet.getDouble("UnitPrice"));
				trxn.setQuantity(resultSet.getInt("Quantity"));
				trxn.setTotalPrice(resultSet.getDouble("TotalPrice"));
				trxn.setExpDate(resultSet.getString("ExpDate"));
				trxn.setCreatedOn(resultSet.getString("CreatedOn"));
				trxn.setCreatedBy(resultSet.getString("CreatedBy"));
//				trxn.setPrefix(resultSet.getInt("Prefix"));
//				trxn.setCreditCardNumber(resultSet.getString("CreditCardNumber"));
//				trxn.setCreditCardExpire(resultSet.getString("CreditCardExpire"));
				
			}
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}
			Gson gson = new Gson();
			String results = gson.toJson(trxn);
			Logger.getLogger("Main").log(Level.INFO, "get:" + results);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger("Main").log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger("Main").log(Level.SEVERE, e.getMessage());
		} finally {
			statement = null;
			resultSet = null;
		}
		return trxn;
	}

	public void closeConnection()
	{
		if(connection != null)
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



}
