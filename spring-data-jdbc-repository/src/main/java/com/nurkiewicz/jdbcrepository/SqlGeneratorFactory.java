package com.nurkiewicz.jdbcrepository;

import com.nurkiewicz.jdbcrepository.SqlGeneratorType;
import com.nurkiewicz.jdbcrepository.sql.DerbySqlGenerator;
import com.nurkiewicz.jdbcrepository.sql.Mssql2012SqlGenerator;
import com.nurkiewicz.jdbcrepository.sql.MssqlSqlGenerator;
import com.nurkiewicz.jdbcrepository.sql.OracleSqlGenerator;
import com.nurkiewicz.jdbcrepository.sql.PostgreSqlGenerator;
import com.nurkiewicz.jdbcrepository.sql.SqlGenerator;

/**
 *
 */
public class SqlGeneratorFactory {

	private SqlGeneratorFactory() {
	}

	public static final SqlGenerator newInstance(final SqlGeneratorType inType) {
		return newInstance(inType, "*");
	}

	public static final SqlGenerator newInstance(final SqlGeneratorType inType,
			final String inAllColumnsClause) {

		SqlGenerator sqlGenerator = null;

		switch (inType) {
		case ORACLE:
			sqlGenerator = new OracleSqlGenerator(inAllColumnsClause);
			break;
		case POSTGRES:
			sqlGenerator = new PostgreSqlGenerator(inAllColumnsClause);
			break;
		case DERBY:
			sqlGenerator = new DerbySqlGenerator(inAllColumnsClause);
			break;
		case SQLSERVER:
			sqlGenerator = new MssqlSqlGenerator(inAllColumnsClause);
			break;
		case SQLSERVER2012:
			sqlGenerator = new Mssql2012SqlGenerator(inAllColumnsClause);
			break;
		case GENERIC:
			sqlGenerator = new SqlGenerator(inAllColumnsClause);
			break;

		default:
			throw new IllegalArgumentException(
					String.format("SqlGeneratorType '%s' not implemented.", inType));
		}
		return sqlGenerator;
	}
}
