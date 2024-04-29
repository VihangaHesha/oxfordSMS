package lk.ijse.oxford.repository;

import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.model.Employee;
import lk.ijse.oxford.model.Salary;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeRepo {
    public static List<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM employee";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String contact = resultSet.getString(4);
            String type = resultSet.getString(5);
            String userId = resultSet.getString(6);

            Employee customer = new Employee(id,name,address,contact,type,userId);
            employeeList.add(customer);
        }
        return employeeList;
    }

    public static boolean save(Employee employee) throws SQLException {
        String sql ="INSERT INTO Employee VALUES (?,?,?,?,?,?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,employee.getEmpId());
        pstm.setObject(2,employee.getName());
        pstm.setObject(4,employee.getContact());
        pstm.setObject(5,employee.getAddress());
        pstm.setObject(3,employee.getType());
        pstm.setObject(6,employee.getUserId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {

        String sql = "DELETE FROM Employee WHERE StId =?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE Student SET Name = ? , Type = ? , Contact = ? , Address = ? ,UserId = ? WHERE EmpId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,employee.getName());
        pstm.setObject(2,employee.getType());
        pstm.setObject(3,employee.getContact());
        pstm.setObject(4,employee.getAddress());
        pstm.setObject(5,employee.getUserId());
        pstm.setObject(6,employee.getEmpId());

        return pstm.executeUpdate() > 0;
    }

    public static Employee searchByCode(String empId) throws SQLException {

        String sql = "SELECT * From  Employee  where EmpId=?;";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, empId);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }
        return null;
    }
}
