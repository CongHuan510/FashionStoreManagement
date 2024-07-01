package vn.devpro.fashionstoremanagement.sale;

import vn.devpro.fashionstoremanagement.update.product.Product;
import vn.devpro.fashionstoremanagement.update.product.ProductManagement;

public class CartProduct {
	private int productId;
	private int quantity; //Số lượng khách mua
	private int orderId;
	
	//Phương thức hiển thị
	public void display() {
		Product product = ProductManagement.getProductById(this.productId);
		System.out.printf("%-30s %,8d %,15.2f %,15.2f%n",
				product.getName(), this.quantity, product.getPrice(), this.total());
	}
	
	//Phương thức tính thành tiền
	public double total() {
		Product product = ProductManagement.getProductById(this.productId);
		return this.quantity * product.getPrice();
	}

	public CartProduct() {
		super();
	}

	public CartProduct(int productId, int quantity, int orderId) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.orderId = orderId;
	}
	
	public CartProduct(int productId, int quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	
}
