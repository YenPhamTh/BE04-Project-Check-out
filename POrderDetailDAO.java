package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import project.model.POrderDetail;
import project.util.DbUtil;

public class POrderDetailDAO {
	public boolean inputOrderToDB(POrderDetail orderDetail) throws SQLException {
		// connect to DB
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// make connection to mySQL
			conn = DbUtil.makeConnection();
			
			//--> table Category -->
			// Run query "Select * from category"
			ps = conn.prepareStatement("INSERT INTO `be4furniture`.`order_detail` (`id`,`productId`) VALUES (?, ?);");
			ps.setInt(1, orderDetail.getOrderId());
			ps.setInt(2, orderDetail.getProductId());
			
			// execute and get result SET
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (ps !=null) {
				ps.close();
			}
			if (conn !=null) {
				conn.close();
			}
		}
		return true;
	}
}
