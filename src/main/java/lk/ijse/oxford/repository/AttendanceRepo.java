package lk.ijse.oxford.repository;

import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.model.AttendMarking;
import lk.ijse.oxford.model.Attendance;
import lk.ijse.oxford.model.CheckPayment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRepo {
    public static Attendance searchById(String id) throws SQLException {

        String sql = "SELECT s.Name,a.Date,a.AttendMark,a.AttendId,a.StId FROM attendance a LEFT JOIN  Student s ON a.StId = s.StId WHERE a.StId= ?";
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


            attendance = new Attendance(stId,date,attendMark,attendId,name);
        }

        return attendance;
    }

    public static List<Attendance> getAll() throws SQLException {
        String sql = "SELECT s.Name,a.Date,a.AttendMark,a.AttendId,a.StId FROM attendance a LEFT JOIN  Student s ON a.StId = s.StId";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Attendance>attendanceList = new ArrayList<>();
        Attendance attendance = null;
        while (resultSet.next()) {
            String stId = resultSet.getString(1);
            Date date = Date.valueOf(resultSet.getString(2));
            String attendMark = resultSet.getString(3);
            String attendId = resultSet.getString(4);
            String name = resultSet.getString(5);

            attendance = new Attendance(name, date, attendMark,stId,attendId);
            attendanceList.add(attendance);
        }
        return attendanceList;
    }

    public static int getAttendanceCount(LocalDate date) throws SQLException {
        String sql = "SELECT COUNT(*) AS attend_count FROM Attendance WHERE AttendMArk='I' AND Date =?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
       pstm.setObject(1,date);
        ResultSet resultSet = pstm.executeQuery();

        int attendCount= 0;
        if (resultSet.next()){
            attendCount=resultSet.getInt("attend_count");
        }
        return attendCount;
    }

    public static String currentId() throws SQLException {
        String sql = "SELECT AttendId FROM Attendance ORDER BY AttendId desc LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public static boolean save(AttendMarking attendMarking) throws SQLException {
        String sql = "INSERT INTO Attendance VALUES (?,?,?,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, attendMarking.getAttendId());
        pstm.setDate(2, attendMarking.getDate());
        pstm.setString(3, attendMarking.getAttendMark());
        pstm.setString(4, attendMarking.getStId());

        return pstm.executeUpdate() > 0;
    }

}
