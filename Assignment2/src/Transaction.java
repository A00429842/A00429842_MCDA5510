import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

import com.google.gson.Gson;

import java.time.LocalDateTime;    
public class Transaction {
	
	private Integer id;

	private String nameOnCard;
	
	private String cardNumber;
	
	private Double unitPrice;
	
	private Integer quantity;
	
	private Double totalPrice;
	
	private String expDate;
	
	private String createdOn;
	
	private String createdBy;
	
	private Integer creditCardType;
	
	private Integer prefix;
	
	private String creditCardNumber;
	
	private String creditCardExpire;
	
	private String CreditCardTypeName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
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
	public Integer getPrefix() {
		return prefix;
	}
	public void setPrefix(Integer prefix) {
		this.prefix = prefix;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getCreditCardExpire() {
		return creditCardExpire;
	}
	public void setCreditCardExpire(String creditCardExpire) {
		this.creditCardExpire = creditCardExpire;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}	
	
//'0:Unknown,1:MasterCard,2:Visa,3.American Express
	public Integer getCreditCardType() {
//	    Hashtable<Integer, String> cardTypes
//	     = new Hashtable<Integer, String>();
//	    cardTypes.put(1, "MasterCard");
//	    cardTypes.put(2, "Visa");
//	    cardTypes.put(3, "American Express");
//		return cardTypes.get(creditCardType);
		return creditCardType;
	}
	public void setCreditCardType(Integer creditCardType) {
		this.creditCardType = creditCardType;
	}
	public String getCreditCardTypeName() {
	    Hashtable<Integer, String> cardTypes
	     = new Hashtable<Integer, String>();
	    cardTypes.put(1, "MasterCard");
	    cardTypes.put(2, "Visa");
	    cardTypes.put(3, "American Express");
		return cardTypes.get(creditCardType);
	}
	public void setCreditCardTypeName(String creditCardTypeName) {
		CreditCardTypeName = creditCardTypeName;
	}
	
	
}
