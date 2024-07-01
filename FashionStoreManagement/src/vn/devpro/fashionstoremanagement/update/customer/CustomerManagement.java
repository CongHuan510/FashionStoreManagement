package vn.devpro.fashionstoremanagement.update.customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class CustomerManagement {

	public static int autoId = 1;
	public static ArrayList<Customer> customers = new ArrayList<Customer>();

	public static void init() {
		customers.add(new Customer(autoId++, "KH001", "Trần Huân", "0961204233"));
		customers.add(new Customer(autoId++, "KH002", "Trần Hùng", "0961204233"));
		customers.add(new Customer(autoId++, "KH003", "Trần Hưng", "0961204233"));
	}

	static Scanner sc = new Scanner(System.in);

	public static void execute() {

		do {
			System.out.println("\n===============CẬP NHẬT THÔNG TIN KHÁCH HÀNG===============");

			System.out.println("Chọn một chức năng cập nhật");
			System.out.println("\t1. Hiển thị danh sách khách hàng");
			System.out.println("\t2. Thêm mới khách hàng");
			System.out.println("\t3. Sửa thông khách hàng");
			System.out.println("\t4. Xóa thông tin khách hàng");
			System.out.println("\t5. Sắp xếp danh sách");
			System.out.println("\t0. Thoát");
			System.out.print("Nhập lựa chọn của bạn: ");
			int choose = Integer.parseInt(sc.nextLine());

			switch (choose) {
			case 1:
				display(); // Hiển thị danh sách khách hàng
				break;
			case 2:
				add(); // Thêm mới khách hàng
				break;
			case 3:
				edit(); // Sửa thông tin khách hàng
				break;
			case 4:
				remove(); // Xóa thông tin khách hàng
				break;
			case 5:
				sort(); // Sắp xếp danh sách theo tên
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
		System.out.println("\n----------Danh sách khách hàng----------");
		System.out.printf("%5s %-20s %-30s %-15s%n", "Ma KH", "Code", "Tên khách hàng", "Số điện thoại");
		for (Customer customer : customers) {
			customer.display();
		}
	}

	private static void add() {
		System.out.println("\n----------Thêm khách hàng mới vào danh sách----------");
		System.out.print("\tNhập code mới: ");
		String code = sc.nextLine();
		// Kiểm tra tính hợp lệ
		if (code.isEmpty()) {
			System.out.println("\tCode không được để trống");
			return;
		}
		// Kiểm tra code đã tồn tại trong DS hay chưa?
		if (findByCode(code) != -1) {
			System.out.println("\tCode '" + code + "' đã có  trong danh sách");
			return;
		}
		System.out.print("\tNhập tên khách hàng mới: ");
		String name = sc.nextLine();
		// Kiểm tra tính hợp lệ
		if (name.isEmpty()) {
			System.out.println("\tTên không được để trống");
			return;
		}
		// Kiểm tra code đã tồn tại trong DS hay chưa?
		if (findByName(name) != -1) {
			System.out.println("\tTên '" + name + "' đã có  trong danh sách");
			return;
		}
		System.out.print("\tNhập số điện thoại mới: ");
		String mobile = sc.nextLine();
		// Kiểm tra tính hợp lệ
		if (mobile.isEmpty()) {
			System.out.println("\tSố điện thoại không được để trống");
			return;
		}
		// Kiểm tra code đã tồn tại trong DS hay chưa?
		if (findByMobile(mobile) != -1) {
			System.out.println("\tSố điện thoại '" + mobile + "' đã có  trong danh sách");
			return;
		}
		// Thêm khách hàng vào danh sách
		Customer newCustomer = new Customer(autoId++, code, name, mobile);
		customers.add(newCustomer);
		System.out.println("\tThêm khách hàng mới thành công!");
	}

	private static void edit() {
		System.out.println("\n----------Sửa khách hàng trong danh sách----------");
		System.out.print("\tNhập mã khách hàng muốn sửa: ");
		int id = Integer.parseInt(sc.nextLine());
		// Tìm xem có trong ds không
		int index = findById(id);
		if (index == -1) {
			System.out.println("\tkhách hàng không có trong danh sách");
			return;
		}
		// Sửa thông tin khách hàng
		customers.get(index).edit();
		System.out.println("\tSửa thông tin khách hàng thành công!");
	}

	private static void remove() {
		System.out.println("\n----------Sửa khách hàng trong danh sách----------");
		System.out.print("\tNhập mã khách hàng muốn sửa: ");
		int id = Integer.parseInt(sc.nextLine());
		// Tìm xem có trong ds không
		int index = findById(id);
		if (index == -1) {
			System.out.println("\tkhách hàng không có trong danh sách");
			return;
		}
		// Xóa khách hàng khỏi danh sách
		customers.remove(index);
		System.out.println("\tXóa khách hàng thành công!");
	}

	private static void sort() {
		Collections.sort(customers, new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});

	}

	// Hàm tìm kiếm tên khách hàng đã tồn tại hay chưa
	public static int findByMobile(String mobile) {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getName().equalsIgnoreCase(mobile)) {
				return i;
			}
		}
		return -1;
	}

	// Hàm tìm kiếm tên khách hàng đã tồn tại hay chưa
	public static int findByName(String name) {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}

	// Hàm tìm kiếm tên khách hàng đã tồn tại hay chưa
	public static int findByCode(String code) {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getName().equalsIgnoreCase(code)) {
				return i;
			}
		}
		return -1;
	}

	// Hàm tìm kiếm mã khách hàng đã tồn tại hay chưa
	public static int findById(int id) {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}

	// Tìm theo id trả về đối tương (Name)
	public static Customer getCustomerById(int id) {
		for (Customer customer : customers) {
			if (customer.getId() == id) {
				return customer;
			}
		}
		return null;
	}
	//Tìm theo id trả về đối tương (Mobile)
	public static String geCustomerByMobile(int id) {
		for (Customer customer : customers) {
			if (customer.getId() == id) {
				return customer.getMobile();
			}
		}
		return null;
	}

	public static ArrayList<Customer> getCustomers() {
		return customers;
	}

	public static void setCustomers(ArrayList<Customer> customers) {
		CustomerManagement.customers = customers;
	}
	
	
}
