package vn.devpro.fashionstoremanagement.sale;

import java.util.ArrayList;
import java.util.List;

import vn.devpro.fashionstoremanagement.update.customer.Customer;
import vn.devpro.fashionstoremanagement.update.customer.CustomerManagement;

public class Order {
	
	private int id;
	private int customerId;
	private double totalMoney;
	private List<ProductInOrder> productInOrder = new ArrayList<ProductInOrder>();
	
	public void displayOrdered() {
		System.out.println("\tMã hóa đơn : " + this.id);
		String customerName = "";
		String customerMobile = "";
		Customer customer = CustomerManagement.getCustomerById(this.customerId);
		if (customer != null) {
			customerName = customer.getName();
			customerMobile = customer.getMobile();
		}
		System.out.println("\tTên khách hàng: " + customerName);
		System.out.println("\tSố điện thoại: " + customerMobile); 
		if(productInOrder.size() > 0) {
			System.out.println("Danh sách sản phẩm");
			System.out.printf("%-30s %-16s %-12s %-15s%n",
					"Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền");
			for(ProductInOrder item : productInOrder) {
				if(item.getCartProduct().getOrderId() == id) {
					item.dispay();
				}
			}
			System.out.printf("\t\tTổng thành tiền: %,.2f\n", this.totalMoney );
		}
	}
	
	public void display() {
		System.out.println("\tMã hóa đơn : " + this.id);
		String customerName = "";
		String customerMobile = "";
		Customer customer = CustomerManagement.getCustomerById(this.customerId);
		if (customer != null) {
			customerName = customer.getName();
			customerMobile = customer.getMobile();
		}
		System.out.println("\tTên khách hàng: " + customerName);
		System.out.println("\tSố điện thoại: " + customerMobile); 
	}
	
	public String getCustomerName() {
	    Customer customer = CustomerManagement.getCustomerById(this.customerId);
	    return (customer != null) ? customer.getName() : "";
	}

	
	public Order() {
		super();
	}

	public Order(int id, int customerId, double totalMoney, List<ProductInOrder> productInOrder) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.totalMoney = totalMoney;
		this.productInOrder = productInOrder;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public List<ProductInOrder> getProductInOrder() {
		return productInOrder;
	}

	public void setProductInOrder(List<ProductInOrder> productInOrder) {
		this.productInOrder = productInOrder;
	} 
}
