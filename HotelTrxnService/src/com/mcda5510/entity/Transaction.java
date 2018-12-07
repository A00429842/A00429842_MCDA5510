package com.mcda5510.entity;

import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;   

public class Transaction {
	@Required
	private String id;
	@Required
	private String nameOnCard;
	@Required
	private String cardNumber;
	@Required
	private Double unitPrice;
	@Required
	private Integer quantity;
	private Double totalPrice;
	@Required
	private String expDate;
	private String createdOn;
	private String createdBy;
	@Required
	private Integer cardCategory;
	private String CreditCardTypeName;
	@Required
	private Integer ReservationId;
	@Required
	private Integer UserId;

	public Integer getReservationId() {
		return ReservationId;
	}
	public void setReservationId(Integer reservationId) {
		ReservationId = reservationId;
	}
	public Integer getUserId() {
		return UserId;
	}
	public void setUserId(Integer userId) {
		UserId = userId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getTotalPrice() {
		totalPrice = quantity * unitPrice;
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) throws Exception {
		boolean isCorrect = expDate.matches("(0[1-9]|1[0-2])/20(1[6-9]|2[0-9]|3[0-1])");
    	if(!isCorrect)
    	{
			Logger.getLogger("Main").log(Level.SEVERE, "exp date is not correct");
    		throw new Exception("exp date is not correct");
    	}
		this.expDate = expDate;
	}
	public String getCreatedOn() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
	    LocalDateTime now = LocalDateTime.now();  
	    createdOn = dtf.format(now);  
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		createdBy = System.getProperty("user.name");
		return createdBy;
	}
	public void setCreatedBy(String createBy) {
		this.createdBy = createBy;
	}

	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) throws Exception {
	    String regStr = "";
		if(cardCategory == 1)
		{
	    	regStr = "5[1-5][0-9]{14}";
		}else if(cardCategory == 2)
		{
	    	regStr = "4[0-9]{15}";

		}else if(cardCategory == 3)
		{
	    	regStr = "3(4|7)[0-9]{14}";
		}
		boolean isCorrect = cardNumber.matches(regStr);
		if(!isCorrect)
		{
			Logger.getLogger("Main").log(Level.SEVERE, "card number is not correct");
    		throw new Exception("card number is not correct");
		}

		this.cardNumber = cardNumber;
	}
	
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}	
	
	public Integer getCardCategory() {
		return cardCategory;
	}
	public void setCardCategory(Integer cardCategory) {
		this.cardCategory = cardCategory;
	}
	public String getCreditCardTypeName() {
	    Hashtable<Integer, String> cardTypes
	     = new Hashtable<Integer, String>();
	    cardTypes.put(1, "MasterCard");
	    cardTypes.put(2, "Visa");
	    cardTypes.put(3, "American Express");
		return cardTypes.get(cardCategory);
	}
	public void setCreditCardTypeName(String creditCardTypeName) {
		this.CreditCardTypeName = creditCardTypeName;
	}
	
	
}
