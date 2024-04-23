package Model;

import java.io.Serializable;

public class diachi implements Serializable{
	private static final long serialVersionUID = 1L;
	private String madc;
	private String tinhtp;
	private String quanhuyen;
	private String phuongxa;
	private String sonha;

	public diachi() {}

    public String getMadc() {
		return madc;
	}
	
	public void setMadc(String madc) {
		this.madc=madc;
	}
	
	public String getTinhtp() {
		return tinhtp;
	}
	
	public void setTinhtp(String tinhtp) {
		this.tinhtp=tinhtp;
	}
	
	public String getPhuongxa() {
		return phuongxa;
	}
	
	public void setPhuongxa(String phuongxa) {
		this.phuongxa=phuongxa;
	}
	
	public String getQuanhuyen() {
		return quanhuyen;
	}
	
	public void setQuanhuyen(String quanhuyen) {
		this.quanhuyen=quanhuyen;
	}
	
	public String getSonha() {
		return sonha;
	}
	
	public void setSonha(String sonha) {
		this.sonha=sonha;
	}
	
	public diachi(String madc, String tinhtp, String quanhuyen, String phuongxa, String sonha) {
		this.madc=madc;
		this.tinhtp=tinhtp;
		this.quanhuyen=quanhuyen;
		this.phuongxa=phuongxa;
		this.sonha=sonha;
	}
}
