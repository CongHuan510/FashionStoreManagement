package vn.devpro.fashionstoremanagement.update.category;

import java.util.Scanner;

public class Category { // Chủng loại
	private int id;
	private String code;
	private String name;

	static Scanner sc = new Scanner(System.in);
	
	//Phương thức hiển thị chủng loại
	public void display() {
		System.out.printf("%5d %-20s %-30s%n", this.id, this.code, this.name);
	}

	// Phương thức sửa thông tin chủng loại
	public void edit() {

		do {
			System.out.println("\n===============Sửa thông tin chủng loại===============");
			System.out.println("Chọn một chức năng sửa");
			System.out.println("\t1. Sửa code");
			System.out.println("\t2. Sửa tên chủng loại");
			System.out.println("\t0. Thoát");
			System.out.print("Nhập lựa chọn của bạn: ");
			int choose = Integer.parseInt(sc.nextLine());

			switch (choose) {
			case 1:
				editCode();
				break;
			case 2:
				editName();
				break;
			case 0:
				return;
			default:
				System.out.println("Không hợp lệ, Chọn lại !");
				break;
			}
		} while (true);
	}
	
	private void editCode() {
		System.out.print("\tNhập code mới: ");
		String code = sc.nextLine();
		//Kiểm tra tính hợp lệ
		if (code.isEmpty()) {
			System.out.println("\tCode không được để trống");
			return;
		}
		//Kiểm tra code đã tồn tại trong DS hay chưa?
		if (CategoryManagement.findByCode(code) != -1) {
			System.out.println("\tCode '" + code + "' đã có  trong danh sách");
			return;
		}
		this.setCode(code);
		
	}
	
	private void editName() {
		System.out.print("\tNhập tên chủng loại mới: ");
		String name = sc.nextLine();
		//Kiểm tra tính hợp lệ
		if (name.isEmpty()) {
			System.out.println("\tTên không được để trống");
			return;
		}
		//Kiểm tra code đã tồn tại trong DS hay chưa?
		if (CategoryManagement.findByName(name) != -1) {
			System.out.println("\tTên '" + name + "' đã có  trong danh sách");
			return;
		}
		this.setName(name);
		
	}

	public Category() {
		super();
	}

	public Category(int id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

}
