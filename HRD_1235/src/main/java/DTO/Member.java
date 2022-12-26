package DTO;

public class Member {
	int no;
	String name;
	String code;
	String school;
	String jumin;
	String city;
	String tel;
	String count;
	
	
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		if (this.tel == null) {
			this.tel = tel;
		} else {
			this.tel += tel;
		}
	}
	
}
