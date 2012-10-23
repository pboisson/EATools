/**
 * 
 */
package tests.com.volvo.ea.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.volvo.ea.dao.impl.VolvoEntityDAOImpl;
import com.volvo.ea.entities.VolvoEntity;

/**
 * @author pico
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/applicationContext.xml" })
public class VolvoEntityCRUDTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());
	
	private Key key;

	@Before
	public void setUp() {
		helper.setUp();
		this.key = KeyFactory.createKey(
				VolvoEntity.class.getSimpleName(), "TEST");
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * Test method for
	 * {@link com.volvo.ea.dao.impl.VolvoEntityDAOImpl#create(com.volvo.ea.entities.VolvoEntity)}
	 * .
	 */
	@Test
	public void testCRUD() {
		VolvoEntityDAOImpl dao = (VolvoEntityDAOImpl) applicationContext
				.getBean("volvoEntityDao");
	
		/* CREATE */
		try {
			VolvoEntity entity = new VolvoEntity(this.key, "TEST NAME",
					new Date(), null);
			dao.create(entity);
		} catch (Throwable e) {
			e.printStackTrace();
			fail("Exception when trying to create a volvo entity");
		}
		/* READ */
		try {
			VolvoEntity entity = dao.read(this.key);
			assertNotNull(entity);
			assertEquals(entity.getName(), "TEST NAME");
		} catch (Throwable e) {
			e.printStackTrace();
			fail("Exception when trying to read a volvo entity");
		}
		/* UPDATE */
		try {
			VolvoEntity entity = dao.read(this.key);
			assertNotNull(entity);
			assertEquals(entity.getName(), "TEST NAME");
			entity.setName("TEST NAME UPDATED");
			dao.update(entity);
		} catch (Throwable e) {
			e.printStackTrace();
			fail("Exception when trying to update a volvo entity");
		}
		/* DELETE */
		try {
			VolvoEntity entity = dao.read(this.key);
			assertNotNull(entity);
			assertEquals(entity.getName(), "TEST NAME UPDATED");
			dao.delete(this.key);
		} catch (Throwable e) {
			e.printStackTrace();
			fail("Exception when trying to update a volvo entity");
		}
	}
}
