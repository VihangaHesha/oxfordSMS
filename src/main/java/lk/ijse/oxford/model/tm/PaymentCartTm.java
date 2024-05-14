
package lk.ijse.oxford.model.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentCartTm {
    private String id;
    private String payId;
    private String desc;
    private double fee;
    private double total;
    private int ableSeats;
    private JFXButton btnRemove;
}

