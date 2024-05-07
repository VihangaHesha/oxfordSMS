package lk.ijse.oxford.repository;

import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.model.TimeTable;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimeTableRepo {
    public static List<TimeTable> getAll() throws SQLException {
        String sql = "SELECT * FROM timetable";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<TimeTable> timeTableList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String timePeriod = resultSet.getString(2);
            String subject = resultSet.getString(3);
            Date date = Date.valueOf(resultSet.getString(4));

            TimeTable tb = new TimeTable(id, timePeriod,subject,date);
            timeTableList.add(tb);
        }
        return timeTableList;
    }
}
