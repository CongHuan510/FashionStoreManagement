package vn.devpro.fashionstoremanagement.sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart {
	private int id;
	private int customerId;
	private String code;
	private List<CartProduct> cartProducts = new ArrayList<CartProduct>();

	static Scanner sc = new Scanner(System.in);
	//Phương thức hiển thị thông tin giỏ hàng
	public void display() {
		System.out.println("Danh sách sản phẩm");
		System.out.printf("%-30s %-16s %-12s %-15s%n",
				"Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền");
		for (CartProduct cartProduct : this.cartProducts) {
			cartProduct.display();
		}
		System.out.printf("\t\tTổng thành tiền: %,.2f", this.totalCartMoney());
	}
	
	//Phương thức tính tổng thành tiền
	public double totalCartMoney() {
		double total = 0;
		for (CartProduct cartProduct : cartProducts) {
			total += cartProduct.total();
		}
		return total;
	}
	
	//Tìm sản phẩm trong giỏ hàng theo id sản phẩm
	public int findCartProductById(int productId) {
		for (int i = 0; i < cartProducts.size(); i++) {
			if (cartProducts.get(i).getProductId() == productId) {
				return i;
			}
		}
		return -1;
	}
	
	public Cart() {
		super();
	}

	public Cart(int id, int customerId, String code, List<CartProduct> cartProducts) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.code = code;
		this.cartProducts = cartProducts;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
}
