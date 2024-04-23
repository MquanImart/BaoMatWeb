package Model;

import java.time.LocalDate;

public class DuLieuNhanVien {
    private String hoten;
    private LocalDate ngaysinh;
    private String gioitinh;
    private String socccd;
    private LocalDate ngaycap;
    private String tinh_cap;
    private String huyen_cap;
    private String xa_cap;
    private String sonha_cap;
    private String tinh;
    private String huyen;
    private String xa;
    private String sonha;
    private String sdt;
    private String email;
    private String usernam;
    private String pass;
    private String congviec;
    private String phongban;
    private String chinhanh;
    private String bangcap;
    private LocalDate ngaybatdau;

    public DuLieuNhanVien(){}
    public DuLieuNhanVien(String hoten, LocalDate ngaysinh, String gioitinh, String socccd, LocalDate ngaycap,
                          String tinh_cap, String huyen_cap, String xa_cap, String sonha_cap, String tinh,
                          String huyen, String xa, String sonha, String sdt, String email, String usernam,
                          String pass, String congviec, String phongban, String chinhanh, String bangcap,
                          LocalDate ngaybatdau) {
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.socccd = socccd;
        this.ngaycap = ngaycap;
        this.tinh_cap = tinh_cap;
        this.huyen_cap = huyen_cap;
        this.xa_cap = xa_cap;
        this.sonha_cap = sonha_cap;
        this.tinh = tinh;
        this.huyen = huyen;
        this.xa = xa;
        this.sonha = sonha;
        this.sdt = sdt;
        this.email = email;
        this.usernam = usernam;
        this.pass = pass;
        this.congviec = congviec;
        this.phongban = phongban;
        this.chinhanh = chinhanh;
        this.bangcap = bangcap;
        this.ngaybatdau = ngaybatdau;
    }
    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
    public LocalDate getNgaysinh() {
        return ngaysinh;
    }
    public void setNgaysinh(LocalDate ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
    public String getGioitinh() {
        return gioitinh;
    }
    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
    public String getSocccd() {
        return socccd;
    }
    public void setSocccd(String socccd) {
        this.socccd = socccd;
    }
    public LocalDate getNgaycap() {
        return ngaycap;
    }
    public void setNgaycap(LocalDate ngaycap) {
        this.ngaycap = ngaycap;
    }
    public String getTinh_cap() {
        return tinh_cap;
    }
    public void setTinh_cap(String tinh_cap) {
        this.tinh_cap = tinh_cap;
    }
    public String getHuyen_cap() {
        return huyen_cap;
    }
    public void setHuyen_cap(String huyen_cap) {
        this.huyen_cap = huyen_cap;
    }
    public String getXa_cap() {
        return xa_cap;
    }
    public void setXa_cap(String xa_cap) {
        this.xa_cap = xa_cap;
    }
    public String getSonha_cap() {
        return sonha_cap;
    }
    public void setSonha_cap(String sonha_cap) {
        this.sonha_cap = sonha_cap;
    }
    public String getTinh() {
        return tinh;
    }
    public void setTinh(String tinh) {
        this.tinh = tinh;
    }
    public String getHuyen() {
        return huyen;
    }
    public void setHuyen(String huyen) {
        this.huyen = huyen;
    }
    public String getXa() {
        return xa;
    }
    public void setXa(String xa) {
        this.xa = xa;
    }
    public String getSonha() {
        return sonha;
    }
    public void setSonha(String sonha) {
        this.sonha = sonha;
    }
    public String getSdt() {
        return sdt;
    }
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsernam() {
        return usernam;
    }
    public void setUsernam(String usernam) {
        this.usernam = usernam;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getCongviec() {
        return congviec;
    }
    public void setCongviec(String congviec) {
        this.congviec = congviec;
    }
    public String getPhongban() {
        return phongban;
    }
    public void setPhongban(String phongban) {
        this.phongban = phongban;
    }
    public String getChinhanh() {
        return chinhanh;
    }
    public void setChinhanh(String chinhanh) {
        this.chinhanh = chinhanh;
    }
    public String getBangcap() {
        return bangcap;
    }
    public void setBangcap(String bangcap) {
        this.bangcap = bangcap;
    }
    public LocalDate getNgaybatdau() {
        return ngaybatdau;
    }
    public void setNgaybatdau(LocalDate ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }
}
