package com.premierinc.service;

import com.premierinc.persistable.ChildPersistable;
import com.premierinc.persistable.ParentPersistable;
import com.premierinc.repo.ChildRepo;
import com.premierinc.repo.ParentChildRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service(value="parentChildService")
public class ParentChildService {

	@Autowired
	private ParentChildRepo parentChildRepo;

	@Autowired
	private ChildRepo childRepo;

	/**
	 *
	 */
	@Transactional
	public ParentPersistable save(final ParentPersistable inParentChild){
		final ChildPersistable child = inParentChild.getChild();
		if(null != child){
			this.childRepo.save(child);
		}
		this.parentChildRepo.save(inParentChild);

		return inParentChild;
	}

	public ParentPersistable findOne(final Long inId){
		return parentChildRepo.findOne(inId);
	}
}
