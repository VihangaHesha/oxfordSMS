package lk.ijse.oxford.repository;

import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.model.PaymentDetails;
import lk.ijse.oxford.model.PlacePayment;
import lk.ijse.oxford.model.Subject;

import java.sql.Connection;
import java.sql.SQLException;

public class PlacePaymentRepo {

    public static boolean placePayment(PlacePayment po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isPaymentSaved = PaymentRepo.save(po.getPayment());
            if (isPaymentSaved) {
                boolean isPaymentDetailSaved = PaymentDetailsRepo.save(po.getPdList());
                if (isPaymentDetailSaved) {
                    boolean isSubjectSeatsUpdate = SubjectRepo.updateSeats(po.getPdList());
                    if (isSubjectSeatsUpdate) {
                        connection.commit();
                        return true;
                    }
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
