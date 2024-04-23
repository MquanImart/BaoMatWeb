package Model;

import java.io.Serializable;
import java.sql.Date;

public class hopdong implements Serializable{
	private static final long serialVersionUID = 1L;
	private String mahopdong;
	private Date ngayki;
	private Date thoihan;
	private String nguoilam;
	private String nguoichu;
	private String mucluongcoban;
	private String noidung;
	
	public String getMahopdong() {
		return mahopdong;
	}
	
	public void setMahopdong(String mahopdong) {
		this.mahopdong=mahopdong;
	}
	
	public Date getNgayki() {
		return ngayki;
	}
	
	public void setNgayki(Date ngayki) {
		this.ngayki=ngayki;
	}
}
