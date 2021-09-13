package app.model;

public class Brand1 {
	int brand_id,no;
	String brand_name;
	
	public Brand1(int brand_id, int no, String brand_name) {
		this.brand_id = brand_id;
		this.no = no;
		this.brand_name = brand_name;
	}

	public int getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	
	
}
