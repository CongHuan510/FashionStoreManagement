package vn.devpro.fashionstoremanagement;

import java.util.Scanner;

import vn.devpro.fashionstoremanagement.sale.CartManagement;
import vn.devpro.fashionstoremanagement.statistical.OrderManagement;
import vn.devpro.fashionstoremanagement.update.UpdateManagement;
import vn.devpro.fashionstoremanagement.update.category.CategoryManagement;
import vn.devpro.fashionstoremanagement.update.customer.CustomerManagement;
import vn.devpro.fashionstoremanagement.update.product.ProductManagement;

public class FashionStoreManagement {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		CategoryManagement.init();
		CustomerManagement.init();
		ProductManagement.init();
		do {
			System.out.println("\n===============CHƯƠNG TRÌNH QUẢN LÝ BÁN HÀNG===============");

			System.out.println("Chọn một chức năng quản lý");
			System.out.println("\t1. Cập nhật thông tin của hàng thời trang");
			System.out.println("\t2. Quản lý bán hàng");
			System.out.println("\t3. Thống kế số liệu");
			System.out.println("\t0. Thoát");
			System.out.print("Nhập lựa chọn của bạn: ");
			int choose = Integer.parseInt(sc.nextLine());

			switch (choose) {
			case 1:
				UpdateManagement.execute();
				break; // Cập nhật thông tin hệ thống
			case 2:
				CartManagement.execute();
				break; // Quản lý phiên giao dịch của khách hàng (quản lý giỏ hàng, tạo đơn hàng)
			case 3:
				OrderManagement.execute();
				break; // Quản lý đơn hàng và doanh thu
			case 0:
				System.exit(0);
			default:
				System.out.println("Không hợp lệ, Chọn lại !");
				break;
			}
		} while (true);
	}
}
