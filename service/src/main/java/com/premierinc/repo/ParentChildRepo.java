package com.premierinc.repo;

import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;
import com.nurkiewicz.jdbcrepository.sql.SqlGenerator;
import com.premierinc.persistable.ChildPersistable;
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
public class ParentChildRepo extends JdbcRepository<ParentPersistable, Long> {

	private final static String LINK_TABLES =
			"PARENT_TABLE P LEFT OUTER JOIN CHILD_TABLE C ON C.Id = P.Child_Id";

	private final static String COLUMN_LIST =
			"P.Id AS Parent_Id, P.Name AS Parent_Name, C.Id AS Child_Id, C.Name AS Child_Name";

	public final static SqlGenerator SQL_GENERATOR = new SqlGenerator(COLUMN_LIST);

	private final static TableDescription TABLE_DESC = new TableDescription("PARENT_TABLE",
			LINK_TABLES, "P.Id");

	public ParentChildRepo() {
		// 3rd param is TableName
		super(ROW_MAPPER, ROW_UNMAPPER, SQL_GENERATOR, TABLE_DESC);
	}

	public static final RowMapper<ParentPersistable> ROW_MAPPER =
			new RowMapper<ParentPersistable>() {
				@Override
				public ParentPersistable mapRow(ResultSet rs, int rowNum) throws SQLException {

					final ParentPersistable parentPersistable = new ParentPersistable().setId(
							rs.getLong("Parent_Id")).setName(rs.getString("Parent_Name"));

					final ChildPersistable child = new ChildPersistable().setId(rs.getLong("Child_Id"))
							.setName(rs.getString("Child_Name"));

					parentPersistable.setChild(child);
					return parentPersistable;
				}
			};

	private static final RowUnmapper<ParentPersistable> ROW_UNMAPPER =
			new RowUnmapper<ParentPersistable>() {
				@Override
				public Map<String, Object> mapColumns(ParentPersistable parentPersistable) {
					final ChildPersistable child = parentPersistable.getChild();
					Map<String, Object> mapping = new LinkedHashMap<String, Object>();
					mapping.put("id", parentPersistable.getId());
					mapping.put("name", parentPersistable.getName());
					mapping.put("child_id", child.getId());
					// mapping.put("contents", parentPersistable.getContents());
					// mapping.put("created_time", new java.sql.Timestamp(parentPersistable.getCreatedTime().getTime()));
					return mapping;
				}
			};

	@Override
	protected <S extends ParentPersistable> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.longValue());
		return entity;
	}

	// public <S extends T> S save(S entity) {
	@Override
	public ParentPersistable save(ParentPersistable entity) {
		//final ChildPersistable child = entity.getChild();
		ParentPersistable save = super.save(entity);
		return save;
	}

	// public Iterable<ParentPersistable> save(Iterable<ParentPersistable> entities) {
	@Override
	public <S extends ParentPersistable> Iterable<S> save(Iterable<S> entities) {
		return super.save(entities);
	}

	// @Override
	// public <S extends T> Iterable<S> save(Iterable<S> entities) {
	// 	return null;
	// }
}

