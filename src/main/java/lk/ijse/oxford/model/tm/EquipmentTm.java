package lk.ijse.oxford.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipmentTm {
    private String equipId;
    private String description;
    private int qty;
}
