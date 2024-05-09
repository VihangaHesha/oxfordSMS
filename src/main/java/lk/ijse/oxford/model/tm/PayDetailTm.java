package lk.ijse.oxford.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PayDetailTm {
    private String payId;
    private String stId;
    private double fee;
    private Date date;
}
