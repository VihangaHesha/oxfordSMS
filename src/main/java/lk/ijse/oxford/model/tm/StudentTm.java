package lk.ijse.oxford.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentTm {
    private String stId;
    private String grade;
    private String name;
    private String contact;
    private String address;
    private String userId;
}
