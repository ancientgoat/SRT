package com.premierinc.service;

import com.premierinc.repo.ChildRepo;
import com.premierinc.repo.ParentChildRepo;
import com.premierinc.repo.ParentRepo;
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
	public ParentRepo parentRepo() {
		return new ParentRepo();
	}

	@Bean
	public ParentChildRepo parentChildRepo() {
		return new ParentChildRepo();
	}

	@Bean
	public ParentChildService parentChildService() {
		return new ParentChildService();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
