package com.premierinc.persistable;

import org.springframework.data.domain.Persistable;

/**
 *
 */
public class ParentPersistable implements Persistable<Long> {

	private Long id;
	private String name;
	private ChildPersistable child;

	public String getName() {
		return name;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public ChildPersistable getChild() {
		return child;
	}

	public ParentPersistable setId(final Long inId) {
		this.id = inId;
		return this;
	}

	public ParentPersistable setName(final String inName) {
		this.name = inName;
		return this;
	}

	public ParentPersistable setChild(ChildPersistable inChild) {
		this.child = inChild;
		return this;
	}

	@Override
	public boolean isNew() {
		return null == this.id;
	}
}
