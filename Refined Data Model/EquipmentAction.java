package brew_day;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipmentAction {
	public static void main(String args[]) throws Exception {
		Equipment equip = new Equipment();
		equip.setName("Kang");
		equip.setCapacity(12.00);
		
		EquipmentAction ea = new EquipmentAction();
		ea.createEquipment(equip);
	}
	
	public void createEquipment(Equipment equip) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"insert into equipment" + 
					" (name, type, capacity)" +
					" values(?, ?, ?)";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, equip.getName());
		ptmt.setString(2, equip.getType());
		ptmt.setDouble(3, equip.getCapacity());
		ptmt.execute();
	}
	
	// read equipment
	public Equipment readEquipment(int id) throws SQLException {
		Equipment equip= null;
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"select *  from equipment" + 
					" where id = ?";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		
		while(rs.next()) {
			equip = new Equipment();
			equip.setName(rs.getString("name"));
			equip.setType(rs.getString("type"));
			equip.setCapacity(rs.getDouble("capacity"));
		}
		return equip;
	}
	
	// update equipment
	public void updateEquipment(Equipment equip) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"update equipment" + 
					" set name = ?, type = ?, capacity = ?" +
					" where id = ?";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, equip.getName());
		ptmt.setString(2, equip.getType());
		ptmt.setDouble(3, equip.getCapacity());
		ptmt.execute();
	}
	
	// delete equipment
	public void deleteEquipment(int id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					"delete from equipment" + 
					" where id = ?";

		// Store sql in prepared statement for later execution
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
	}
}
