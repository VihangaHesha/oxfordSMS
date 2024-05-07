package lk.ijse.oxford.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TimeTable {
    private String TimeId;
    private String TimePeriod;
    private String Subject;
    private java.sql.Date Date;
}
