package app.model;

public class Category1 {
	int cat_id,no;
	String cat_name;
	
	public Category1(int cat_id, int no, String cat_name) {
		this.cat_id = cat_id;
		this.no = no;
		this.cat_name = cat_name;
	}

	public int getCat_id() {
		return cat_id;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	
	
	
	
}
