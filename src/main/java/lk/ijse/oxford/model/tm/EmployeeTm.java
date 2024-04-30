package lk.ijse.oxford.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeTm {
    private String empId;
    private String name;
    private String contact;
    private String address;
    private String type;
    private String userId;
}
