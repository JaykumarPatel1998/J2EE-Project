package mvcdemo.model;

public class Registration {
    private int registrationId;
    private int userId;
    private int productId;
    private String serialNumber;
    private String purchaseDate;
    
	public Registration(int registrationId, int userId, int productId, String serialNumber, String purchaseDate) {
		super();
		this.registrationId = registrationId;
		this.userId = userId;
		this.productId = productId;
		this.serialNumber = serialNumber;
		this.purchaseDate = purchaseDate;
	}
	
	public int getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

    
}
