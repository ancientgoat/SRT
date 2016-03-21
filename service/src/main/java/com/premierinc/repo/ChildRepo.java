package com.premierinc.repo;

import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.premierinc.persistable.ChildPersistable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public class ChildRepo extends JdbcRepository<ChildPersistable, Long> {

	public ChildRepo() {
		// 3rd param is TableName
		super(ROW_MAPPER, ROW_UNMAPPER, "child_table");
	}

	public static final RowMapper<ChildPersistable> ROW_MAPPER = new RowMapper<ChildPersistable>() {
		@Override
		public ChildPersistable mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new ChildPersistable().setId(rs.getLong("id")).setName(rs.getString("name"))
					// , rs.getString("contents")
					// , rs.getTimestamp("created_time")
					;
		}
	};

	private static final RowUnmapper<ChildPersistable> ROW_UNMAPPER =
			new RowUnmapper<ChildPersistable>() {
				@Override
				public Map<String, Object> mapColumns(ChildPersistable childPersistable) {
					Map<String, Object> mapping = new LinkedHashMap<String, Object>();
					mapping.put("id", childPersistable.getId());
					mapping.put("name", childPersistable.getName());
					// mapping.put("contents", childPersistable.getContents());
					// mapping.put("created_time", new java.sql.Timestamp(childPersistable.getCreatedTime().getTime()));
					return mapping;
				}
			};

	@Override
	protected <S extends ChildPersistable> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.longValue());
		return entity;
	}

	// public <S extends T> S save(S entity) {
	@Override
	public ChildPersistable save(ChildPersistable entity) {
		return null;
	}

	// public Iterable<ChildPersistable> save(Iterable<ChildPersistable> entities) {
	@Override
	public <S extends ChildPersistable> Iterable<S> save(Iterable<S> entities) {
		return null;
	}

	// @Override
	// public <S extends T> Iterable<S> save(Iterable<S> entities) {
	// 	return null;
	// }
}

/******************************************************
 @Repository public class CommentRepository extends JdbcRepository<Comment, Integer> {

 public CommentRepository() {
 super(ROW_MAPPER, ROW_UNMAPPER, "COMMENTS");
 }

 public static final RowMapper<Comment> ROW_MAPPER = //see below

 private static final RowUnmapper<Comment> ROW_UNMAPPER = //see below

 @Override protected <S extends Comment> S postCreate(S entity, Number generatedId) {
 entity.setId(generatedId.intValue());
 return entity;
 }
 }
 ******************************************************/