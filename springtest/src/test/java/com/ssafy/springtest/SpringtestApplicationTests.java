package com.ssafy.springtest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zaxxer.hikari.HikariDataSource;

@SpringBootTest
class SpringtestApplicationTests {

	@Autowired
	HikariDataSource ds;
	
	@Test
	public void dsTest() throws SQLException {
		assertNotNull(ds);
		Connection con = ds.getConnection();
		assertNotNull(con);
	}

}
