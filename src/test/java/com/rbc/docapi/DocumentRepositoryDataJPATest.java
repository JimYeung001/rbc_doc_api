package com.rbc.docapi;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rbc.docapi.model.Document;
import com.rbc.docapi.service.DocumentService;
import com.rbc.docapi.service.impl.DocumentServiceImpl;

/**
 * This unit test used to test all {@code Document } data JPA test
 * 
 * @author YEUNGJ4
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class DocumentRepositoryDataJPATest {

	/**
	 * {@code DocumentService}
	 */
	@Autowired
	private DocumentService service;

	/**
	 * Configure the DocumentService before Autowired.
	 * 
	 * @author YEUNGJ4
	 *
	 */
	@TestConfiguration
	static class DocumentServiceImplTestContextConfiguration {
		@Bean
		public DocumentService documentService() {
			return new DocumentServiceImpl();
		}

	}

	/**
	 * Prepare some data for the rest unit tests.
	 * 
	 * @throws ParseException
	 */
	@Before
	public void prepareDataForTest() throws ParseException {

		// Doc #1
		Document doc1 = new Document();
		doc1.setAppCode("Java");
		doc1.setVersion("V110");
		doc1.setBody("this is Document body 1");
		doc1.setCratedDate(new Date());
		service.save(doc1);

		// Doc #2
		Document doc2 = new Document();
		doc2.setAppCode("Java2");
		doc2.setVersion("V100");
		doc2.setBody("this is Document body 2");
		doc2.setCratedDate(new Date());
		service.save(doc2);

		// Doc #3
		Document doc3 = new Document();
		doc3.setAppCode("Java");
		doc3.setVersion("V100");
		doc3.setBody("this is Document body 3");
		doc3.setCratedDate(new Date());
		doc3 = service.save(doc3);

		// Modify document
		// Doc #3
		String inputString = "11-11-2012";
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = dateFormat.parse(inputString);
		doc3.setLastModifieddDate(date);
		doc3 = service.save(doc3);

		// Doc #2
		doc2.setLastModifieddDate(new Date());
		service.save(doc2);

		// Doc #1
		doc1.setLastModifieddDate(new Date());
		service.save(doc1);
	}

	/**
	 * Test the service to save {@code Document } entity
	 */
	@Test
	public void saveDocument() {
		Document doc = new Document();
		doc.setAppCode("Java2");
		doc.setVersion("V101");
		doc.setBody("this is Document body");
		doc.setCratedDate(new Date());
		Document savedDoc = service.save(doc);
		assertNotNull(savedDoc);
	}

	/**
	 * Test The service to fetch all {@code Document}s by AppCode and order by last
	 * modified date, and in descendant order.
	 */
	@Test
	public void fetchAllDocumentByAppCodeOrderByLastModifiedDateDescendantOrder() {
		List<Document> docs = service.findAllByAppCodeOrderByLastModifiedDateDesc("Java");
		assertNotNull(docs);
		assertTrue(docs.size() == 2);
		assertTrue(docs.get(0).getLastModifieddDate().getTime() > docs.get(1).getLastModifieddDate().getTime());
	}

	/**
	 * Test the service to fetch {@code Document}s depends on the appCode and
	 * version, could return multiple documents with same appCode and version
	 */
	@Test
	public void getDocumentByAppCodeAndVersion() {
		List<Document> docs = service.findByAppCodeAndVersion("Java", "V100");
		assertNotNull(docs);
		assertTrue(docs.size() == 2);

	}

}
