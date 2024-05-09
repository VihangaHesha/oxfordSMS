package lk.ijse.oxford.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlacePayment {
    private Payment payment;
    private List<PaymentDetails> pdList;
}
