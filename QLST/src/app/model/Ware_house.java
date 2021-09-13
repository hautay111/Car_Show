package app.model;

public class Ware_house {
	int no,pro_id,amount,price,ipd_id;
	String name,sup,date_input,date_expiry,id;
	public Ware_house(int ipd_id,int no, int pro_id, String id, int amount, int price, String name, String sup, String date_input,
			String date_expiry) {
		super();
		this.ipd_id = ipd_id;
		this.no = no;
		this.pro_id = pro_id;
		this.id = id;
		this.amount = amount;
		this.price = price;
		this.name = name;
		this.sup = sup;
		this.date_input = date_input;
		this.date_expiry = date_expiry;
	}
	public int getIpd_id() {
		return ipd_id;
	}
	public void setIpd_id(int ipd_id) {
		this.ipd_id = ipd_id;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
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
	public String getSup() {
		return sup;
	}
	public void setSup(String sup) {
		this.sup = sup;
	}
	public String getDate_input() {
		return date_input;
	}
	public void setDate_input(String date_input) {
		this.date_input = date_input;
	}
	public String getDate_expiry() {
		return date_expiry;
	}
	public void setDate_expiry(String date_expiry) {
		this.date_expiry = date_expiry;
	}
	
}
