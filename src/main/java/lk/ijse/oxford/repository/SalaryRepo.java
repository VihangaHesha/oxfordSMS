package lk.ijse.oxford.repository;

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
        String sql = "SELECT * FROM Salary";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Salary> salaryList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            double amount = Double.parseDouble(resultSet.getString(2));
            Date date = Date.valueOf(resultSet.getString(3));
            String empId = resultSet.getString(4);

            Salary salary = new Salary(id,amount,date,empId);
            salaryList.add(salary);
        }
        return salaryList;
    }

    public static Salary searchByCode(String empId) throws SQLException {
        String sql = "SELECT * From  Salary  where EmpId=?;";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, empId);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return new Salary(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getDate(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }
}
