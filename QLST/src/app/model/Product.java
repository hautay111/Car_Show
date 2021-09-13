package app.model;

import java.text.DecimalFormat;

public class Product {

	int id,no;
	String price,barcode,name,expiry;
	
	public Product(int id, int no, String price, String barcode, String name, String expiry) {
		this.id = id;
		this.no = no;
		this.price = price;
		this.barcode = barcode;
		this.name = name;
		this.expiry = expiry;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

		
}
