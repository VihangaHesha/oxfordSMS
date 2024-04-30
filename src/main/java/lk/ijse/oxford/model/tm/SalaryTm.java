package lk.ijse.oxford.model.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalaryTm {
    private String salaryId;
    private double amount;
    private Date date;
    private String EmpId;

}

