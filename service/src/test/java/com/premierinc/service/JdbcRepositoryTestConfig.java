package com.premierinc.service;

import com.premierinc.repo.ChildRepo;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 */
public abstract class JdbcRepositoryTestConfig {

	@Bean
	public abstract DataSource dataSource();

	@Bean
	public ChildRepo childRepo() {
		return new ChildRepo();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
