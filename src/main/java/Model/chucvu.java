package Model;

import java.io.Serializable;

public class chucvu implements Serializable{
	private static final long serialVersionUID = 1L;
	private String matk;
	private String tentk;
	
	public String getMatk() {
		return matk;
	}
	
	public void setMatk(String matk) {
        this.matk=matk;
    }
	
	public String getTentk() {
		return tentk;
	}
	
	public void setTentk(String tentk) {
		this.tentk=tentk;
	}

	
	public chucvu(String matk, String tentk) {
		this.matk=matk;
		this.tentk=tentk;
	}
}


