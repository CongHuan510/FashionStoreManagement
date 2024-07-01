package vn.devpro.fashionstoremanagement.update;

import java.util.Scanner;

import vn.devpro.fashionstoremanagement.update.category.CategoryManagement;
import vn.devpro.fashionstoremanagement.update.customer.CustomerManagement;
import vn.devpro.fashionstoremanagement.update.product.ProductManagement;

public class UpdateManagement {
	static Scanner scanner = new Scanner(System.in);
	public static void execute() {
		do {
			System.out.println("\n===============CẬP NHẬT THÔNG TIN HỆ THỐNG===============");
			System.out.println("Chọn một chức năng cập nhật");
			System.out.println("\t1. Cập nhật thông tin chủng loại");
			System.out.println("\t2. Cập nhật thông tin sản phẩm");
			System.out.println("\t3. Cập nhật thông tin khách hàng");
			System.out.println("\t0. Thoát");
			System.out.print("Nhập lựa chọn của bạn: ");
			int choose = Integer.parseInt(scanner.nextLine());

			switch (choose) {
			case 1:
				CategoryManagement.execute();
				break; //Cap nhat thong tin chung loai
			case 2: 
				ProductManagement.execute();
				break; // Cap nhap thong tin san pham
			case 3:
				CustomerManagement.execute();
				break; // Cap nhap thong tin khach hang 
			case 0:
				return;
			default:
				System.out.println("Không hợp lệ, Chọn lại !");
				break;
			}
		} while (true);

	}
}
