package vn.devpro.fashionstoremanagement.sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vn.devpro.fashionstoremanagement.update.customer.Customer;
import vn.devpro.fashionstoremanagement.update.customer.CustomerManagement;
import vn.devpro.fashionstoremanagement.update.product.ProductManagement;

public class CartManagement {
	public static int autoProductInOrderId = 1;
	public static int autoIdCard = 1;
	public static int autoIdOrder = 1;

	private static Cart cart = new Cart();
	private static Order order = new Order();
	private static ProductInOrder productInOrder = new ProductInOrder();
	private static List<Order> listOrder = new ArrayList<Order>();
	private static List<ProductInOrder> listProductInOrder = new ArrayList<ProductInOrder>();
	private static List<Cart> listCart = new ArrayList<Cart>();

	static Scanner sc = new Scanner(System.in);

	public static void execute() {

		do {
			System.out.println("\n===============QUẢN LÝ GIỎ HÀNG===============");
			System.out.println("Chọn một chức năng quản lý");
			System.out.println("\t1. Hiển thị giỏ hàng");
			System.out.println("\t2. Thêm sản phẩm vào giỏ hàng");
			System.out.println("\t3. Thay đổi số lượng sản phẩm trong giỏ hàng");
			System.out.println("\t4. Xóa sản phẩm trong giỏ hàng");
			System.out.println("\t5. Hủy giỏ hàng");
			System.out.println("\t6. Tạo hóa đơn");
			System.out.println("\t7. Thanh toán");
			System.out.println("\t0. Quay lại");
			System.out.print("Nhập lựa chọn của bạn: ");
			int choose = Integer.parseInt(sc.nextLine());

			switch (choose) {
			case 1:
				System.out.println("\n\t\t----------GIỎ HÀNG CỦA BẠN----------");
				if (cart.getCartProducts().size() <= 0) {
					System.out.println("\tKhông có sản phẩm nào trong giỏ hàng");
				} else {
					cart.display();
				}
				break;
			case 2:
				addToCart();
				break;
			case 3:
				changeProductQuantity();
				break;
			case 4:
				deleteCartProduct();
				break;
			case 5:
				cart = new Cart();
				break;
			case 6:
				createOrder();
				break;
			case 7:
				payment();
				break;
			case 0:
				return;
			default:
				System.out.println("Không hợp lệ, Chọn lại !");
				break;
			}
		} while (true);
	}

	private static void addToCart() {
		System.out.println("\n----------Thêm sản phẩm vào giỏ hàng----------");
		System.out.print("\tNhập vào id sản phẩm cần mua: ");
		int productId = Integer.parseInt(sc.nextLine());
		// Kiểm tra cái sản phẩm này có trong DS sản phẩm không
		int productIndex = ProductManagement.findById(productId);
		if (productIndex == -1) {
			System.out.println("\tSản phẩm này không có trong danh sách sản phẩm");
			return;
		}
		// Có thì nhập số lượng
		System.out.print("\tNhập số lượng cần mua: ");
		int quantity = Integer.parseInt(sc.nextLine());
		if (quantity <= 0) {
			System.out.println("\tSố lượng không hợp lệ");
			return;
		}
		// Cập nhật sản phẩm vào giỏ hàng: có 2 trường hợp
		int cartProductIndex = cart.findCartProductById(productId);
		// Tính tổng số lượng hàng dự kiến mua
		if (cartProductIndex != -1) { // Sản phẩm có trong giỏ
			// Tổng của số lượng mới nhập và số lượng đã có
			quantity += cart.getCartProducts().get(cartProductIndex).getQuantity();
		}
		// Cập nhật giỏ hàng
		// + TH1: Sản phẩm chưa có trong giỏ hàng -> thêm mới
		if (cartProductIndex == -1) {
			cart.getCartProducts().add(new CartProduct(productId, quantity));
		}
		// + TH2: Sản phẩm có trong giỏ hàng -> Tăng số lượng
		else {
			cart.getCartProducts().get(cartProductIndex).setQuantity(quantity);
		}
		System.out.println("\tThêm sản phẩm mới thành công!");
	}

