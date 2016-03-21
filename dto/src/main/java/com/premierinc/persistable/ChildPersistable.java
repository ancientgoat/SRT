package com.premierinc.persistable;

import org.springframework.data.domain.Persistable;

/**
 *
 */
public class ChildPersistable implements Persistable<Long> {

	private Long id;
	private String name;
	private ParentPersistable parent;

	public ParentPersistable getParent() {
		return parent;
	}

	public String getName() {
		return name;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public boolean isNew() {
		return null == this.id;
	}

	public ChildPersistable setParent(ParentPersistable parent) {
		this.parent = parent;
		return this;
	}

	public ChildPersistable setId(final Long inId) {
		this.id = inId;
		return this;
	}

	public ChildPersistable setName(final String inName) {
		this.name = inName;
		return this;
	}

	// public ChildPersistable withPersisted(boolean inPersisted) {
	// 	this.persisted = inPersisted;
	// 	return this;
	// }

}
