package com.premierinc.service;

import com.google.common.collect.Lists;
import com.premierinc.persistable.ChildPersistable;
import com.premierinc.repo.ChildRepo;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	private static final int MANY_COUNT = 10;

	@Autowired
	private ChildRepo childRepo;

	public TestChildRepoTest() {
	}

	@Before
	public void setUp() {
		Assert.assertNotNull(this.childRepo);
	}

	@Test
	public void testChildSimple() {

		try {
			final ChildPersistable firstChild = new ChildPersistable().setName("Patty");
			final ChildPersistable savedChild = this.childRepo.save(firstChild);
			Assert.assertNotNull("'savedChild' getId() should not be null", savedChild.getId());

			Assert.assertEquals("SavedChild and secondChild getIds() should have been equal.",
					savedChild.getId(), firstChild.getId());

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

	@Test
	public void testChildMany() {

		try {
			final List<ChildPersistable> childList = Lists.newArrayList();

			// Build a bunch of Children
			for (int i = 0; i < MANY_COUNT; i++) {
				childList.add(new ChildPersistable().setName(String.format("Patty_%02d", i)));
			}

			// Save all at once
			this.childRepo.save(childList);

			// Are they all there
			for (int i = 0; i < MANY_COUNT; i++) {
				final ChildPersistable child = childList.get(i);
				System.out.println(
						String.format("child %d Id == %s : %s", i, child.getId(), child.getName()));

				Assert.assertNotNull(String.format("'child[%d]' getId() should not be null", i),
						child.getId());
				Assert.assertNotNull(String.format("'child[%d]' getName() should not be null", i),
						child.getName());
			}

			// Test Pageable
			int half = MANY_COUNT / 2;
			final PageRequest page = new PageRequest(0, half);
			final Page<ChildPersistable> halfChildList = this.childRepo.findAll(page);

			Assert.assertEquals(String.format("Page size needs to be %d", half),
					halfChildList.getNumberOfElements(), half);

		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException(
					"First child was not saved, or 'findAll' not working.", e);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}
