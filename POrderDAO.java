package project.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import project.model.POrder;
import project.util.DbUtil;

public class POrderDAO {
	public int addOrder(POrder order) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.makeConnection();
			String sql = "INSERT INTO `be4furniture`.`order` (`userId`, `submitDate`, `approve`) VALUES (?, ?, ?);";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// set parameters
			ps.setInt(1, order.getUserId());
			ps.setDate(2, new Date(System.currentTimeMillis()));
			ps.setBoolean(3, order.isApprove());

			// execute and get result SET
			ps.execute();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int insertedId = rs.getInt(1);
				return insertedId;
			}
			return 0;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
}
