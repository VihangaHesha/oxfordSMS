package lk.ijse.oxford.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendMarking {

    private String attendId;
    private String attendMark;
    private String  stId;
    private String date;
}
