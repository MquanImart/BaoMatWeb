package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import JDBCUtils.JDBCUtils;
import Model.taikhoan;
public class changeDAO {

    public boolean changePassword(taikhoan tk, String newPassword) throws ClassNotFoundException {
        boolean rowUpdated = false;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("update taikhoan set pass = ? where username = ? and pass = ?")) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, tk.getUsername());
            preparedStatement.setString(3, tk.getPass());

            System.out.println(preparedStatement);
            boolean isResultSet = preparedStatement.execute();
            if (!isResultSet) {
                int rowsAffected = preparedStatement.getUpdateCount();
                rowUpdated = rowsAffected > 0;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return rowUpdated;
    }
}