package Model;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class thanhtichkyluat implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String soqd;
    private String matk;
    private LocalDate ngay;
    private String loai;
    private String noidung;
    private String ngki;
    public String getId() { return id; }
    public void setId(String id) { this.id=id; }
    public String getSoqd() { return soqd; }
    public void setSoqd(String soqd) { this.soqd=soqd; }
    public String getMatk() { return matk; }
    public void setMatk(String matk) { this.matk=matk; }
    public LocalDate getNgay() { return ngay; }
    public void setNgay(LocalDate ngay) { this.ngay=ngay; }
    public String getLoai() { return loai; }
    public void setLoai(String loai) { this.loai=loai; }
    public String getNoidung() { return noidung; }
    public void setNoidung(String noidung) { this.noidung=noidung; }
    public String getNgki() { return ngki; }
    public void setNgki(String ngki) { this.ngay=ngay; }
    public thanhtichkyluat(String id, String soqd, String matk, LocalDate ngay, String loai, String noidung, String ngki){
        this.id=id;
        this.soqd=soqd;
        this.matk=matk;
        this.ngay=ngay;
        this.loai=loai;
        this.noidung=noidung;
        this.ngki=ngki;
    }
}
