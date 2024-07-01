package vn.devpro.fashionstoremanagement.update.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import vn.devpro.fashionstoremanagement.update.category.CategoryManagement;

public class ProductManagement {

	public static int autoId = 1;
	public static ArrayList<Product> products = new ArrayList<Product>();
	static Scanner sc = new Scanner(System.in);

	public static void init() {
		products.add(new Product(autoId++, 1, "SP001", "Áo phông cổ tròn", 120000));
		products.add(new Product(autoId++, 2, "SP002", "Váy maxi", 500000));
		products.add(new Product(autoId++, 1, "SP003", "Áo phông cổ tym", 200000));
		products.add(new Product(autoId++, 2, "SP004", "Váy dự tiệc", 1200000));
		products.add(new Product(autoId++, 3, "SP005", "Giày thể thao ", 5000000));
		products.add(new Product(autoId++, 1, "SP006", "Thương hiệp áo phông", 200000));
	}

	public static void execute() {

		do {
			System.out.println("\n===============CẬP NHẬT THÔNG TIN SẢN PHẨM===============");
			System.out.println("Chọn một chức năng cập nhật");
			System.out.println("\t1. Hiển thị danh sách sản phẩm");
			System.out.println("\t2. Thêm mới sản phẩm");
			System.out.println("\t3. Sửa thông tin sản phẩm");
			System.out.println("\t4. Xóa thông tin sản phẩm");
			System.out.println("\t5. Sắp xếp danh sách");
			System.out.println("\t6: Tìm kiếm sản phẩm theo chủng loại");
			System.out.println("\t7: Tìm kiếm theo (một phần) tên sản phẩm");
			System.out.println("\t0. Thoát");
			System.out.print("Nhập lựa chọn của bạn: ");
			int choose = Integer.parseInt(sc.nextLine());

			switch (choose) {
			case 1:
				display(); // Hiển thị danh sách hàng
				break;
			case 2:
				add(); // Thêm mới hàng
				break;
			case 3:
				edit(); // Sửa thông tin hàng
				break;
			case 4:
				remove(); // Xóa thông tin hàng
				break;
			case 5:
				sort(); // Sắp xếp danh sách theo tên
				break;
			case 6:
				findProductsByCategory(); // Tìm kiếm sản phẩm theo chủng loại
				break;
			case 7:
				findProductsByKeyWord(); // Tìm kiếm tên (một phần) theo sản phẩm
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
		System.out.println("\n----------DANH SÁCH SẢN PHẨM----------");
		System.out.printf("%5s %-30s %-20s %-38s %-15s%n", "Ma SP", "Tên chủng loại", "Code SP", "Tên sản phẩm",
				"Đơn giá");
		for (Product product : products) {
			product.display();
		}

	}

	private static void add() {
		System.out.println("\n----------Thêm sản phẩm mới vào danh sách----------");
		System.out.print("\tNhập mã chủng loại: ");
		int categoryId = Integer.parseInt(sc.nextLine());
		// Kiểm tra cái categoryId có trong Ds loại hàng hay không?
		if (CategoryManagement.findById(categoryId) == -1) {
			System.out.println("\tChủng loại không tồn tại");
			return;
		}
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
		System.out.print("\tNhập tên sản phẩm mới: ");
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
		System.out.print("\tNhập đơn giá mới: ");
		double price = Double.parseDouble(sc.nextLine());
		if (price < 0) {
			System.out.println("\tĐơn giá không được là số âm");
			return;
		}
		// Thêm sản phẩm vào danh sách
		Product newProduct = new Product(autoId++, categoryId, code, name, price);
		products.add(newProduct);
		System.out.println("\tThêm mới sản phẩm thành công!");

	}

	private static void edit() {
		System.out.println("\n----------Sửa sản phẩm trong danh sách----------");
		System.out.print("\tNhập mã hàng muốn sửa: ");
		int id = Integer.parseInt(sc.nextLine());
		// Tìm xem có trong ds không
		int index = findById(id);
		if (index == -1) {
			System.out.println("\tSản phẩm không có trong danh sách");
			return;
		}
		products.get(index).edit();
		System.out.println("\tSửa thông tin sản phẩm thành công!");
	}

	private static void remove() {
		System.out.println("\n----------Xóa sản phẩm trong danh sách----------");
		System.out.print("\tNhập mã sản phẩm muốn xóa: ");
		int id = Integer.parseInt(sc.nextLine());
		// Tìm xem có trong ds không
		int index = findById(id);
		if (index == -1) {
			System.out.println("\tSản phẩm không có trong danh sách");
			return;
		}
		products.remove(index);
		System.out.println("\tXóa sản phẩm thành công");
	}

	private static void sort() {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});

	}

	private static void findProductsByCategory() {
		System.out.println("\n----------Tìm kiếm sản phẩm theo chủng loại---------");
		System.out.print("\tNhập mã chủng loại: ");
		int categoryId = Integer.parseInt(sc.nextLine());
		// Kiểm tra cái categoryId có trong Ds loại hàng hay không?
		if (CategoryManagement.findById(categoryId) == -1) {
			System.out.println("\tKhông có sản phẩm nào thuộc chủng loại này");
			return;
		}
		System.out.printf("%5s %-30s %-20s %-38s %-15s%n", "Ma SP", "Tên chủng loại", "Code SP", "Tên sản phẩm",
				"Đơn giá");
		for (Product product : products) {
			if (product.getCategoryId() == categoryId) {
				product.display();
			}
		}
	}

	private static void findProductsByKeyWord() {
		System.out.println("\n----------Tìm kiếm theo(một phần) tên sản phẩm---------");
		System.out.print("\tNhập (một phần) tên sản phẩm: ");
		String keyword = sc.nextLine();
		// Kiểm tra tính hợp lệ
		if (keyword.isEmpty()) {
			System.out.println("\tTên không được để trống");
			return;
		}
		if (findByKeyWord(keyword) == -1) {
			System.out.println("\tKhông có sản phẩm nào có tên là '" + keyword + "'");
			return;
		}
		System.out.printf("%5s %-30s %-20s %-38s %-15s%n", "Ma SP", "Tên chủng loại", "Code SP", "Tên sản phẩm",
				"Đơn giá");
		for (Product product : products) {
			if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
				product.display();
			}
		}
	}

	// Hàm tìm kiếm code đã tồn tại hay chưa
	public static int findByCode(String code) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getCode().equalsIgnoreCase(code)) {
				return i;
			}
		}
		return -1;
	}

	// Hàm tìm kiếm tên chủng loại đã tồn tại hay chưa
	public static int findByName(String name) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}

	// Hàm tìm kiếm mã chủng loại đã tồn tại hay chưa
	public static int findById(int id) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}

	// Tìm kiếm keyword có trong tồn tại trong danh sách hay khống
	public static int findByKeyWord(String keyword) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getName().toLowerCase().contains(keyword.toLowerCase())) {
				return i;
			}
		}
		return -1;
	}

	// Tìm theo id trả về đối tương (Name)
	public static Product getProductById(int id) {
		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
	}
}
