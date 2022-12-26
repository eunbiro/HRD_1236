package DTO;

public class Vote {
	String jumin;
	String name;
	String no;
	String time;
	String area;
	String confirm;
	String age = "만 ";
	String gender;
	
	
	public String getGender() {
		return gender;
	}
	public void setGender(String sex) {
		this.gender = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age += age;
	}
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	
}
