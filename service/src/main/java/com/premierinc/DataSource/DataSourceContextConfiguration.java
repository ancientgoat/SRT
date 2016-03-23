package com.premierinc.DataSource;

import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;
import com.nurkiewicz.jdbcrepository.SqlGeneratorFactory;
import com.nurkiewicz.jdbcrepository.SqlGeneratorType;
import com.nurkiewicz.jdbcrepository.sql.SqlGenerator;
import com.premierinc.repo.ChildRepo;
import com.premierinc.repo.ParentChildRepo;
import com.premierinc.repo.ParentRepo;
import com.premierinc.service.ParentChildService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 */
@Component
@PropertySource("classpath:application.properties")
public class DataSourceContextConfiguration {

	public static final int POSTGRESQL_PORT = 5432;

	@Value("${trs.db.type:POSTGRES}")
	SqlGeneratorType dbType;

	@Bean
	public ChildRepo childRepo() {
		return new ChildRepo(dbType);
	}

	@Bean
	public ParentRepo parentRepo() {
		return new ParentRepo(dbType);
	}

	@Bean
	public ParentChildRepo parentChildRepo() {
		return new ParentChildRepo(dbType);
	}

	@Bean
	public ParentChildService parentChildService() {
		return new ParentChildService();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Value("${trs.db.driver}")
	String driverClassName;

	@Value("${trs.db.url}")
	String url;

	@Value("${trs.db.user}")
	String username;

	@Value("${trs.db.password}")
	String password;

	@Value("${db.connpool.minconn:1}")
	String min;

	@Value("${db.connpool.maxconn:2}")
	String max;

	@Value("${db.connpool.idle_max_age_in_minutes:2}")
	String idleMaxAgeInMinutes;

	@Value("${db.connpool.idle_connection_test_period_in_minutes:1}")
	String idleConnectionTestPeriodInMinutes;

	@Value("${db.connpool.partition_count:2}")
	String partitionCount;

	@Value("${db.connpool.statement_cache_size:0}")
	String statementsCacheSize;

	//To resolve ${} in @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public SqlGenerator sqlGenerator() {
		return SqlGeneratorFactory.newInstance(dbType);
	}

	@Bean
	public DataSource dataSource() {
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			throw new AssertionError("Could not find JDBC driver: " + e);
		}

		BoneCPConfig config = new BoneCPConfig();
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		config.setIdleConnectionTestPeriodInMinutes(
				Long.parseLong(idleConnectionTestPeriodInMinutes));
		config.setIdleMaxAgeInMinutes(Long.parseLong(idleMaxAgeInMinutes));
		config.setMinConnectionsPerPartition(Integer.parseInt(min));
		config.setPartitionCount(Integer.parseInt(partitionCount));
		config.setMaxConnectionsPerPartition(Integer.parseInt(max));
		config.setAcquireIncrement(Integer.parseInt(min));
		config.setDisableConnectionTracking(true);
		config.setStatementsCacheSize(Integer.parseInt(statementsCacheSize));
		return new BoneCPDataSource(config);
	}
}
