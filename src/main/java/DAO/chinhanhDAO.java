package DAO;

import JDBCUtils.JDBCUtils;
import Model.chinhanh;
import Model.phongban;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class chinhanhDAO {
    private static final String SELECT_ALL = "select * from chinhanh";
    private static final String SELECT_ALL_CN = "select * from chinhanh where macn = ?;";
    private static final String DELETE_CN_BY_MACN = "delete from chinhanh where macn = ?;";
    private static final String SELECT_GIAMDOC = "select macn from chinhanh where magiamdoc = ?;";

    private static final String SELECT_CN_BY_MACN = "select * from chinhanh where macn = ?";

    private static final String UPDATE_CN = "update chinhanh set tencn = ?, diachi = ?, magiamdoc =?, tinhtrang = ? where macn = ?;";
    private static final String INSERT_CN_BY_MACN = "INSERT INTO chinhanh (macn, tencn, diachi, magiamdoc, tinhtrang, ngaytao) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_MACN = "select magiamdoc from chinhanh where macn = ?;";
    public static List<chinhanh> selectAllchinhanh() {

        List < chinhanh > listchinhanh = new ArrayList< >();

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String macn = rs.getString("macn");
                String tencn = rs.getString("tencn");
                String diachi = rs.getString("diachi");
                String magiamdoc = rs.getString("magiamdoc");
                String tinhtrang = rs.getString("tinhtrang");
                LocalDate ngaytao = rs.getDate("ngaytao").toLocalDate();  // Use java.util.Date

                listchinhanh.add(new chinhanh(macn, tencn, diachi, magiamdoc, tinhtrang, ngaytao));

            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return listchinhanh;
    }
    public static List<chinhanh> selectAllchinhanh_MaCN(String MaChiNhanh) {

        List < chinhanh > listchinhanh = new ArrayList< >();

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CN);) {
            preparedStatement.setString(1,MaChiNhanh);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String macn = rs.getString("macn");
                String tencn = rs.getString("tencn");
                String diachi = rs.getString("diachi");
                String magiamdoc = rs.getString("magiamdoc");
                String tinhtrang = rs.getString("tinhtrang");
                LocalDate ngaytao = rs.getDate("ngaytao").toLocalDate();  // Use java.util.Date

                listchinhanh.add(new chinhanh(macn, tencn, diachi, magiamdoc, tinhtrang, ngaytao));

            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return listchinhanh;
    }
    public boolean deleteChiNhanh(String macn) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CN_BY_MACN);) {
            statement.setString(1, macn);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    public static chinhanh selectChiNhanh(String Macn) {
        chinhanh cn = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CN_BY_MACN);) {
            preparedStatement.setString(1, Macn);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String macn = rs.getString("macn");
                String tencn = rs.getString("tencn");
                String diachi = rs.getString("diachi");
                String magiamdoc = rs.getString("magiamdoc");
                String tinhtrang = rs.getString("tinhtrang");
                LocalDate ngaytao = rs.getDate("ngaytao").toLocalDate();  // Use java.util.Date

                cn = new chinhanh(macn, tencn, diachi, magiamdoc, tinhtrang, ngaytao);

            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return cn;
    }
    public static String LayMaCN(String magiamdoc) throws SQLException {
        String result = null;
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_GIAMDOC);) {
            statement.setString(1, magiamdoc);
            ResultSet rs = statement.executeQuery();
            System.out.println(statement);
            while (rs.next()) {
                result = rs.getString("macn");
            }
        }
        return result;
    }
    public void insertChiNhanh(chinhanh cn) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CN_BY_MACN)) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, cn.getMacn());
            preparedStatement.setString(2, cn.getTencn());
            preparedStatement.setString(3, cn.getDiachi());
            preparedStatement.setString(4, cn.getMagiamdoc());
            preparedStatement.setString(5, cn.getTinhtrang());
            preparedStatement.setDate(6, JDBCUtils.getSQLDate(cn.getNgaytao()));
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    public boolean updateChiNhanh(chinhanh cn) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CN);) {
            statement.setString(1, cn.getTencn());
            statement.setString(2, cn.getDiachi());
            statement.setString(3, cn.getMagiamdoc());
            statement.setString(4, cn.getTinhtrang());
            statement.setString(5, cn.getMacn());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    public static String LayMaGiamDoc(String macn) throws SQLException {
        String result = null;
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_MACN);) {
            statement.setString(1, macn);
            ResultSet rs = statement.executeQuery();
            System.out.println(statement);
            while (rs.next()) {
                result = rs.getString("magiamdoc");
            }
        }
        return result;
    }
}
