package lk.ijse.oxford.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MarkAttendance {
    private AttendMarking attendMarking;
    private CheckPayment checkPayment;
}
