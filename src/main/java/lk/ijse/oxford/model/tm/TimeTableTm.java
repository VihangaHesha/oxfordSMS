package lk.ijse.oxford.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class TimeTableTm {
    private String TimeId;
    private String TimePeriod;
    private String Subject;
    private java.sql.Date Date;
}
