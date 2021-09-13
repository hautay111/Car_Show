package app.model;

public class Bill {
	int no, pro_id,amount_stock,amount_input;
	String name,brand,price,code,category;
	public Bill(int no,int pro_id,int amount_stock,int amount_input, String name, String brand, String price, String code, String category) {
		this.no = no;
		this.pro_id = pro_id;
		this.amount_stock=amount_stock;
		this.amount_input=amount_input;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.code = code;
		this.category = category;

	}
	
	

	public int getAmount_stock() {
		return amount_stock;
	}



	public void setAmount_stock(int amount_stock) {
		this.amount_stock = amount_stock;
	}



	public int getAmount_input() {
		return amount_input;
	}



	public void setAmount_input(int amount_input) {
		this.amount_input = amount_input;
	}



	public int getPro_id() {
		return pro_id;
	}



	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}



	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	
	


}
