package DAO;

import JDBCUtils.JDBCUtils;
import Model.congtac;
import DAO.phongbanDAO;
import Model.nhanvien;
import Model.phongban;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class congtacDAO {

    private static final String SELECT_ALL = "select * from congtac where matk = ?";
    private  static  final String INSERT_CONGTAC_SQL =  "INSERT INTO congtac" + "  (matk, ngaybatdau, tentochuc, diachi,  chucvu, lydo) VALUES " + " (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_CONGTAC = "update congtac set diachi =?, chucvu =?, lydo = ? where matk = ? and ngaybatdau = ? and tentochuc= ?;";
    private static final String DELETE_CONGTAC = "Delete from congtac where matk = ? and ngaybatdau = ? and tentochuc= ?;";
    public static List< congtac > DanhSachCongTac(String matk) {

        List < congtac > listcongtac = new ArrayList< >();

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            preparedStatement.setString(1, matk);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                LocalDate ngaybatdau = rs.getDate("ngaybatdau").toLocalDate();
                String tentochuc = rs.getString("tentochuc");
                String diachi = rs.getString("diachi");
                String chucvu = rs.getString("chucvu");
                String lydo = rs.getString("lydo");
                listcongtac.add(new congtac(matk,ngaybatdau,tentochuc,diachi,chucvu,lydo));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return listcongtac;
    }
    public static void ThemCongTac(congtac congtac) {
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONGTAC_SQL)) {
            preparedStatement.setString(1, congtac.getMatk());
            preparedStatement.setDate(2, JDBCUtils.getSQLDate(congtac.getNgaybatdau()));
            preparedStatement.setString(3, congtac.getTentochuc());
            preparedStatement.setString(4, congtac.getDiachi());
            preparedStatement.setString(5, congtac.getChucvu());
            preparedStatement.setString(6, congtac.getLydo());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    public static void ThayDoiCongTac(congtac congtac) {
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONGTAC)) {
            preparedStatement.setString(1, congtac.getDiachi());
            preparedStatement.setString(2, congtac.getChucvu());
            preparedStatement.setString(3, congtac.getLydo());
            preparedStatement.setString(4, congtac.getMatk());
            preparedStatement.setDate(5, JDBCUtils.getSQLDate(congtac.getNgaybatdau()));
            preparedStatement.setString(6, congtac.getTentochuc());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    public  static void XoaCongTac(congtac congtac){
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CONGTAC)) {
            preparedStatement.setString(1, congtac.getMatk());
            preparedStatement.setDate(2, JDBCUtils.getSQLDate(congtac.getNgaybatdau()));
            preparedStatement.setString(3, congtac.getTentochuc());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    private static List<congtac> getCongTacs(List<nhanvien> nhanviens){
        List<congtac> listcongtac = new ArrayList<>();
        for (nhanvien nv: nhanviens) {
            try (Connection connection = JDBCUtils.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
                preparedStatement.setString(1, nv.getMatk());
                ResultSet rs = preparedStatement.executeQuery();
                System.out.println(preparedStatement);
                while (rs.next()) {
                    LocalDate ngaybatdau = rs.getDate("ngaybatdau").toLocalDate();
                    String tentochuc = rs.getString("tentochuc");
                    String diachi = rs.getString("diachi");
                    String chucvu = rs.getString("chucvu");
                    String lydo = rs.getString("lydo");
                    listcongtac.add(new congtac(nv.getMatk(),ngaybatdau,tentochuc,diachi,chucvu,lydo));
                }
            } catch (SQLException exception) {
                JDBCUtils.printSQLException(exception);
            }
        }
        return listcongtac;
    }
    public static List< congtac > DanhSachCongTac_NV_PB(String mapb) {
        List <nhanvien> listnhanvien = qlnhanvienDAO.LayNhanVienPB(mapb);
        return getCongTacs(listnhanvien);
    }
    public static List< congtac > DanhSachCongTac_NV_CN(String macn) {
        List <nhanvien> listnhanvien = qlnhanvienDAO.LayNhanVienCN(macn);
        return getCongTacs(listnhanvien);
    }
    public static List< congtac > DanhSachCongTac_ALL_NV() {
        List <nhanvien> listnhanvien = qlnhanvienDAO.LayNhanVien();
        return getCongTacs(listnhanvien);
    }
}