package app.model;

import javafx.scene.control.DatePicker;


public class Bill_Mange {
	int no,no2, ma_id,total_price,point,discount,quantity_detail,price_detail,total_detail;
	String name_product;
	String time;
	
	
	
	
	public Bill_Mange(int no, int no2, int ma_id, int total_price, int point, int discount, int quantity_detail,
			int price_detail, int total_detail, String name_product, String time) {
		super();
		this.no = no;
		this.no2 = no2;
		this.ma_id = ma_id;
		this.total_price = total_price;
		this.point = point;
		this.discount = discount;
		this.quantity_detail = quantity_detail;
		this.price_detail = price_detail;
		this.total_detail = total_detail;
		this.name_product = name_product;
		this.time = time;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getNo2() {
		return no2;
	}
	public void setNo2(int no2) {
		this.no2 = no2;
	}
	public int getMa_id() {
		return ma_id;
	}
	public void setMa_id(int ma_id) {
		this.ma_id = ma_id;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getQuantity_detail() {
		return quantity_detail;
	}
	public void setQuantity_detail(int quantity_detail) {
		this.quantity_detail = quantity_detail;
	}
	public int getPrice_detail() {
		return price_detail;
	}
	public void setPrice_detail(int price_detail) {
		this.price_detail = price_detail;
	}
	public int getTotal_detail() {
		return total_detail;
	}
	public void setTotal_detail(int total_detail) {
		this.total_detail = total_detail;
	}
	public String getName_product() {
		return name_product;
	}
	public void setName_product(String name_product) {
		this.name_product = name_product;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}



	
	

	
	
}
