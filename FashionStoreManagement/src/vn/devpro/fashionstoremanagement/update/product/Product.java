package vn.devpro.fashionstoremanagement.update.product;

import java.util.Scanner;

import vn.devpro.fashionstoremanagement.update.category.Category;
import vn.devpro.fashionstoremanagement.update.category.CategoryManagement;

public class Product {
	private int id;
	private int categoryId;
	private String code;
	private String name;
	private double price;
	
	static Scanner scanner = new Scanner(System.in);

	//Phương thức hiển thị sản phẩm
	public void display() {
		Category category = CategoryManagement.getCategoryById(this.categoryId);
		System.out.printf("%5d %-30s %-20s %-30s %,15.2f%n",
				this.id, category.getName(), this.code,
				this.name, this.price);
	}
	
	//Phương thức sửa thông tin sản phẩm
	public void edit() {
		do {
			System.out.println("\n===============Sửa thông tin sản phẩm===============");
			System.out.println("Chọn một chức năng sửa");
			System.out.println("\t1. Sửa chủng loại");
			System.out.println("\t2. Sửa code sp");
			System.out.println("\t3. Sửa tên sản phẩm");
			System.out.println("\t4. Sửa đơn giá sản phẩm");
			System.out.println("\t0. Thoát");
			System.out.print("Nhập lựa chọn của bạn: ");
			int choose = Integer.parseInt(scanner.nextLine());

			switch (choose) {
			case 1:
				editCategoryId(); // sửa chủng loại
				break;
			case 2:
				editCode(); // Sửa code sản phẩm
				break;
			case 3:
				editName(); // Sửa tên sản phẩm
				break;
			case 4:
				editPrice(); // Sửa đơn giá
				break;
			case 0:
				return;
			default:
				System.out.println("Không hợp lệ, Chọn lại !");
				break;
			}
		} while (true);
	}
	
	private void editCategoryId() {
		System.out.print("\tNhập mã chủng loại: ");
		int categoryId = Integer.parseInt(scanner.nextLine());
		// Kiểm tra cái categoryId có trong Ds loại hàng hay không?
		if (CategoryManagement.findById(categoryId) == -1) {
			System.out.println("\tChủng loại không tồn tại");
			return;
		}
		this.setCategoryId(categoryId);
	}

	private void editCode() {
		System.out.print("\tNhập code mới: ");
		String code = scanner.nextLine();

		// Kiểm tra tính hợp lệ
		if (code.isEmpty()) {
			System.out.println("\tCode không được để trống");
			return;
		}
		// Kiểm tra tên đã tồn tại trong DS hay chưa?
		if (ProductManagement.findByCode(code) != -1) {
			System.out.println("\tCode'" + code + "'đã có trong danh sách");
			return;
		}
		this.setCode(code);
	}

	private void editName() {
		System.out.print("\tNhập tên sản phẩm mới: ");
		String name = scanner.nextLine();

		// Kiểm tra tính hợp lệ
		if (name.isEmpty()) {
			System.out.println("\tTên không được để trống");
			return;
		}
		// Kiểm tra tên đã tồn tại trong DS hay chưa?
		if (ProductManagement.findByName(name) != -1) {
			System.out.println("\tTên '" + name + "'đã có trong danh sách");
			return;
		}
		this.setName(name);
	}

	private void editPrice() {
		System.out.print("\tNhập đơn giá: ");
		double price = Double.parseDouble(scanner.nextLine());
		if (price < 0) {
			System.out.println("\tĐơn giá không được là số âm");
			return;
		}
		this.setPrice(price);
		
	}

	public Product() {
		super();
	}

	public Product(int id, int categoryId, String code, String name, double price) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static Scanner getScanner() {
		return scanner;
	}

	public static void setScanner(Scanner scanner) {
		Product.scanner = scanner;
	}

	
	
}
