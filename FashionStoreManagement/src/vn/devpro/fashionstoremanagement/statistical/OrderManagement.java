package vn.devpro.fashionstoremanagement.statistical;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import vn.devpro.fashionstoremanagement.sale.CartManagement;
import vn.devpro.fashionstoremanagement.sale.Order;
import vn.devpro.fashionstoremanagement.sale.ProductInOrder;
import vn.devpro.fashionstoremanagement.update.product.Product;
import vn.devpro.fashionstoremanagement.update.product.ProductManagement;

public class OrderManagement {

	private static List<Order> listOrder = CartManagement.getListOrder();
	static Scanner scanner = new Scanner(System.in);

	public static void execute() {
		do {
			System.out.println("\n===============QUẢN LÝ ĐƠN HÀNG VÀ DOANH THU===============");
			System.out.println("Chọn một chức năng cập nhật");
			System.out.println("\t1. Hiển thị danh sách các hóa đơn");
			System.out.println("\t2. Xóa một đơn hàng khỏi danh sách");
			System.out.println("\t3. Hiển thị tổng doanh thu có được từ tất cả các hóa đơn");
			System.out.println("\t4. Hiển thị tổng tiền thu được theo khách hàng");
			System.out.println("\t5. Hiển thị tổng tiền thu được theo sản phẩm đã bán");
			System.out.println("\t0. Thoát");
			System.out.print("Nhập lựa chọn của bạn: ");
			int choose = Integer.parseInt(scanner.nextLine());

			switch (choose) {
			case 1:
				display();
				break;
			case 2:
				removeAnOrder();
				break;
			case 3:
				displayTotalRevenue();
				break;
			case 4:
				displayTotalByCustomer();
				break;
			case 5:
				displayTotalByProduct();
				break;
			case 0:
				return;
			default:
				System.out.println("Không hợp lệ, Chọn lại !");
				break;
			}
		} while (true);
	}

	private static void display() {
		System.out.println("\n\t\t----------DANH SÁCH HÓA ĐƠN----------");
		for (Order order : listOrder) {
			order.displayOrdered();
		}
	}

	private static void removeAnOrder() {
		System.out.print("\tNhập vào mã đơn hàng muốn xóa: ");
		int id = Integer.parseInt(scanner.nextLine());
		int index = findByOrder(id);
		if (index == -1) {
			System.out.println("\tMã đơn hàng không hợp lệ");
			return;
		}
		// Xóa đơn hàng
		listOrder.remove(index);
		System.out.println("\tXóa đơn hàng thành công");
	}

	private static void displayTotalRevenue() {
		System.out.printf("\tTổng doanh thu của tất cả hóa đơn là: %,.2f", totalRevenue());
	}

	private static void displayTotalByCustomer() {
		System.out.println("\n\t\t----------TỔNG TIỀN THEO KHÁCH HÀNG----------");
		// Tạo danh sách lưu trữ id khách hàng và tổng thành tiền
		List<Integer> customerIds = new ArrayList<Integer>();
		List<Double> totalOfEachCustomer = new ArrayList<Double>();
		// Duyệt tất cả các khách hàng để tính tổng thành tiền
		for (int i = 0; i < listOrder.size(); i++) {
			int customerId = listOrder.get(i).getCustomerId();
			double total = listOrder.get(i).getTotalMoney();
			if (!customerIds.contains(customerId)) {// id khách hàng chưa có trong DS thêm nó
				customerIds.add(customerId);
				totalOfEachCustomer.add(total);
			} else { // đã có id thì cập nhật tiền
				int index = customerIds.indexOf(customerId);
				totalOfEachCustomer.set(index, total + totalOfEachCustomer.get(index));
			}
		}
		System.out.printf("%-13s %-30s %-15s%n", "Mã khách hàng", "Tên khách hàng", "Tổng thành tiền");
		for (int i = 0; i < customerIds.size(); i++) {
			System.out.printf("%13d %-30s %,15.2f%n", customerIds.get(i), listOrder.get(i).getCustomerName(), totalOfEachCustomer.get(i));
		}
	}

	private static void displayTotalByProduct() {
		System.out.println("\n\t\t----------TỔNG TIỀN THEO SẢN PHẨM----------");	
		// Lấy danh sách sản phẩm theo thứ tự đầu tiên
		List<ProductInOrder> allProducts = listOrder.get(0).getProductInOrder();
		// Tạo danh sách lưu trữ id sản phẩm và sl tương ứng
		List<Integer> productIds = new ArrayList<Integer>();
		List<Integer> quantities = new ArrayList<Integer>();
		// Duyệt tất cả các sản phẩm để tính số lượng
		for (int i = 0; i < allProducts.size(); i++) {
			int productId = allProducts.get(i).getCartProduct().getProductId();
			int quantity = allProducts.get(i).getCartProduct().getQuantity();
			if (!productIds.contains(productId)) {//id sản phẩm chưa có trong DS thêm nó
				productIds.add(productId);
				quantities.add(quantity);
			} else { // đã có id thì cập nhật số lượng
				int index = productIds.indexOf(productId);
				quantities.set(index, quantities.get(index) + quantity);
			}
		}
		System.out.printf("%-3s %-30s %-15s %-15s%n", "STT", "Tên sản phẩm",
				"Sl đã bán", "Doanh thu");
		for (int i = 0; i < productIds.size(); i++) {
			Product product = ProductManagement.getProductById(productIds.get(i));
			int sl = quantities.get(i);
			System.out.printf("%3d %-30s %,9d %,15.2f%n", (i + 1),
					product.getName(), sl, sl * product.getPrice());
		}
	}

	public static double totalRevenue() {
		double sum = 0;
		for (Order order : listOrder) {
			sum += order.getTotalMoney();
		}
		return sum;
	}

	public static int findByOrder(int id) {
		for (int i = 0; i < listOrder.size(); i++) {
			if (listOrder.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
}
