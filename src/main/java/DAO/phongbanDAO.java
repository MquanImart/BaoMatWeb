package DAO;

import JDBCUtils.JDBCUtils;
import Model.nhanvien;
import Model.phongban;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class phongbanDAO {


    private static final String SELECT_ALL = "select * from phongban";
    private static final String SELECT_ALL_CN = "select * from phongban where macn = ?";
    private static final String SELECT_ALL_PB = "select * from phongban where mapb = ?";
    private static final String DELETE_PB_BY_MAPB = "delete from phongban where mapb = ?;";

    private static final String SELECT_PB_BY_MAPB = "select * from phongban where mapb = ?";

    private static final String SELECT_TRUONGPHONG = "select mapb from phongban where matrphong = ?;";

    private static final String UPDATE_PB = "update phongban set tenpb = ?, macn = ?, matrphong =?, mapbtr = ? where mapb = ?;";
    private static final String INSERT_PB_BY_MAPB = "INSERT INTO phongban (mapb, tenpb, macn, matrphong, ngaytao, mapbtr) VALUES (?, ?, ?, ?, ?, ?)";

    public static List< phongban > selectAllphongban() {

        List < phongban > listphongban = new ArrayList< >();

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String mapb = rs.getString("mapb");
                String tenpb = rs.getString("tenpb");
                String macn = rs.getString("macn");
                String matrphong = rs.getString("matrphong");
                LocalDate ngaytao = rs.getDate("ngaytao").toLocalDate(); // Use java.util.Date
                String mapbtr = rs.getString("mapbtr");

                listphongban.add(new phongban(mapb, tenpb, macn, matrphong, ngaytao, mapbtr));

            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return listphongban;
    }
    public static List< phongban > selectAllphongban_CN(String machinhanh) {

        List < phongban > listphongban = new ArrayList< >();

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CN);) {
            preparedStatement.setString(1,machinhanh);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String mapb = rs.getString("mapb");
                String tenpb = rs.getString("tenpb");
                String macn = rs.getString("macn");
                String matrphong = rs.getString("matrphong");
                LocalDate ngaytao = rs.getDate("ngaytao").toLocalDate(); // Use java.util.Date
                String mapbtr = rs.getString("mapbtr");

                listphongban.add(new phongban(mapb, tenpb, macn, matrphong, ngaytao, mapbtr));

            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return listphongban;
    }

    public static List< phongban > selectAllphongban_PB(String maphongban) {

        List < phongban > listphongban = new ArrayList< >();

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PB);) {
            preparedStatement.setString(1,maphongban);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String mapb = rs.getString("mapb");
                String tenpb = rs.getString("tenpb");
                String macn = rs.getString("macn");
                String matrphong = rs.getString("matrphong");
                LocalDate ngaytao = rs.getDate("ngaytao").toLocalDate(); // Use java.util.Date
                String mapbtr = rs.getString("mapbtr");

                listphongban.add(new phongban(mapb, tenpb, macn, matrphong, ngaytao, mapbtr));

            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return listphongban;
    }
    public boolean deletePhongBan(String mapb) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PB_BY_MAPB);) {
            statement.setString(1, mapb);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    public void insertPhongBan(phongban pb) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PB_BY_MAPB)) {

            preparedStatement.setString(1, pb.getMapb());
            preparedStatement.setString(2, pb.getTenpb());
            preparedStatement.setString(3, pb.getMacn());
            preparedStatement.setString(4, pb.getMatrphong());
            preparedStatement.setDate(5, JDBCUtils.getSQLDate(pb.getNgaytao()));
            preparedStatement.setString(6, pb.getMapbtr());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    public boolean updatePhongBan(phongban pb) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PB);) {
            statement.setString(1, pb.getTenpb());
            statement.setString(2, pb.getMacn());
            statement.setString(3, pb.getMatrphong());
            statement.setString(4, pb.getMapbtr());
            statement.setString(5, pb.getMapb());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public static phongban selectPhongBan(String Mapb) {
        phongban pb = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PB_BY_MAPB);) {
            preparedStatement.setString(1, Mapb);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String mapb = rs.getString("mapb");
                String tenpb = rs.getString("tenpb");
                String macn = rs.getString("macn");
                String matrphong = rs.getString("matrphong");
                LocalDate ngaytao = rs.getDate("ngaytao").toLocalDate(); // Use java.util.Date
                String mapbtr = rs.getString("mapbtr");

                pb = new phongban(mapb, tenpb, macn, matrphong, ngaytao, mapbtr);

            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return pb;
    }
    public static String LayMaPB(String matrphong) throws SQLException {
        String result = null;
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_TRUONGPHONG);) {
            statement.setString(1, matrphong);
            ResultSet rs = statement.executeQuery();
            System.out.println(statement);
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                result = rs.getString("mapb");
            }
        }
        return result;
    }
    public static List<String> Selected_PB_BY_CN(String maCN) {
        List<String> PBNames = new ArrayList<>();

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
             // Step 2: Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CN);) {
            System.out.println(preparedStatement);
            if (maCN != null) {
                preparedStatement.setString(1, maCN);
            }
            // Step 3: Execute the query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String macn = rs.getString("mapb"); // Replace "tennhanvien" with the actual column name for the employee's name
                PBNames.add(macn);
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return PBNames;
    }
}