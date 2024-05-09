package lk.ijse.oxford.repository;

import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.model.PaymentDetails;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PaymentDetailsRepo {

    public static boolean save(List<PaymentDetails> pdList) throws SQLException {
        for (PaymentDetails pd : pdList) {
            if(!save(pd)) {
                return false;
            }
        }
        return true;
    }
    private static boolean save(PaymentDetails pd) throws SQLException {
        String sql = "INSERT INTO Payment_Details VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setString(1, pd.getPayId());
        pstm.setString(2, pd.getSubId());
        pstm.setDouble(3, pd.getTotalFee());
        pstm.setInt(4, pd.getSeats());

        return pstm.executeUpdate() > 0;
    }
}
