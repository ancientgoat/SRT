package com.premierinc.repo;

import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.premierinc.persistable.ParentPersistable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
@Component
public class ParentRepo extends JdbcRepository<ParentPersistable, Long> {

	public ParentRepo() {
		// 3rd param is TableName
		super(ROW_MAPPER, ROW_UNMAPPER, "parent_table");
	}

	public static final RowMapper<ParentPersistable> ROW_MAPPER =
			new RowMapper<ParentPersistable>() {
				@Override
				public ParentPersistable mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new ParentPersistable().setId(rs.getLong("id")).setName(
							rs.getString("name"));
				}
			};

	private static final RowUnmapper<ParentPersistable> ROW_UNMAPPER =
			new RowUnmapper<ParentPersistable>() {
				@Override
				public Map<String, Object> mapColumns(ParentPersistable childPersistable) {
					Map<String, Object> mapping = new LinkedHashMap<String, Object>();
					mapping.put("id", childPersistable.getId());
					mapping.put("name", childPersistable.getName());
					return mapping;
				}
			};

	@Override
	protected <S extends ParentPersistable> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.longValue());
		return entity;
	}

	@Override
	public ParentPersistable save(ParentPersistable entity) {
		return super.save(entity);
	}

	@Override
	public <S extends ParentPersistable> Iterable<S> save(Iterable<S> entities) {
		return super.save(entities);
	}
}
