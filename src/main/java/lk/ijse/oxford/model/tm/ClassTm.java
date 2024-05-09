package lk.ijse.oxford.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClassTm {
    private String classId;
    private String description;
    private int capacity;
    private String subId;
}
