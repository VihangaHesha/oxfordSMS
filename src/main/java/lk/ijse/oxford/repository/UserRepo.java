package lk.ijse.oxford.repository;

import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.model.TimeTable;
import lk.ijse.oxford.model.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    public static boolean updateName(String uId, String name) throws SQLException {
        String sql = "UPDATE User SET Name = ?  WHERE UserId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,name);
        pstm.setObject(2,uId);

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateContact(String id, String contact) throws SQLException {
        String sql = "UPDATE User SET Contact = ?  WHERE UserId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,contact);
        pstm.setObject(2,id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean updatePW(String id, String pw) throws SQLException {
        String sql = "UPDATE User SET Password = ?  WHERE UserId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,pw);
        pstm.setObject(2,id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateEmail(String id, String email) throws SQLException {
        String sql = "UPDATE User SET Email = ?  WHERE UserId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,email);
        pstm.setObject(2,id);

        return pstm.executeUpdate() > 0;
    }

    public static List<User> getAll(String uId) throws SQLException {
        String sql = "SELECT Name,Password,Contact,Email FROM User WHERE UserId =?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,uId);
        ResultSet resultSet = pstm.executeQuery();

        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString(1);
            String pw = resultSet.getString(2);
            String contact = resultSet.getString(3);
            String email = resultSet.getString(4);

            User user = new User(name, pw,contact,email);
            userList.add(user);
        }
        return userList;
    }
}
