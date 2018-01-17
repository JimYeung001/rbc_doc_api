package com.rbc.docapi.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.docapi.model.Document;
import com.rbc.docapi.service.DocumentService;

/**
 * This class represents all the resource for {@code Document}
 * 
 * @author YEUNGJ4
 *
 */
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentController {

	/**
	 * Document service injection
	 */
	@Autowired
	private DocumentService docService;

	/**
	 * POST /api/{appCode}/config/{version} – add new or update existing JSON
	 * document for specified appCode and version. JSON document will be included in
	 * the request body
	 * 
	 * @param appCode
	 *            appCode
	 * @param version
	 *            document version
	 * @param doc
	 *            Query Body
	 * @return Saved {@code Document }
	 */
	@PostMapping("/{appCode}/config/{version}")
	public Document createDocument(@PathVariable String appCode, @PathVariable String version,
			@RequestBody Document doc) {
		doc.setAppCode(appCode);
		doc.setVersion(version);
		doc.setCratedDate(new Date());

		// For simplify logic if ID == null means to create new document; otherwise
		// update exist document
		if (doc.getId() != null) {
			doc.setLastModifieddDate(new Date());
		}

		return getDocService().save(doc);
	}

	/**
	 * GET /api/{appCode}/config/{version} – return JSON document for specified
	 * appCode and version
	 * 
	 * @param appCode
	 *            appCode
	 * @param version
	 *            document version
	 * @return a List<Document>
	 */
	@GetMapping("/{appCode}/config/{version}")
	public List<Document> getDocByAppCodeAndVersion(@PathVariable String appCode, @PathVariable String version) {
		return getDocService().findByAppCodeAndVersion(appCode, version);
	}

	/**
	 * GET /api/{appCode}/config – return list of available versions in JSON sorted
	 * by last modified date in descending order
	 * 
	 * @param appCode
	 *            appCode
	 * @return List<Document>
	 */
	@GetMapping("/{appCode}/config")
	public List<Document> getAllDocument(@PathVariable String appCode) {
		return getDocService().findAllByAppCodeOrderByLastModifiedDateDesc(appCode);
	}

	/**
	 * @return the docService
	 */
	public DocumentService getDocService() {
		return docService;
	}

}
