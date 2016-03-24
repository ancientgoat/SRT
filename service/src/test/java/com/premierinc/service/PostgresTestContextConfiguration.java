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
@Component
@PropertySource("classpath:application.properties")
public class PostgresTestContextConfiguration extends JdbcRepositoryTestConfig {

	public static final int POSTGRESQL_PORT = 5432;

//	trs.db.driver=org.postgresql.Driver
//	trs.db.url=jdbc:postgresql://localhost:5432/dev1
//	trs.db.user=postgres
//	trs.db.password=postgres
//	trs.db.type=POSTGRES

	@Value("${trs.db.url}")
	private String dsUrl;

	@Value("${trs.db.user}")
	private String dsUser;

	@Value("${trs.db.password}")
	private String dsPass;

	@Value("${trs.db.database.name}")
	private String dsDatabaseName;

	//To resolve ${} in @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public SqlGenerator sqlGenerator() {
		return new PostgreSqlGenerator();
	}

	@Bean
	@Override
	public DataSource dataSource() {

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
