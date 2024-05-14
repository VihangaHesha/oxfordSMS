package lk.ijse.oxford.repository;

import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.model.Classroom;
import lk.ijse.oxford.model.Equipment;
import lk.ijse.oxford.model.PaymentDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassroomRepo {
    public static List<Classroom> getAll() throws SQLException {
        String sql = "SELECT * FROM classroom";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Classroom> classroomList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String desc = resultSet.getString(2);
            int capacity = Integer.parseInt(resultSet.getString(3));
            String subId = resultSet.getString(4);


            Classroom classroom = new Classroom(id,desc,capacity,subId);
            classroomList.add(classroom);
        }
        return classroomList;
    }

    public static boolean save(Classroom classroom) throws SQLException {
        String sql ="INSERT INTO Classroom VALUES (?,?,?,?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,classroom.getClassId());
        pstm.setObject(2,classroom.getDescription());
        pstm.setObject(3,classroom.getCapacity());
        pstm.setObject(4,classroom.getSubId());


        return pstm.executeUpdate() > 0;
    }

    public static int getClassCount() throws SQLException {
        String sql = "SELECT COUNT(ClassId) AS total_class_count FROM Classroom";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        int classCount=0;
        if (resultSet.next()){
            classCount = resultSet.getInt("total_class_count");
        }
        return classCount;
    }

    public static int getClassCapacity() throws SQLException {
        String sql = "SELECT SUM(Capacity) AS total_capacity FROM Classroom";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        int totCapacity= 0;
        if (resultSet.next()){
            totCapacity=resultSet.getInt("total_capacity");
        }
        return totCapacity;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Classroom WHERE ClassId =?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Classroom classroom) throws SQLException {
        String sql = "UPDATE Classroom SET Description = ? , Capacity = ?, SubId=? WHERE ClassId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,classroom.getDescription());
        pstm.setObject(2,classroom.getCapacity());
        pstm.setObject(3,classroom.getSubId());
        pstm.setObject(4,classroom.getClassId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateSeats(List<PaymentDetails> pdList) throws SQLException {
        for (PaymentDetails od : pdList) {
            if(!updateSeates(od)) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateSeates(PaymentDetails od) throws SQLException {
        String sql = "update Classroom set Capacity=Capacity-? where SubId=?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setInt(1, od.getSeats());
        pstm.setString(2, od.getSubId());

        return pstm.executeUpdate() > 0;
    }

    public static String currentId() throws SQLException {
        String sql = "SELECT ClassId FROM Classroom ORDER BY ClassId desc LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
