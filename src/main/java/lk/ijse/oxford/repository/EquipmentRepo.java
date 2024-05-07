package lk.ijse.oxford.repository;

import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.model.Equipment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentRepo {

        public static List<Equipment> getAll() throws SQLException {
            String sql = "SELECT * FROM equipment";

            PreparedStatement pstm = DbConnection.getInstance().getConnection()
                    .prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery();

            List<Equipment> equipmentList = new ArrayList<>();
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String desc = resultSet.getString(2);
                int qty = Integer.parseInt(resultSet.getString(3));


                Equipment equipment = new Equipment(id,desc,qty);
                equipmentList.add(equipment);
            }
            return equipmentList;
        }

    public static boolean save(Equipment equipment) throws SQLException {
        String sql ="INSERT INTO Equipment VALUES (?,?,?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,equipment.getEquipId());
        pstm.setObject(2,equipment.getDescription());
        pstm.setObject(3,equipment.getQty());


        return pstm.executeUpdate() > 0;

    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Equipment WHERE EquipId =?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Equipment equipment) throws SQLException {

        String sql = "UPDATE Student SET Description = ? , Qty = ?  WHERE EquipId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,equipment.getDescription());
        pstm.setObject(2,equipment.getQty());
        pstm.setObject(3,equipment.getEquipId());

        return pstm.executeUpdate() > 0;
    }

    public static int getEquipmentCount() throws SQLException {
        String sql ="SELECT COUNT(*) equipment_count FROM Equipment";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        int equipmentCount=0;
        if (resultSet.next()){
            equipmentCount = resultSet.getInt("equipment_count");
        }
        return equipmentCount;
    }
}

