package app.model;

public class ChangeShift {
	int shift_id,no;
	String main_shift,switch_shift,date_switch,shift_change_date,emp_name,emp_email;
	public ChangeShift(int shift_id, int no, String main_shift, String switch_shift, String date_switch, String shift_change_date, String emp_name, String emp_email) {
		this.shift_id = shift_id;
		this.no = no;
		this.main_shift = main_shift;
		this.switch_shift = switch_shift;
		this.date_switch = date_switch;
		this.shift_change_date = shift_change_date;
		this.emp_name = emp_name;
		this.emp_email = emp_email;
	}
	public int getShift_id() {
		return shift_id;
	}
	public void setShift_id(int shift_id) {
		this.shift_id = shift_id;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getMain_shift() {
		return main_shift;
	}
	public void setMain_shift(String main_shift) {
		this.main_shift = main_shift;
	}
	public String getSwitch_shift() {
		return switch_shift;
	}
	public void setSwitch_shift(String switch_shift) {
		this.switch_shift = switch_shift;
	}
	public String getDate_switch() {
		return date_switch;
	}
	public void setDate_switch(String date_switch) {
		this.date_switch = date_switch;
	}
	public String getShift_change_date() {
		return shift_change_date;
	}
	public void setShift_change_date(String shift_change_date) {
		this.shift_change_date = shift_change_date;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_email() {
		return emp_email;
	}
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}
}
