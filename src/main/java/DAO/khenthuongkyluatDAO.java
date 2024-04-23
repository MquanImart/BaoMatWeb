package DAO;

import JDBCUtils.JDBCUtils;
import Model.congtac;
import Model.nhanvien;
import Model.thanhtichkyluat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class khenthuongkyluatDAO {
    private static final String SELECT_ALL = "select * from thanhtichkyluat where matk = ?";
    private static final String CHECK_ID = "select * from thanhtichkyluat where id = ?";
    private  static  final String INSERT_KTKL_SQL =  "INSERT INTO thanhtichkyluat" + "  (id, soqd, matk, ngay,  loai, noidung, ngki) VALUES " + " (?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_KTKL = "update thanhtichkyluat set soqd =?, matk =?, ngay = ?, loai = ?, noidung = ? where id = ?;";
    private static final String DELETE_KTKL = "Delete from thanhtichkyluat where id = ?;";
    public static List<thanhtichkyluat> DanhSachKTKL(String matk) {

        List <thanhtichkyluat> listKTKL = new ArrayList< >();

        try (Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            preparedStatement.setString(1, matk);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String soqd = rs.getString("soqd");
                LocalDate ngay = rs.getDate("ngay").toLocalDate();
                String loai = rs.getString("loai");
                String noidung = rs.getString("noidung");
                String nguoiki = rs.getString("ngki");
                listKTKL.add(new thanhtichkyluat(id,soqd,matk,ngay,loai,noidung,nguoiki));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return listKTKL;
    }
    private static List<thanhtichkyluat> getKTKL(List<nhanvien> nhanviens){
        List<thanhtichkyluat> listKTKL = new ArrayList<>();
        for (nhanvien nv: nhanviens) {
            try (Connection connection = JDBCUtils.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
                preparedStatement.setString(1, nv.getMatk());
                ResultSet rs = preparedStatement.executeQuery();
                System.out.println(preparedStatement);
                while (rs.next()) {
                    String id = rs.getString("id");
                    String soqd = rs.getString("soqd");
                    LocalDate ngay = rs.getDate("ngay").toLocalDate();
                    String loai = rs.getString("loai");
                    String noidung = rs.getString("noidung");
                    String nguoiki = rs.getString("ngki");
                    listKTKL.add(new thanhtichkyluat(id,soqd,nv.getMatk(),ngay,loai,noidung,nguoiki));
                }
            } catch (SQLException exception) {
                JDBCUtils.printSQLException(exception);
            }
        }
        return listKTKL;
    }
    public static List< thanhtichkyluat > DanhSachKTKL_NV_PB(String mapb) {
        List <nhanvien> listnhanvien = qlnhanvienDAO.LayNhanVienPB(mapb);
        return getKTKL(listnhanvien);
    }
    public static List< thanhtichkyluat > DanhSachKTKL_NV_CN(String macn) {
        List <nhanvien> listnhanvien = qlnhanvienDAO.LayNhanVienCN(macn);
        return getKTKL(listnhanvien);
    }
    public static List< thanhtichkyluat > DanhSachKTKL_ALL_NV() {
        List <nhanvien> listnhanvien = qlnhanvienDAO.LayNhanVien();
        return getKTKL(listnhanvien);
    }
    public static void ThemKTKL(thanhtichkyluat KTKL) {
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_KTKL_SQL)) {
            preparedStatement.setString(1, KTKL.getId());
            preparedStatement.setString(2, KTKL.getSoqd());
            preparedStatement.setString(3, KTKL.getMatk());
            preparedStatement.setDate(4, JDBCUtils.getSQLDate(KTKL.getNgay()));
            preparedStatement.setString(5, KTKL.getLoai());
            preparedStatement.setString(6, KTKL.getNoidung());
            preparedStatement.setString(7, KTKL.getNgki());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    public static void ThayDoiKTKL(thanhtichkyluat KTKL) {
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_KTKL)) {
            preparedStatement.setString(1, KTKL.getSoqd());
            preparedStatement.setString(2, KTKL.getMatk());
            preparedStatement.setDate(3, JDBCUtils.getSQLDate(KTKL.getNgay()));
            preparedStatement.setString(4, KTKL.getLoai());
            preparedStatement.setString(5, KTKL.getNoidung());
            preparedStatement.setString(6, KTKL.getId());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    public  static void XoaKTKL(thanhtichkyluat KTKL){
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_KTKL)) {
            preparedStatement.setString(1, KTKL.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    public static boolean CheckID(String id){
        try (Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ID);) {
            preparedStatement.setString(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                return true;
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return false;
    }
}
