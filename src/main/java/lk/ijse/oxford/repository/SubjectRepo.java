package lk.ijse.oxford.repository;

import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.model.PaymentDetails;
import lk.ijse.oxford.model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepo {

    public static List<String> getIds() throws SQLException {

        String sql = "SELECT description FROM Subject";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> subList = new ArrayList<>();

        while (resultSet.next()) {
            subList.add(resultSet.getString(1));
        }
        return subList;
    }

    public static Subject searchByName(String subName) throws SQLException {
        String sql = "SELECT SubId,Description,FeeAmount,AvailableSeats FROM Subject WHERE Description = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, subName);
        ResultSet resultSet = pstm.executeQuery();

        Subject subject = null;

        if (resultSet.next()) {
            String subId = resultSet.getString(1);
            String desc = resultSet.getString(2);
            double feeAmount = Double.parseDouble(resultSet.getString(3));
            int ableSeats = Integer.parseInt(resultSet.getString(4));

            subject = new Subject(subId,desc,feeAmount,ableSeats);
        }
        return subject;
    }

    public static boolean updateSeats(List<PaymentDetails> pdList) throws SQLException {
        for (PaymentDetails od : pdList) {
            if(!updateQty(od)) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(PaymentDetails od) throws SQLException {
        String sql = "UPDATE Subject SET AvailableSeats = AvailableSeats - ? WHERE SubId = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setInt(1, od.getSeats());
        pstm.setString(2, od.getSubId());

        return pstm.executeUpdate() > 0;
    }
}
