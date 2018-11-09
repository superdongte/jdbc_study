package jdbc_study.jdbc;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dao.DepartmentDaoImpl;
import jdbc_study.dto.Department;

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
	public void testSelectDepartmentByAll() {
		List<Department> list = dao.selectDepartmentByAll();
		for(Department dept : list) {
			MYSQLJdbcUtilTest.LOG.debug(dept);
		}
		Assert.assertNotEquals(0, list.size());
	}

}
