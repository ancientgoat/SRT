package com.premierinc.repo;

import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.SqlGeneratorFactory;
import com.nurkiewicz.jdbcrepository.SqlGeneratorType;
import com.nurkiewicz.jdbcrepository.TableDescription;
import com.premierinc.persistable.ChildPersistable;
import com.premierinc.persistable.ParentPersistable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
@Component
@PropertySource("classpath:application.properties")
public class ChildRepo extends JdbcRepository<ChildPersistable, Long> {

	private final static TableDescription TABLE_DESC = new TableDescription("CHILD_TABLE", null,
			"id");

	//	@Value("${trs.db.type:POSTGRES}")
	//	private static SqlGeneratorType dbType;

	public ChildRepo(@Value("${trs.db.type:POSTGRES}") SqlGeneratorType dbType) {
		//public ChildRepo() {
		// 3rd param is TableName
		super(ROW_MAPPER, ROW_UNMAPPER, SqlGeneratorFactory.newInstance(dbType), TABLE_DESC);
	}

	public static final RowMapper<ChildPersistable> ROW_MAPPER = new BeanPropertyRowMapper<>(
			ChildPersistable.class);

	//	public static final RowMapper<ChildPersistable> ROW_MAPPER = new RowMapper<ChildPersistable>() {
	//		@Override
	//		public ChildPersistable mapRow(ResultSet rs, int rowNum) throws SQLException {
	//			return new ChildPersistable().setId(rs.getLong("id")).setName(rs.getString("name"))
	//					// , rs.getString("contents")
	//					// , rs.getTimestamp("created_time")
	//					;
	//		}
	//	};

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
		return super.save(entity);
	}

	// public Iterable<ChildPersistable> save(Iterable<ChildPersistable> entities) {
	@Override
	public <S extends ChildPersistable> Iterable<S> save(Iterable<S> entities) {
		return super.save(entities);
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