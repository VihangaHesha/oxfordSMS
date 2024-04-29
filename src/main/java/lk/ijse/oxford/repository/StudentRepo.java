package lk.ijse.oxford.repository;

import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.model.Attendance;
import lk.ijse.oxford.model.Student;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepo {
    public static List<Student> getAll() throws SQLException {
        String sql = "SELECT * FROM Student";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Student>studentList = new ArrayList<>();
        while (resultSet.next()) {
            String stId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String grade = resultSet.getString(3);
            String contact = resultSet.getString(4);
            String address = resultSet.getString(5);
            String userId = resultSet.getString(6);

            Student student = new Student(stId,userId,name,contact,address,grade);
            studentList.add(student);
        }
        return studentList;
    }

    public static boolean save(Student student) throws SQLException {
        String sql ="INSERT INTO Student VALUES (?,?,?,?,?,?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,student.getStId());
        pstm.setObject(2,student.getName());
        pstm.setObject(3,student.getGrade());
        pstm.setObject(4,student.getContact());
        pstm.setObject(5,student.getAddress());
        pstm.setObject(6,student.getUserId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {

        String sql = "DELETE FROM Student WHERE StId =?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Student student) throws SQLException {

        String sql = "UPDATE Student SET Name = ? , Grade = ? , Contact = ? , Address = ? ,UserId = ? WHERE StId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,student.getName());
        pstm.setObject(2,student.getGrade());
        pstm.setObject(3,student.getContact());
        pstm.setObject(4,student.getAddress());
        pstm.setObject(5,student.getUserId());
        pstm.setObject(6,student.getStId());

        return pstm.executeUpdate() > 0;
    }


}
