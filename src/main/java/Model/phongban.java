package Model;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
public class phongban implements Serializable{
    private static final long serialVersionUID = 1L;
    private String mapb;
    private String tenpb;
    private String macn;
    private String matrphong;
    private LocalDate ngaytao;
    private String mapbtr;
    public String getMapb() { return mapb; }
    public void setMapb(String mapb) { this.mapb=mapb; }
    public String getTenpb() { return tenpb; }
    public void setTenpb(String tenpb) { this.tenpb=tenpb; }
    public String getMacn() { return macn; }
    public void setMacn(String macn) { this.macn=macn; }
    public String getMatrphong() { return matrphong; }
    public void setMatrphong(String matrphong) { this.matrphong=matrphong; }

    public LocalDate getNgaytao () { return ngaytao; }
    public void setNgaytao(LocalDate ngaytao) { this.ngaytao=ngaytao; }
    public  String getMapbtr() { return mapbtr; }
    public phongban(String mapb, String tenpb, String macn, String matrphong, LocalDate ngaytao, String mapbtr){
        this.mapb=mapb;
        this.tenpb=tenpb;
        this.macn=macn;
        this.matrphong=matrphong;
        this.ngaytao=ngaytao;
        this.mapbtr=mapbtr;
    }
}
