package jdbc_study.jdbc;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class MYSQLJdbcUtilTest {
	static final Logger LOG = LogManager.getLogger();
	
	@Test
	public void testConnection() {
		try {
			Connection conn =MySQLJdbcUtil.getConnection();
			LOG.trace(String.format("Connected to DataBase %s successfully",conn.getCatalog()));
			Assert.assertNotNull(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
