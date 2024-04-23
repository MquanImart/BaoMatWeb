package Model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class cancuoccongdan implements Serializable{
	private static final long serialVersionUID = 1L;
	private String matk;
	private String cccd;
	private LocalDate ngaycap;
	private String madc;

	public cancuoccongdan() {

	}

	public cancuoccongdan(String matk, String cccd) {
		this.matk=matk;
		this.cccd=cccd;
	}

    public cancuoccongdan(String cccd, LocalDate ngaycap) {
		this.cccd=cccd;
		this.ngaycap=ngaycap;
    }
	public cancuoccongdan(String cccd, LocalDate ngaycap,String madc) {
		this.cccd=cccd;
		this.ngaycap=ngaycap;
		this.madc = madc;
	}
    public String getMatk() {
		return matk;
	}
	
	public void setMatk(String matk) {
        this.matk=matk;
    }
	
	public String getCccd() {
		return cccd;
	}
	
	public void setCccd(String cccd) {
		this.cccd=cccd;
	}
	
	public LocalDate getNgaycap() {
		return ngaycap;
	}
	
	public void setNgaycap(LocalDate ngaycap) {
		this.ngaycap=ngaycap;
	}
	
	public String getMadc() {
		return madc;
	}
	
	public cancuoccongdan(String matk, String cccd, LocalDate ngaycap, String madc) {
		this.matk=matk;
		this.cccd=cccd;
		this.ngaycap=ngaycap;
		this.madc=madc;
	}
}


