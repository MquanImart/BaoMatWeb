package DAO;

import JDBCUtils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class testdao {

    public static void main(String[] args) throws ClassNotFoundException {
        try {
            if (validate()) {
                System.out.println("pass");
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static boolean validate() throws ClassNotFoundException {
        boolean status = false;

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from nhanvien where matk = ?")) {
            preparedStatement.setString(1, "N9999");

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }
}