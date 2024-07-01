package vn.devpro.fashionstoremanagement.sale;

public class ProductInOrder {
	private  int id;
	private CartProduct cartProduct;

	public void dispay() {
		cartProduct.display();
	}
	
	public ProductInOrder() {
		super();
	}
	
	public ProductInOrder(int id, CartProduct cartProduct) {
		super();
		this.id = id;
		this.cartProduct = cartProduct;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public CartProduct getCartProduct() {
		return cartProduct;
	}
	
	public void setCartProducts(CartProduct cartProduct) {
		this.cartProduct = cartProduct;
	}	
}
