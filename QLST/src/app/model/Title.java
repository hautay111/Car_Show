package app.model;

public class Title {
	int title_id,no;
	String title_name,description;
	public Title(int title_id, int no, String title_name, String description) {
		super();
		this.title_id = title_id;
		this.no = no;
		this.title_name = title_name;
		this.description = description;
	}
	public int getTitle_id() {
		return title_id;
	}
	public void setTitle_id(int title_id) {
		this.title_id = title_id;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle_name() {
		return title_name;
	}
	public void setTitle_name(String title_name) {
		this.title_name = title_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
