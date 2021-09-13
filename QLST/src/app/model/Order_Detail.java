package app.model;

public class Order_Detail {

	int no,id,id_detail,quantity,price,total;
	String name;
	
	
	
	public Order_Detail(int no, int id,int id_detail, int quantity, int price, String name, int total) {
		super();
		this.no = no;
		this.id = id;
		this.id_detail = id_detail;
		this.quantity = quantity;
		this.price = price;
		this.name = name;
		this.total = total;
	}
	
	
	
	
	public int getId_detail() {
		return id_detail;
	}

	public void setId_detail(int id_detail) {
		this.id_detail = id_detail;
	}




	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}

	
	
	



	
	
	
}
