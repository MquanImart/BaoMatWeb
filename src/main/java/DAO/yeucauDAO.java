package DAO;

import JDBCUtils.JDBCUtils;
import Model.nhanvien;
import Model.yeucau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class yeucauDAO {

    private static final String SELECT_ALL_NGGUI = "select * from yeucau where matk = ?";
    private static final String SELECT_ALL_NGNHAN = "select * from yeucau where nguoinhan = ?";
    private static final String UPDATE_TINHTRANG = "update yeucau set tinhtrang =? where mayeucau = ?;";
    private static final String CHECK_MAYC = "select * from yeucau where mayeucau = ?";
    private static final String INSERT_YEUCAU = "INSERT INTO yeucau" + "  (mayeucau, matk, ngaygui, nguoinhan,  congviec, mapb, tinhtrang) VALUES " + " (?, ?, ?, ?, ?, ?, ?);";
    private static List< yeucau > DanhSachYeuCau(String matk, String SQL) {

        List < yeucau > listyeucau = new ArrayList< >();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);) {
            preparedStatement.setString(1, matk);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String mayc = rs.getString("mayeucau");
                String manguoinhan = rs.getString("matk");
                LocalDate ngaygui = rs.getDate("ngaygui").toLocalDate();
                String nguoinhan = rs.getString("nguoinhan");
                String congviec = rs.getString("congviec");
                String mapb = rs.getString("mapb");
                String tinhtrang = rs.getString("tinhtrang");
                listyeucau.add(new yeucau(mayc,manguoinhan,ngaygui,nguoinhan,congviec,mapb,tinhtrang));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return listyeucau;
    }
    public static List< yeucau > DanhSachYeuCau_TruongPhong(String matk) {
        return DanhSachYeuCau(matk, SELECT_ALL_NGGUI);
    }
    public static List< yeucau > DanhSachYeuCau_GiamDoc(String matk) {
        return DanhSachYeuCau(matk, SELECT_ALL_NGNHAN);
    }
    public static void Update_tinhtrang(String tinhtrang, String mayeucau){
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TINHTRANG)) {
            preparedStatement.setString(1, tinhtrang);
            preparedStatement.setString(2, mayeucau);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    public static void Update_tinhtrang_yes(String mayeucau){
        Update_tinhtrang("chấp nhận",mayeucau);
    }
    public static void Update_tinhtrang_no(String mayeucau){
        Update_tinhtrang("từ chối",mayeucau);
    }
    public static boolean CheckID(String id){
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_MAYC);) {
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
    public static String getMaYeuCau() {
        int[] id_num = {0, 0, 1};
        String id = null;
        while (true) {
            id = "YC" + id_num[0] + id_num[1] + id_num[2];
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
    public static void ThemYeuCau(yeucau yc){
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_YEUCAU);) {
            preparedStatement.setString(1, yc.getMayeucau());
            preparedStatement.setString(2, yc.getMatk());
            preparedStatement.setDate(3, JDBCUtils.getSQLDate(yc.getNgaygui()));
            preparedStatement.setString(4, yc.getNguoinhan());
            preparedStatement.setString(5, yc.getCongviec());
            preparedStatement.setString(6, yc.getMapb());
            preparedStatement.setString(7, yc.getTinhtrang());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
}