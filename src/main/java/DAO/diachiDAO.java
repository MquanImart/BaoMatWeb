package DAO;

import JDBCUtils.JDBCUtils;
import Model.congtac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Model.diachi;
import Model.phongban;

public class diachiDAO {
    private static final String SELECT_ALL_DIACHI = "select * from diachi";
    private static final String CHECK_MADC = "select * from diachi where madc = ?";
    private static final String SELECT_DC = "SELECT diachi FROM chinhanh WHERE macn = ?";
    private static final String UPDATE_DC_CN = "UPDATE diachi SET tinhtp = ?, quanhuyen = ?, phuongxa = ?, sonha = ? WHERE madc = ?";
    private static final String INSERT_DIACHICN = "INSERT INTO diachi (madc, tinhtp, quanhuyen, phuongxa, sonha) VALUES (?, ?, ?, ?, ?)";

    public boolean uodateDiaChi_CN(diachi dc) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_DC_CN);) {
            statement.setString(1, dc.getTinhtp());
            statement.setString(2, dc.getQuanhuyen());
            statement.setString(3, dc.getPhuongxa());
            statement.setString(4, dc.getSonha());
            statement.setString(5, dc.getMadc());
            rowUpdated = statement.executeUpdate() > 0;
            System.out.println(statement);
        }
        return rowUpdated;
    }
    public static String LayMaDC (String macn) throws SQLException {
        String result = null;
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_DC);) {
            statement.setString(1, macn);
            ResultSet rs = statement.executeQuery();
            System.out.println(statement);
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                result = rs.getString("diachi");
            }
        }
        return result;
    }

    public static List<diachi> DanhSachDiaChi() {

        List < diachi > listdiachi = new ArrayList< >();

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DIACHI);) {
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String madc = rs.getString("madc");
                String tinhtp = rs.getString("tinhtp");
                String quanhuyen = rs.getString("quanhuyen");
                String phuongxa = rs.getString("phuongxa");
                String sonha = rs.getString("sonha");
                listdiachi.add(new diachi(madc, tinhtp, quanhuyen, phuongxa, sonha));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return listdiachi;
    }
    public static diachi Selected_DiaChi(String macn) {
        diachi diachicn = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_MADC);) {
            preparedStatement.setString(1, macn);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String madc = rs.getString("madc");
                String tinhtp = rs.getString("tinhtp");
                String quanhuyen = rs.getString("quanhuyen");
                String phuongxa = rs.getString("phuongxa");
                String sonha = rs.getString("sonha");

                diachicn = new diachi(madc, tinhtp, quanhuyen, phuongxa, sonha);

            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return diachicn;
    }
    public static boolean CheckID(String id){
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_MADC);) {
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
    public static String DiaChiChiNhanh() {
        int[] id_num = {0, 0, 1};
        String id = null;
        while (true) {
            id = "DN" + id_num[0] + id_num[1] + id_num[2];
            if (CheckID(id)) {
                id_num[2] = id_num[2] + 1;
                id_num[1] = id_num[1] + id_num[2] / 10;
                id_num[0] = id_num[0] + id_num[1] / 10;

                id_num[2] = id_num[2] % 10;
                id_num[1] = id_num[1] % 10;
                id_num[0] = id_num[0] % 10;
            } else {
                break;
            }
        }
        return id;
    }
    public static String getDiaChiCanCuoc(String manguon) {
        int[] id_num = {0, 0, 1};
        String id = null;
        while (true) {
            id = manguon + id_num[0] + id_num[1] + id_num[2];
            if (CheckID(id)) {
                id_num[2] = id_num[2] + 1;
                id_num[1] = id_num[1] + id_num[2] / 10;
                id_num[0] = id_num[0] + id_num[1] / 10;

                id_num[2] = id_num[2] % 10;
                id_num[1] = id_num[1] % 10;
                id_num[0] = id_num[0] % 10;
            } else {
                break;
            }
        }
        return id;
    }
    public static String getDiaChi() {
        int[] id_num = {0, 0, 0, 1};
        String id = null;
        while (true) {
            id = "D" + id_num[0] + id_num[1] + id_num[2]+ id_num[3];
            if (CheckID(id)) {
                id_num[3] = id_num[3] + 1;
                id_num[2] = id_num[2] + id_num[3] / 10;
                id_num[1] = id_num[1] + id_num[2] / 10;
                id_num[0] = id_num[0] + id_num[1] / 10;

                id_num[3] = id_num[3] % 10;
                id_num[2] = id_num[2] % 10;
                id_num[1] = id_num[1] % 10;
                id_num[0] = id_num[0] % 10;
            } else {
                break;
            }
        }
        return id;
    }
    public void insertDiaChi_CN(diachi dc) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DIACHICN)) {

            preparedStatement.setString(1, DiaChiChiNhanh());
            preparedStatement.setString(2, dc.getTinhtp());
            preparedStatement.setString(3, dc.getQuanhuyen());
            preparedStatement.setString(4, dc.getPhuongxa());
            preparedStatement.setString(5, dc.getSonha());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    public static void insertDiaChi(diachi dc) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DIACHICN)) {

            preparedStatement.setString(1, dc.getMadc());
            preparedStatement.setString(2, dc.getTinhtp());
            preparedStatement.setString(3, dc.getQuanhuyen());
            preparedStatement.setString(4, dc.getPhuongxa());
            preparedStatement.setString(5, dc.getSonha());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
}
