package com.premierinc.service;

import com.premierinc.persistable.ParentPersistable;
import com.premierinc.repo.ParentRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
		classes = PostgresTestContextConfiguration.class)
@Transactional
public class TestParentRepoTest {

	@Autowired
	private ParentRepo parentRepo;

	public TestParentRepoTest() {
	}

	@Before
	public void setUp() {
		Assert.assertNotNull(this.parentRepo);
	}

	@Test
	public void testParentSimple() {

		try {
			final ParentPersistable firstParent = new ParentPersistable().setName("Patty");
			final ParentPersistable savedParent = this.parentRepo.save(firstParent);
			Assert.assertNotNull("'savedParent' getId() should not be null", savedParent.getId());

			Assert.assertEquals("SavedParent and secondParent getIds() should have been equal.",
					savedParent.getId(), firstParent.getId());

			final ParentPersistable secondParent = this.parentRepo.findOne(savedParent.getId());
			Assert.assertNotNull("'secondParent' should not be null", secondParent);

			Assert.assertEquals("SavedParent and secondParent getIds() should have been equal.",
					savedParent.getId(), secondParent.getId());

		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException(
					"First parent was not saved, or 'findAll' not working.", e);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}
