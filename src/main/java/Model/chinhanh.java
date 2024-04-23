package Model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class chinhanh implements Serializable{
	private static final long serialVersionUID = 1L;
	private String macn;
	private String tencn;
	private String diachi;
	private String magiamdoc;
	private String tinhtrang;
	private LocalDate ngaytao;
	
	public String getMacn() {
		return macn;
	}
	
	public void setMacn(String macn) {
		this.macn=macn;
	}
	
	public String getTencn() {
		return tencn;
	}
	
	public void setTencn(String tencn) {
		this.tencn=tencn;
	}
	
	public String getDiachi() {
		return diachi;
	}
	
	public void setDiachi(String diachi) {
		this.diachi=diachi;
	}
	
	public String getMagiamdoc() {
		return magiamdoc;
	}
	
	public void setMagiamdoc(String magiamdoc) {
		this.magiamdoc=magiamdoc;
	}
	
	public String getTinhtrang() {
		return tinhtrang;
	}
	
	public void setTinhtrang(String tinhtrang) {
		this.tinhtrang=tinhtrang;
	}
	
	public LocalDate getNgaytao() {
		return ngaytao;
	}
	
	public void setNgaydtao(LocalDate ngaytao) {
		this.ngaytao=ngaytao;
	}
	
	public chinhanh(String macn, String tencn, String diachi, String magiamdoc, String tinhtrang, LocalDate ngaytao) {
		this.macn=macn;
		this.tencn=tencn;
		this.diachi=diachi;
		this.magiamdoc=magiamdoc;
		this.tinhtrang=tinhtrang;
		this.ngaytao=ngaytao;
	}
}
