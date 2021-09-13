package app.model.search_dashboard;

public class M4 {
	int pro_id,amount,no;
	String pro_name;
	public M4(int pro_id, int amount, int no, String pro_name) {
		super();
		this.pro_id = pro_id;
		this.amount = amount;
		this.no = no;
		this.pro_name = pro_name;
	}
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	
}
