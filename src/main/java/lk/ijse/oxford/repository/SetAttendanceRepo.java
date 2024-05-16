package lk.ijse.oxford.repository;

import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.model.AttendMarking;
import lk.ijse.oxford.model.CheckPayment;
import lk.ijse.oxford.model.MarkAttendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetAttendanceRepo {


    public static boolean markAttendance(MarkAttendance markAttendance) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isChecked = AttendanceRepo.getFromPayId(markAttendance.getCheckPayment());
            if (isChecked) {
                System.out.println(isChecked);
                boolean isAttendMarked = AttendanceRepo.save(markAttendance.getAttendMarking());
                if (isAttendMarked) {
                        System.out.println(isAttendMarked);
                        connection.commit();
                        return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
