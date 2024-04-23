package DAO;
import JDBCUtils.JDBCUtils;
import Model.*;

import java.sql.*;
import java.time.LocalDate;

public class thongtincanhanDAO {
    private static final String SELECT_ALL_TTCANHAN = "select * from thongtincanhan where matk = ?;";
    private static final String SELECT_CCCD = "select * from cancuoccongdan where matk = ?;";
    private static final String SELECT_ALL_TAIKHOAN = "select * from taikhoan where matk = ?;";
    private static final String SELECT_CHUCVU = "select * from chucvu where matk = ?;";
    private static final String SELECT_CONGVIEC = "select * from nhanvien where matk = ?;";
    private static final String SELECT_NGAYBATDAU = "select * from nhanvien where matk = ?";
    private static final String SELECT_TENPB = "select * " +
            "from phongban join nhanvien on phongban.mapb = nhanvien.mapb where matk = ?;";
    private static final String SELECT_TENCN = "select * " +
            "from chinhanh join nhanvien on chinhanh.macn = nhanvien.macn where matk = ?;";
    private static final String SELECT_DIACHI = "select * from diachi where madc = ?;";

    private static final String UPDATE_TAIKHOAN = "update taikhoan set pass = ? where matk = ?;";
    private static final String UPDATE_TTCANHAN = "update thongtincanhan set hoten = ?, ngaysinh = ?, gioitinh = ?, sdt = ?, email = ? where matk = ?;";
    private static final String UPDATE_CCCD = "update cancuoccongdan set cccd = ?, ngaycap = ? where matk = ?;";
    private static final String UPDATE_DIACHI = "update diachi set tinhtp = ?, quanhuyen = ?, phuongxa = ?, sonha = ? where madc = ?;";

    private  static  final String INSERT_THONGTINCANHAN =  "INSERT INTO thongtincanhan" + "  (matk, hoten, ngaysinh,gioitinh, diachi,  sdt, email, bangcap) VALUES " + " (?, ?, ?, ?, ?, ?,?,?);";
    private  static  final String INSERT_CCCD =  "INSERT INTO cancuoccongdan" + "  (matk, cccd, ngaycap,madc) VALUES " + " (?, ?, ?, ?);";

    public static thongtincanhan layThongTinCaNhan(String matk)
    {
        thongtincanhan tt = new thongtincanhan();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TTCANHAN);){
            preparedStatement.setString(1,matk);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                String manv = rs.getString("matk");
                String hoten = rs.getString("hoten");
                LocalDate ngaysinh = rs.getDate("ngaysinh").toLocalDate();
                String gioitinh = rs.getString("gioitinh");
                String diachi = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");
                String bangcap = rs.getString("bangcap");
                tt = new thongtincanhan(manv, hoten, ngaysinh, gioitinh, diachi, sdt, email, bangcap);
            }

        }catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);}
        return tt;
    }
    public static cancuoccongdan layCCCD(String matk){
        cancuoccongdan cancuoc = new cancuoccongdan();
        try(Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CCCD);)
        {
            preparedStatement.setString(1,matk);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                String cccd = rs.getString("cccd");
                LocalDate ngaycap = rs.getDate("ngaycap").toLocalDate();
                String madc = rs.getString("madc");
                cancuoc = new cancuoccongdan(cccd,ngaycap,madc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cancuoc;
    }
    public static taikhoan layTaiKhoan(String matk){
        taikhoan tk = new taikhoan();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TAIKHOAN);) {
            preparedStatement.setString(1, matk);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("pass");
                tk = new taikhoan(username, password);
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return tk;
    }
    public static String layChucVu(String matk){
        String chucvu = "";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CHUCVU);) {
            preparedStatement.setString(1, matk);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                chucvu = rs.getString("tentk");
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return chucvu;
    }
    public static String LayCongViec(String matk){
        String congviec = "";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONGVIEC);) {
            preparedStatement.setString(1, matk);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                congviec = rs.getString("congviec");
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return congviec;
    }
    public static LocalDate layNgayBatDau(String matk) {
        LocalDate ngaybatdau = null;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NGAYBATDAU);) {
            preparedStatement.setString(1, matk);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ngaybatdau = rs.getDate("ngaybatdau").toLocalDate();
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return ngaybatdau;
    }
    public static String layTenPB(String matk){
        String tenpb = "";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TENPB);) {
            preparedStatement.setString(1, matk);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                tenpb = rs.getString("tenpb");
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return tenpb;
    }
    public static String layTenCN(String matk) {
        String tencn = "";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TENCN);) {
            preparedStatement.setString(1, matk);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                tencn = rs.getString("tencn");
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return tencn;
    }
    public static diachi layDiaChi(String madc){
        diachi dc = new diachi();
        try (Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DIACHI);){
            preparedStatement.setString(1,madc);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String madcc = rs.getString("madc");
                String tinhtp = rs.getString("tinhtp");
                String quanhuyen = rs.getString("quanhuyen");
                String phuongxa = rs.getString("phuongxa");
                String sonha = rs.getString("sonha");

                dc = new diachi(madc,tinhtp,phuongxa,quanhuyen,sonha);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dc;
    }
    public static void capNhatMatKhau(taikhoan taikhoan) {
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TAIKHOAN)) {
            preparedStatement.setString(1, taikhoan.getPass());
            preparedStatement.setString(2, taikhoan.getMatk());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    public static void capNhatThongTinCaNhan(thongtincanhan tt){
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TTCANHAN)) {
            preparedStatement.setString(1, tt.getHoten());
            preparedStatement.setDate(2, JDBCUtils.getSQLDate(tt.getNgaysinh()));
            preparedStatement.setString(3,tt.getGioitinh());
            preparedStatement.setString(4,tt.getSdt());
            preparedStatement.setString(5,tt.getEmail());
            preparedStatement.setString(6,tt.getMatk());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    public static void capNhatDiaChi(diachi dc){
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DIACHI)) {
            preparedStatement.setString(1,dc.getTinhtp());
            preparedStatement.setString(2,dc.getQuanhuyen());
            preparedStatement.setString(3,dc.getPhuongxa());
            preparedStatement.setString(4,dc.getSonha());
            preparedStatement.setString(5,dc.getMadc());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    public static void capNhatCCCD(cancuoccongdan cccd){
        try(Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CCCD)){
            preparedStatement.setString(1,cccd.getCccd());
            preparedStatement.setDate(2,JDBCUtils.getSQLDate(cccd.getNgaycap()));
            preparedStatement.setString(3,cccd.getMatk());
            preparedStatement.executeUpdate();
        }catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    public static void ThemThongTinCaNhan(thongtincanhan ttcn){
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_THONGTINCANHAN);) {
            preparedStatement.setString(1, ttcn.getMatk());
            preparedStatement.setString(2, ttcn.getHoten());
            preparedStatement.setDate(3, JDBCUtils.getSQLDate(ttcn.getNgaysinh()));
            preparedStatement.setString(4, ttcn.getGioitinh());
            preparedStatement.setString(5, ttcn.getDiachi());
            preparedStatement.setString(6, ttcn.getSdt());
            preparedStatement.setString(7, ttcn.getEmail());
            preparedStatement.setString(8, ttcn.getBangcap());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    public static void ThemCCCD(cancuoccongdan cccd){
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CCCD);) {
            preparedStatement.setString(1, cccd.getMatk());
            preparedStatement.setString(2, cccd.getCccd());
            preparedStatement.setDate(3, JDBCUtils.getSQLDate(cccd.getNgaycap()));
            preparedStatement.setString(4, cccd.getMadc());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
}
