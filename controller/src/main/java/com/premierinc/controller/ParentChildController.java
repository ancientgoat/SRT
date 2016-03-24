package com.premierinc.controller;

import com.premierinc.persistable.ParentPersistable;
import com.premierinc.service.ParentChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/parent")
public class ParentChildController {

	@Autowired
	private ParentChildService parentChildService;

//	@Autowired
//	public ParentChildController(final ParentChildService inParentChildService) {
//		this.parentChildService = inParentChildService;
//	}

	//@RequestMapping(value = "/parent", method = RequestMethod.GET, produces = "application/json")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public Page<ParentPersistable> getParents(final Pageable inPageable) {
		return this.parentChildService.findAll(inPageable);
	}

	//@RequestMapping(value = "/parent", method = RequestMethod.POST, produces = "application/json")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ParentPersistable saveParent(final @RequestBody ParentPersistable inParent) {
		final ParentPersistable savedParent = parentChildService.save(inParent);
		return savedParent;
	}
}
