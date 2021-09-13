package app.model;

public class Run_Out {
	int no,ex_id;
	String sup,ex,amount,total,pice,pro;
	public Run_Out(int no, int ex_id, String sup, String ex, String amount, String total, String pice, String pro) {
		super();
		this.no = no;
		this.ex_id = ex_id;
		this.sup = sup;
		this.ex = ex;
		this.amount = amount;
		this.total = total;
		this.pice = pice;
		this.pro = pro;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getEx_id() {
		return ex_id;
	}
	public void setEx_id(int ex_id) {
		this.ex_id = ex_id;
	}
	public String getSup() {
		return sup;
	}
	public void setSup(String sup) {
		this.sup = sup;
	}
	public String getEx() {
		return ex;
	}
	public void setEx(String ex) {
		this.ex = ex;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getPice() {
		return pice;
	}
	public void setPice(String pice) {
		this.pice = pice;
	}
	public String getPro() {
		return pro;
	}
	public void setPro(String pro) {
		this.pro = pro;
	}
	
	
}
