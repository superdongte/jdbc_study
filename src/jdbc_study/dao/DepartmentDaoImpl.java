package jdbc_study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jdbc_study.dto.Department;
import jdbc_study.jdbc.MySQLJdbcUtil;

public class DepartmentDaoImpl implements DepartmentDao {
	Logger LOG = LogManager.getLogger();

	@Override
	public List<Department> selectDepartmentByAll() {
		List<Department> list = new ArrayList<>();
		String sql = "select deptno, deptname, floor from department";
		try (Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			LOG.debug(pstmt);
			while (rs.next()) {
				list.add(getDepatment(rs));
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	private Department getDepatment(ResultSet rs) throws SQLException {
		int deptno = rs.getInt("deptno");
		String deptname = rs.getString("deptname");
		int floor = rs.getInt("floor");
		return new Department(deptno, deptname, floor);
	}

	@Override
	public int insertDepartment(Department department) throws SQLException {
		String sql = "insert into department values(?,?,?)";
		int res = 0;

		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, department.getDeptNo());
			pstmt.setString(2, department.getDeptName());
			pstmt.setInt(3, department.getFloor());
			LOG.debug(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int deleteDepartment(Department department) throws SQLException {
		String sql2 = "Delete from department where deptno=?";
		int res1 = 0;

		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql2);) {
			pstmt.setInt(1, department.getDeptNo());
			LOG.debug(pstmt);
			res1 = pstmt.executeUpdate();
		}
		return res1;
	}

	@Override
	public int updateDepartment(Department department) throws SQLException {
		String sql3 = "update department set deptname = ?, floor = ? where deptno=?";
		int res3 = 0;

		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql3);) {
			pstmt.setInt(3, department.getDeptNo());
			pstmt.setString(1, department.getDeptName());
			pstmt.setInt(2, department.getFloor());
			LOG.debug(pstmt);
			res3 = pstmt.executeUpdate();
		}
		return res3;
	}

	@Override
	public Department selectDepartmentByNo(Department department) throws SQLException {
		Department seldept = null;
		String sql = "select deptNo, deptName, floor from department where deptno = ?";

		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, department.getDeptNo());
			LOG.debug(pstmt);

			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					seldept = getDepatment(rs);
				}
			}

		}
		return seldept;
	}

}
