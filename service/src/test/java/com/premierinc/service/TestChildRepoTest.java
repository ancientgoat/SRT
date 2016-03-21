package com.premierinc.service;

import com.premierinc.persistable.ChildPersistable;
import com.premierinc.repo.ChildRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
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
public class TestChildRepoTest {

	@Autowired
	private ChildRepo childRepo;

	public TestChildRepoTest() {
	}

	@Before
	public void setUp() {
		Assert.assertNotNull(this.childRepo);
	}

	@Test
	public void testSimple() {

		try {
			final ChildPersistable firstChild = new ChildPersistable().setName("Patty");
			final ChildPersistable savedChild = this.childRepo.save(firstChild);
			Assert.assertNotNull("'savedChild' getId() should not be null", savedChild.getId());

			final ChildPersistable secondChild = this.childRepo.findOne(savedChild.getId());
			Assert.assertNotNull("'secondChild' should not be null", secondChild);

			Assert.assertEquals("SavedChild and secondChild getIds() should have been equal.",
					savedChild.getId(), secondChild.getId());

		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException(
					"First child was not saved, or 'findAll' not working.", e);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}
