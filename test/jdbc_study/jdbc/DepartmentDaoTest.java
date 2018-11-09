package jdbc_study.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dao.DepartmentDaoImpl;
import jdbc_study.dto.Department;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	static DepartmentDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MYSQLJdbcUtilTest.LOG.debug("setUpBeforeClass");
		dao = new DepartmentDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		MYSQLJdbcUtilTest.LOG.debug("setUpBeforeClass");
		dao = new DepartmentDaoImpl();
	}
	@Test
	public void test01SelectDepartmentByAll() {
		List<Department> list = dao.selectDepartmentByAll();
		for(Department dept : list) {
			MYSQLJdbcUtilTest.LOG.debug(dept);
		}
		Assert.assertNotEquals(0, list.size());
	}
	@Test
	public void test02InsertDepartment() {
		Department newDept = new Department(4, "잡아 과정", 15);
		try {
			int res =dao.insertDepartment(newDept);
			Assert.assertNotEquals(0, res);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			e.printStackTrace();
			if(e.getErrorCode()==1062) {
				JOptionPane.showMessageDialog(null, "해당부서 존재한다.");
			}
		}
	}
	@Test
	public void test04DeleteDepartment() {
		Department delDept = new Department(4);
		try {
			int res = dao.deleteDepartment(delDept);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		test01SelectDepartmentByAll();
	}
	@Test
	public void test03UpdateDepartment() {
		Department setDept = new Department(4,"건담아아아아",10);
		try {
			int res =dao.updateDepartment(setDept);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test05SelectDepartmentByNo() {
		Department newDept = new Department(1);
		try {
			Department res = dao.selectDepartmentByNo(newDept);
			JOptionPane.showMessageDialog(null, res);
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "검색 실패");
		}
	}
}
