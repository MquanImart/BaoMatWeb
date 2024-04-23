package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class congtac implements Serializable{
	private static final long serialVersionUID = 1L;
	private String matk;
	private LocalDate ngaybatdau;
	private String tentochuc;
	private String diachi;
	private String chucvu;
	private String lydo;

	public congtac() {

	}

	public String getMatk() {
		return matk;
	}
	
	public void setMatk(String matk) {
        this.matk=matk;
    }
	
	public LocalDate getNgaybatdau() {
		return ngaybatdau;
	}
	
	public void setNgaybatdau(LocalDate ngaybatdau) {
		this.ngaybatdau=ngaybatdau;
	}
	
	public String getTentochuc() {
		return tentochuc;
	}
	
	public void setTentochuc(String tentochuc) {
		this.tentochuc=tentochuc;
	}
	
	public String getDiachi() {
		return diachi;
	}
	
	public void setDiachi(String diachi) {
		this.diachi=diachi;
	}
	
	public String getChucvu() {
		return chucvu;
	}
	
	public void setChuccu(String chucvu) {
		this.chucvu=chucvu;
	}
	
	public String getLydo() {
		return lydo;
	}
	
	public void setLydo(String lydo) {
		this.lydo=lydo;
	}
	
	public congtac(String matk, LocalDate ngaybatdau, String tentochuc, String diachi, String chucvu, String lydo) {
		this.matk=matk;
		this.ngaybatdau=ngaybatdau;
		this.tentochuc=tentochuc;
		this.diachi=diachi;
		this.chucvu=chucvu;
		this.lydo=lydo;
	}
}