	private static void changeProductQuantity() {
		System.out.println("\n----------Thay đổi sản phẩm số lượng trong giỏ hàng----------");
		System.out.print("\tNhập vào id sản phẩm cần thay đổi: ");
		int productId = Integer.parseInt(sc.nextLine());
		// Kiểm tra cái sản phẩm này có trong giỏ hàng không
		int cartProductIndex = cart.findCartProductById(productId);
		if (cartProductIndex == -1) {
			System.out.println("\tSản phẩm không có trong giỏ hàng");
			return;
		}
		// Có thì nhập số lượng
		System.out.print("\tNhập số lượng cần thêm(+)/bớt(-): ");
		int quantity = Integer.parseInt(sc.nextLine());
		quantity += cart.getCartProducts().get(cartProductIndex).getQuantity();
		if (quantity <= 0) {
			System.out.println("\tSố lượng không hợp lệ");
			return;
		}
		// Tính số lượng sau khi thêm hoặc bớt
		cart.getCartProducts().get(cartProductIndex).setQuantity(quantity);
		System.out.println("\tThêm số lượng thành công!");
	}

	private static void deleteCartProduct() {
		System.out.println("\n----------Xóa sản phẩm trong giỏ hàng----------");
		System.out.print("\tNhập vào id sản phẩm cần xóa: ");
		int productId = Integer.parseInt(sc.nextLine());

		int index = cart.findCartProductById(productId);
		if (index == -1) {
			System.out.println("\tSản phẩm không có trong giỏ hàng");
			return;
		}
		cart.getCartProducts().remove(index);
		System.out.println("\tXóa sản phẩm thành công!");
	}

	private static void createOrder() {
		System.out.print("\tNhập vào id khách hàng: ");
		int customerId = Integer.parseInt(sc.nextLine());
		// Check xem khách hàng đã có trong danh sách hay chưa
		int customerIndex = CustomerManagement.findById(customerId);
		String customerName;
		String customerMobile;
		String customerCode;
		if (customerIndex == -1) {
			do {
				System.out.println("\tNhập code sản phẩm: ");
				customerCode = sc.nextLine();
			} while (customerCode.isEmpty());
			do {
				System.out.println("\tNhập tên khách hàng: ");
				customerName = sc.nextLine();

			} while (customerName.isEmpty());
			do {
				System.out.println("\tNhập số điện thoại: ");
				customerMobile = sc.nextLine();
			} while (customerMobile.isEmpty());

			// Thêm khách hàng vào DS khách hàng
			customerId = CustomerManagement.autoId++;
			Customer customer = new Customer(customerId, customerCode, customerName, customerMobile);
			CustomerManagement.getCustomers().add(customer);
		} else { // Khách đã có trong danh sách
			customerCode = CustomerManagement.getCustomers().get(customerIndex).getCode();
			customerName = CustomerManagement.getCustomers().get(customerIndex).getName();
			customerMobile = CustomerManagement.getCustomers().get(customerIndex).getMobile();
		}
		// Tạo hóa đơn
		order.setId(autoIdOrder);
		order.setCustomerId(customerId);
		order.setTotalMoney(cart.totalCartMoney());
		// Tạo 1 cái giỏ hàng
		cart.setId(autoIdCard);
		cart.setCustomerId(customerId);

		// Bổ sung mã hóa đơn cho danh sách hàng hóa sau khi tạo 1 hóa đơn
		for (CartProduct item : cart.getCartProducts()) {
			item.setOrderId(autoIdOrder);
		}
		// Thêm hóa đơn vào trong danh sách hóa đơn
		listOrder.add(order);
		// Thêm 1 giỏ hàng vào danh sách giỏ hàng
		listCart.add(cart);
		System.out.println("\n\t\t----------CHI TIẾT HÓA ĐƠN----------");
		// Hiển thị thông tin hóa đơn
		order.display();
		// Hiển thị thông tin những đơn hàng trong hóa đơn
		cart.display();
		autoIdOrder++;
		autoIdCard++;
	}

	private static void payment() {
		System.out.println("\n\t\t----------THANH TOÁN HÓA ĐƠN----------");
		// set cho tung chi tiet san pham
		for (CartProduct item : cart.getCartProducts()) {
			productInOrder.setId(autoProductInOrderId++);
			productInOrder.setCartProducts(item);
			listProductInOrder.add(productInOrder);
			productInOrder = new ProductInOrder();
		}
		// Cap nhat chi tiet thong tin cua tung san pham trong hoa don 
		order.setProductInOrder(listProductInOrder);

		// Hiển thị ra chi tiết đơn hàng lần nữa 
		order.display();
		cart.display();

		System.out.println(" Cảm ơn bạn đã thanh toán!");
		// Khởi tạo lại giỏ hàng , hóa đơn và chi tiết hóa đơn
		cart = new Cart();
		order = new Order();
	}

	public static List<Order> getListOrder() {
		return listOrder;
	}

	public static void setListOrder(List<Order> listOrder) {
		CartManagement.listOrder = listOrder;
	}

	public static List<Cart> getListCart() {
		return listCart;
	}
}
