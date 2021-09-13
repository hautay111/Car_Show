package app.model;

public class Unit {
	int no,id;
	String name;
	public Unit(int no, int id, String name) {
		super();
		this.no = no;
		this.id = id;
		this.name = name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
