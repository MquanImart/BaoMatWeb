package DAO;

import JDBCUtils.JDBCUtils;
import Model.chucvu;
import Model.congtac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class chucvuDAO {
    private static final String SELECT_ALL = "select * from chucvu where matk = ?";
    private static final String SELECT_MATK = "select matk from chucvu where tentk = ?";
    private  static  final String INSERT_CHUCVU =  "INSERT INTO chucvu" + "  (matk, tentk) VALUES " + " (?, ?);";

    public static int CapBacQuyenHan(String matk) {
        int capbac = 0;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            preparedStatement.setString(1, matk);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                if (Objects.equals(rs.getString("tentk"), "Trưởng Phòng") && capbac < 1){
                    capbac = 1;
                } else if (Objects.equals(rs.getString("tentk"), "Giám Đốc") && capbac < 2) {
                    capbac = 2;
                } else if (Objects.equals(rs.getString("tentk"), "Quản Trị") && capbac < 3) {
                    capbac = 3;
                }
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return capbac;
    }

    public static String TenCapBac(String matk) {
        String tencapbac = null;
        int capbac = 0;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            preparedStatement.setString(1, matk);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                tencapbac = rs.getString("tentk");
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return tencapbac;
    }
    public static void ThemChucVu(chucvu chucvu){
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CHUCVU);) {
            preparedStatement.setString(1, chucvu.getMatk());
            preparedStatement.setString(2, chucvu.getTentk());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    public static String GetAdmin(){
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MATK)) {
            preparedStatement.setString(1, "Quản Trị");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return  rs.getString("matk");
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return null;
    }
}
