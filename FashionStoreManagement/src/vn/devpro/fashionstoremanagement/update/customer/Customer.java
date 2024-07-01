package vn.devpro.fashionstoremanagement.update.customer;

import java.util.Scanner;

public class Customer {
	private int id;
	private String code;
	private String name;
	private String mobile;
	
	static Scanner sc = new Scanner(System.in);
	//Phương thức hiển thị danh sách khách hàng
	public void display() {
		System.out.printf("%5d %-20s %-30s %-15s%n",
				this.id, this.code, this.name, this.mobile);
	}
	
	//Phương thức sửa thông tin khách hàng
	public void edit() {
		do {
			System.out.println("\n===============Sửa thông tin khách hàng===============");
			System.out.println("Chọn một chức năng sửa");
			System.out.println("\t1. Sửa code");
			System.out.println("\t2. Sửa tên khách hàng");
			System.out.println("\t3. Sửa số điện thoại");
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
			case 3:
				editMobile();
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
		if (CustomerManagement.findByCode(code) != -1) {
			System.out.println("\tCode '" + code + "' đã có  trong danh sách");
			return;
		}
		this.setCode(code);	
	}

	private void editName() {
		System.out.print("\tNhập tên khách hàng mới: ");
		String name = sc.nextLine();
		//Kiểm tra tính hợp lệ
		if (name.isEmpty()) {
			System.out.println("\tTên không được để trống");
			return;
		}
		//Kiểm tra code đã tồn tại trong DS hay chưa?
		if (CustomerManagement.findByName(name) != -1) {
			System.out.println("\tTên '" + name + "' đã có  trong danh sách");
			return;
		}
		this.setName(name);
	}

	private void editMobile() {
		System.out.print("\tNhập số điện thoại mới: ");
		String mobile = sc.nextLine();
		//Kiểm tra tính hợp lệ
		if (mobile.isEmpty()) {
			System.out.println("\tSố điện thoại không được để trống");
			return;
		}
		//Kiểm tra code đã tồn tại trong DS hay chưa?
		if (CustomerManagement.findByMobile(mobile) != -1) {
			System.out.println("\tSố điện thoại '" + mobile + "' đã có  trong danh sách");
			return;
		}
		this.setName(name);
	}

	public Customer() {
		super();
	}

	public Customer(int id, String code, String name, String mobile) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.mobile = mobile;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
