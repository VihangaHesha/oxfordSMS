package lk.ijse.oxford.repository;

import javafx.collections.ObservableList;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.model.Employee;
import lk.ijse.oxford.model.Salary;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryRepo {
    public static List<Salary> getAll() throws SQLException {
        String sql = "SELECT s.SalaryId,s.Amount,s.Date,s.EmpId,e.Name FROM Salary s JOIN Employee e ON s.EmpId=e.EmpId";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Salary> salaryList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String amount = String.valueOf(Double.parseDouble(resultSet.getString(2)));
            Date date = Date.valueOf(resultSet.getString(3));
            String empId = resultSet.getString(4);
            String name = resultSet.getString(5);

            Salary salary = new Salary(id,amount,date,empId,name);
            salaryList.add(salary);
        }
        return salaryList;
    }

    public static Salary searchByCode(String empId) throws SQLException {
        String sql = "SELECT * From  Salary s JOIN Employee e ON s.EmpId=e.EmpId WHERE EmpId=?;";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, empId);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return new Salary(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }

    public static int getTotalSalary() throws SQLException {
        String sql = "SELECT SUM(Amount) AS total_salary FROM Salary";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        int totSalary= 0;
        if (resultSet.next()){
            totSalary=resultSet.getInt("total_salary");
        }
        return totSalary;
    }
}
