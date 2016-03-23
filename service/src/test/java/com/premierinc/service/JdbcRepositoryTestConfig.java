package com.premierinc.service;

import com.nurkiewicz.jdbcrepository.SqlGeneratorType;
import com.premierinc.repo.ChildRepo;
import com.premierinc.repo.ParentChildRepo;
import com.premierinc.repo.ParentRepo;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 */
@PropertySource("classpath:application.properties")
public abstract class JdbcRepositoryTestConfig {

	@Value("${trs.db.type:POSTGRES}")
	SqlGeneratorType dbType;

	@Bean
	public abstract DataSource dataSource();

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
}
