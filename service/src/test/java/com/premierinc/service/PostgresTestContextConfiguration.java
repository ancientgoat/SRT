package com.premierinc.service;

import com.nurkiewicz.jdbcrepository.sql.PostgreSqlGenerator;
import com.nurkiewicz.jdbcrepository.sql.SqlGenerator;
import com.premierinc.repo.ChildRepo;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

/**
 *
 */

//@EnableTransactionManagement
//@Configuration
@Component
@PropertySource("classpath:application.properties")
public class PostgresTestContextConfiguration extends JdbcRepositoryTestConfig {

	public static final int POSTGRESQL_PORT = 5432;

	@Value("${spring.datasource.url}")
	private String dsUrl;

	@Value("${spring.datasource.username}")
	private String dsUser;

	@Value("${spring.datasource.password}")
	private String dsPass;

	@Value("${spring.datasource.database.name}")
	private String dsDatabaseName;

	//To resolve ${} in @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	@Override
	public ChildRepo childRepo() {
		return new ChildRepo();
	}

	@Bean
	public SqlGenerator sqlGenerator() {
		return new PostgreSqlGenerator();
	}

	@Bean
	@Override
	public DataSource dataSource() {

		System.out.println("************************************** : " + dsUrl);
		System.out.println("************************************** : " + dsUrl);
		System.out.println("************************************** : " + dsUrl);
		System.out.println("************************************** : " + dsUrl);
		System.out.println("************************************** : " + dsUrl);
		System.out.println("************************************** : " + dsUrl);
		System.out.println("************************************** : " + dsUrl);
		System.out.println("************************************** : " + dsUrl);
		System.out.println("************************************** : " + dsUrl);

		EmbeddedDatabase embeddedDatabase = new EmbeddedDatabaseBuilder()
				//.generateUniqueName(true)
				.setType(H2).setScriptEncoding("UTF-8").ignoreFailedDrops(true).addScript(
						"schema.sql") //.addScripts("user_data.sql", "country_data.sql")
				.build();

		//PoolingDataSource ds = new PoolingDataSource();
		//ds.setUser(dsUser);
		//ds.setPassword(dsPass);
		//ds.setDatabaseName(dsDatabaseName);

		return embeddedDatabase;
	}
}
