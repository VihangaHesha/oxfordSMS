package lk.ijse.oxford.repository;

import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.model.Attendance;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceRepo {
    public static Attendance searchById(String id) throws SQLException {

        String sql = "SELECT s.Name,a.Date,a.AttendMark,a.AttendId,a.StId FROM attendance a LEFT JOIN  Student s ON a.StId = s.StId WHERE a.StId= ?;";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        Attendance attendance = null;

        if (resultSet.next()) {
            String stId = resultSet.getString(1);
            Date date = Date.valueOf(resultSet.getString(2));
            String attendMark = resultSet.getString(3);
            String attendId = resultSet.getString(4);
            String name = resultSet.getString(5);


            attendance = new Attendance(name, date, attendMark,attendId,stId);
        }

        return attendance;
    }
}
