package com.rbc.docapi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbc.docapi.controller.DocumentController;
import com.rbc.docapi.model.Document;
import com.rbc.docapi.service.DocumentService;

@RunWith(SpringRunner.class)
@WebMvcTest(DocumentController.class)
public class DocumentRESTResourceTest {

	private static final String BASE_URI = "/{appCode}/config/{version}";

	@Autowired
	private MockMvc mvc;

	@MockBean
	private DocumentService service;

	/**
	 * Test resource to fetch {@code Document }s by the same AppCode and Version.
	 * 
	 * @throws Exception
	 */
	@Test
	public void givenDocument_whenGetDocumentByAppCodeAndVersion_thenReturnJsonArray() throws Exception {
		String appCode = "Java";
		String version = "V110";

		Document doc = new Document();
		doc.setAppCode(appCode);
		doc.setVersion(version);
		doc.setBody("this is Document body 1");
		doc.setCratedDate(new Date());

		List<Document> docs = Arrays.asList(doc);

		given(service.findByAppCodeAndVersion(appCode, version)).willReturn(docs);

		mvc.perform(get(BASE_URI, appCode, version).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].appCode", is(doc.getAppCode())));
	}

	/**
	 * Test resource to Save {@code Document } with AppCode and Version
	 * 
	 * @throws Exception
	 */
	@Test
	public void whenSaveNewDocument_thenReturnSavedDocumentAsJsonObject() throws Exception {
		String appCode = "Java";
		String version = "V110";

		Document doc = new Document();
		doc.setAppCode(appCode);
		doc.setVersion(version);
		doc.setBody("this is Document body 1");
		doc.setCratedDate(new Date());

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(doc);

		when(service.save(doc)).thenReturn(doc);

		mvc.perform(post(BASE_URI, appCode, version).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(json)).andExpect(status().isOk())
				.andExpect(jsonPath("$.appCode", is(doc.getAppCode())))
				.andExpect(jsonPath("$.version", is(doc.getVersion())));
	}

	/**
	 * Test resource to Save {@code Document } with AppCode and Version
	 * 
	 * @throws Exception
	 */
	@Test
	public void whenUpdateExistDocument_thenReturnUpdatedDocumentAsJsonObject() throws Exception {
		String appCode = "Java";
		String version = "V110";

		Document doc = new Document();
		doc.setId(1L);
		doc.setAppCode(appCode);
		doc.setVersion(version);
		doc.setBody("\"this is Document body 1 UPDATED");
		doc.setCratedDate(new Date());

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(doc);

		doc.setLastModifieddDate(new Date());
		when(service.save(doc)).thenReturn(doc);

		mvc.perform(post(BASE_URI, appCode, version).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(json)).andExpect(status().isOk())
				.andExpect(jsonPath("$.appCode", is(doc.getAppCode())))
				.andExpect(jsonPath("$.version", is(doc.getVersion())))
				.andExpect(jsonPath("$.lastModifieddDate", is(not(equalTo(null)))));
	}

}
