package app.model;

public class Account1 {
	int emp_id,emp_status,no;
	String emp_name,emp_email,emp_phone,emp_address,emp_gender,title_name,emp_user,emp_pass,status1;
	
	public Account1(int emp_id, int emp_status, int no, String emp_name, String emp_email, String emp_phone,
			String emp_address, String emp_gender, String title_name, String emp_user, String emp_pass) {
		this.emp_id = emp_id;
		this.emp_status = emp_status;
		this.no = no;
		this.emp_name = emp_name;
		this.emp_email = emp_email;
		this.emp_phone = emp_phone;
		this.emp_address = emp_address;
		this.emp_gender = emp_gender;
		this.title_name = title_name;
		this.emp_user = emp_user;
		this.emp_pass = emp_pass;
		if(this.emp_status==1) {
			status1="Active";
		}else if(this.emp_status==0){
			status1="None Active";
		}else {
			status1="Bloked";
		}
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public int getEmp_status() {
		return emp_status;
	}

	public void setEmp_status(int emp_status) {
		this.emp_status = emp_status;
		if(this.emp_status==1) {
			status1="Active";
		}else if(this.emp_status==0){
			status1="None Active";
		}else {
			status1="Bloked";
		}
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public String getEmp_phone() {
		return emp_phone;
	}

	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}

	public String getEmp_address() {
		return emp_address;
	}

	public void setEmp_address(String emp_address) {
		this.emp_address = emp_address;
	}

	public String getEmp_gender() {
		return emp_gender;
	}

	public void setEmp_gender(String emp_gender) {
		this.emp_gender = emp_gender;
	}

	public String getTitle_name() {
		return title_name;
	}

	public void setTitle_name(String title_name) {
		this.title_name = title_name;
	}

	public String getEmp_user() {
		return emp_user;
	}

	public void setEmp_user(String emp_user) {
		this.emp_user = emp_user;
	}

	public String getEmp_pass() {
		return emp_pass;
	}

	public void setEmp_pass(String emp_pass) {
		this.emp_pass = emp_pass;
	}
	
	
	
	
}
