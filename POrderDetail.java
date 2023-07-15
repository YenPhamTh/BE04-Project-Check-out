package project.model;

public class POrderDetail {
	private int orderId;
	private int productId;
//	private double price;
	public POrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public POrderDetail(int orderId, int productId) {
		super();
		this.orderId = orderId;
		this.productId = productId;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}	
	
}
