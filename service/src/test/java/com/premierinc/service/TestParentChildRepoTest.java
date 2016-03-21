package com.premierinc.service;

import com.premierinc.persistable.ChildPersistable;
import com.premierinc.persistable.ParentPersistable;
import com.premierinc.util.JsonHelper;
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
public class TestParentChildRepoTest {

	@Autowired
	private ParentChildService parentChildService;

	public TestParentChildRepoTest() {
	}

	@Before
	public void setUp() {
		Assert.assertNotNull(this.parentChildService);
	}

	@Test
	public void testParentChildSimple() {

		try {
			final ParentPersistable firstParent = new ParentPersistable().setName("BigPatty");
			final ChildPersistable firstChild = new ChildPersistable().setName("BabyPatty");
			firstParent.setChild(firstChild);

			final ParentPersistable savedParent = this.parentChildService.save(firstParent);
			Assert.assertNotNull("'savedParent' getId() should not be null", savedParent.getId());
			Assert.assertNotNull("'savedParent' getChild() should not be null", savedParent.getChild());
			Assert.assertNotNull("'savedParent' getChild().getId() should not be null", savedParent.getChild().getId());

			Assert.assertEquals("SavedParent and secondParent getIds() should have been equal.",
					savedParent.getId(), firstParent.getId());

			final ParentPersistable secondParent = this.parentChildService.findOne(savedParent.getId());
			Assert.assertNotNull("'secondParent' should not be null", secondParent);

			System.out.println(String.format("Second : \n%s", JsonHelper.beanToJsonString(secondParent)));
			System.out.println(String.format("Saved  : \n%s", JsonHelper.beanToJsonString(savedParent)));

			Assert.assertEquals("SavedParent and secondParent getIds() should have been equal.",
					savedParent.getId(), secondParent.getId());

			Assert.assertEquals("SavedParent.Child and secondParent.Child getIds() should have been equal.",
					savedParent.getChild().getId(), secondParent.getChild().getId());

		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException(
					"First parent was not saved, or 'findAll' not working.", e);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}
