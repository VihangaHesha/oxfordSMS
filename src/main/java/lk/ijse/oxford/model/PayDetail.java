package lk.ijse.oxford.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PayDetail {
    private String payId;
    private String stId;
    private double fee;
    private Date date;
}