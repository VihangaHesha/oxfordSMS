package lk.ijse.oxford.repository;

import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.model.Payment;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentRepo {

    public static boolean save(Payment payment) throws SQLException {
        String sql ="INSERT INTO Payment VALUES (?,?,?,'Cash',?,?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,payment.getPayId());
        pstm.setObject(2,payment.getAmount());
        pstm.setObject(3,payment.getDate());
        pstm.setObject(4,payment.getSubject());
        pstm.setObject(5,payment.getStId());

        return pstm.executeUpdate() > 0;

    }
}
