package lk.ijse.oxford.repository;

import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.model.CheckPayment;
import lk.ijse.oxford.model.PayDetail;
import lk.ijse.oxford.model.Payment;
import lk.ijse.oxford.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepo {
    public static boolean getFromPayId(CheckPayment checkPayment) throws SQLException {
        String sql = "SELECT DISTINCT StId AS StudentID FROM Payment WHERE MONTH(Date) = ? AND StId = ?";

        // Prepare the statement
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        // Set the parameters correctly
        pstm.setInt(1, checkPayment.getMonth());
        pstm.setString(2, checkPayment.getStId());

        // Execute the query
        ResultSet resultSet = pstm.executeQuery();

        // Check if the student ID exists in the result set
        if (resultSet.next()) {
            String id = resultSet.getString(1);
            if (id.equals(checkPayment.getStId())) {
                return true;
            }
        }
        return false;
    }



    public static String currentId() throws SQLException {
        String sql = "SELECT PayId FROM Payment ORDER BY PayId desc LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public static List<PayDetail> getAll() throws SQLException {
        String sql = "SELECT * FROM Payment ORDER BY PayId desc";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<PayDetail>payDetailList = new ArrayList<>();
        while (resultSet.next()) {
            String payId = resultSet.getString(3);
            double fee = Double.parseDouble(resultSet.getString(4));
            Date date = Date.valueOf(resultSet.getString(2));
            String stId = resultSet.getString(1);

            PayDetail payDetail = new PayDetail(payId,stId,fee,date);
            payDetailList.add(payDetail);
        }
        return payDetailList;
    }

    public static boolean save(Payment payment) throws SQLException {
        String sql = "INSERT INTO Payment VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setString(1, payment.getPayId());
        pstm.setString(3, payment.getStId());
        pstm.setDate(2, payment.getDate());
        pstm.setDouble(4, payment.getAmount());

        return pstm.executeUpdate() > 0;
    }

    public static int getTotalPayments() throws SQLException {
        String sql = "SELECT SUM(amount) AS total_payment FROM Payment";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        int totPayment= 0;
        if (resultSet.next()){
            totPayment=resultSet.getInt("total_payment");
        }
        return totPayment;
    }
}
