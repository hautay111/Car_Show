package app.model;

public class Inventory {
	int wh_id,input_de_id,amount,input_price,no;
	String pro_name,date_input;
	public Inventory(int wh_id, int input_de_id, int amount, int input_price, int no, String pro_name, String date_input) {
		this.wh_id = wh_id;
		this.input_de_id = input_de_id;
		this.amount = amount;
		this.input_price = input_price;
		this.no = no;
		this.pro_name = pro_name;
		this.date_input = date_input;


}
	public int getWh_id() {
		return wh_id;
	}
	public void setWh_id(int wh_id) {
		this.wh_id = wh_id;
	}
	public int getInput_de_id() {
		return input_de_id;
	}
	public void setInput_de_id(int input_de_id) {
		this.input_de_id = input_de_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getInput_price() {
		return input_price;
	}
	public void setInput_price(int input_price) {
		this.input_price = input_price;
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
	public String getDate_input() {
		return date_input;
	}
	public void setDate_input(String date_input) {
		this.date_input = date_input;
	}
	
}