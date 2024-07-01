package vn.devpro.fashionstoremanagement.update.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class CategoryManagement {

	public static int autoId = 1;
	public static ArrayList<Category> categories = new ArrayList<Category>();
	static Scanner sc = new Scanner(System.in);

	public static void init() {
		categories.add(new Category(autoId++, "CL0001", "Quần áo"));
		categories.add(new Category(autoId++, "CL0002", "Váy đầm"));
		categories.add(new Category(autoId++, "CL0003", "Giày dép"));
	}

	public static void execute() {
		do {
			System.out.println("\n===============CẬP NHẬT THÔNG TIN CHỦNG LOẠI===============");

			System.out.println("Chọn một chức năng cập nhật");
			System.out.println("\t1. Hiển thị danh sách chủng loại");
			System.out.println("\t2. Thêm mới chủng loại");
			System.out.println("\t3. Sửa thông tin chủng loại");
			System.out.println("\t4. Xóa thông tin chủng loại");
			System.out.println("\t5. Sắp xếp danh sách");
			System.out.println("\t0. Thoát");
			System.out.print("Nhập lựa chọn của bạn: ");
			int choose = Integer.parseInt(sc.nextLine());

			switch (choose) {
			case 1:
				display(); // Hiển thị danh sách chủng loại
				break;
			case 2:
				add(); // Thêm mới chủng loại
				break;
			case 3:
				edit(); // Sửa thông tin chủng loại
				break;
			case 4:
				remove(); // Xóa thông tin chủng loại
				break;
			case 5:
				sort(); // Sắp xếp danh sách theo tên chủng loại
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
		System.out.println("\n----------DANH SÁCH CHỦNG LOẠI----------");
		System.out.printf("%5s %-20s %-30s%n", "Ma CL", "Code", "Tên chủng loại");
		for (Category category : categories) {
			category.display();
		}
	}

	private static void add() {
		System.out.println("\n----------Thêm chủng loại mới vào danh sách----------");
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
		System.out.print("\tNhập tên chủng loại mới: ");
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
		// Thêm chủng loại vào danh sách
		Category newCategory = new Category(autoId++, code, name);
		categories.add(newCategory);
		System.out.println("\tThêm mới chủng loại thành công!");
	}

	private static void edit() {
		System.out.println("\n----------Sửa chủng loại trong danh sách----------");
		System.out.print("\tNhập mã chủng loại muốn sửa: ");
		int id = Integer.parseInt(sc.nextLine());
		// Tìm xem có trong DS không?
		int index = findById(id);
		if (index == -1) {
			System.out.println("\tChủng loại không có trong danh sách");
			return;
		}
		categories.get(index).edit();
		System.out.println("\tSửa thông tin chủng loại thành công!");
	}

	private static void remove() {
		System.out.println("\n----------Xóa chủng loại trong danh sách----------");
		System.out.print("\tNhập mã chủng loại muốn xóa: ");
		int id = Integer.parseInt(sc.nextLine());
		// Tìm xem có trong DS không?
		int index = findById(id);
		if (index == -1) {
			System.out.println("\tChủng loại không có trong danh sách");
			return;
		}
		// Xóa khỏi danh sách
		categories.remove(index);
		System.out.println("\tXóa chủng loại thành công!");
	}

	private static void sort() {
		Collections.sort(categories, new Comparator<Category>() {
			@Override
			public int compare(Category o1, Category o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
	}

	// Hàm tìm kiếm code đã tồn tại hay chưa
	public static int findByCode(String code) {
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getCode().equalsIgnoreCase(code)) {
				return i;
			}
		}
		return -1;
	}

	// Hàm tìm kiếm tên chủng loại đã tồn tại hay chưa
	public static int findByName(String name) {
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}

	// Hàm tìm kiếm mã chủng loại đã tồn tại hay chưa
	public static int findById(int id) {
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}

	// Tìm theo id trả về đối tương (Name)
	public static Category getCategoryById(int id) {
		for (Category category : categories) {
			if (category.getId() == id) {
				return category;
			}
		}
		return null;
	}
}
