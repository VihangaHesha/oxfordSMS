package lk.ijse.oxford.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendMarking {

    private String attendId;
    private Date date;
    private String attendMark;
    private String  stId;
}
