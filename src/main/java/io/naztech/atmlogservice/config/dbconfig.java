package io.naztech.atmlogservice.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.nazdaqTechnologies.jdbc.JdbcService;
import com.nazdaqTechnologies.jdbc.JdbcStatementFactory;

@Configuration
@ComponentScan("io.naztech.atmlogservice.jdbc")
public class dbconfig {	
	
	@Value("${io.naztech.atmlog.jdbc.driver}")
	private String dbDriver;
	@Value("${io.naztech.atmlog.jdbc.url}")
	private String dbUrl;
	@Value("${io.naztech.atmlog.jdbc.username}")
	private String dbUser;
	@Value("${io.naztech.atmlog.jdbc.password}")
	private String dbPass;

	@Bean
	JdbcService jdbcService() {
		JdbcService jdbcService = new JdbcService();
		jdbcService.setDataSource(dataSource());
		jdbcService.setTransactionManager(dataSourceTransactionManager());
		jdbcService.setJdbcStatementFactory(new JdbcStatementFactory());
		return jdbcService;	
	}
	
	@Bean("dataSource")
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(dbDriver);
		ds.setUrl(dbUrl);
		ds.setUsername(dbUser);
		ds.setPassword(dbPass);
		ds.setInitialSize(5);
		ds.setMaxActive(-1);
		ds.setMaxIdle(10);
		ds.setDefaultAutoCommit(true);
		return ds;
	}
	
	@Bean
	DataSourceTransactionManager dataSourceTransactionManager() {
		DataSourceTransactionManager obj = new DataSourceTransactionManager();
		obj.setDataSource(dataSource());
		return obj;
	}

	
}
