package com.rbc.docapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rbc.docapi.model.Document;

/**
 * This class will extend all the operations from {@code DocumentRepository }
 * and will only implement the operations the api needs
 * 
 * @author YEUNGJ4
 *
 */
@Service
public interface DocumentService {

	/**
	 * Find the document with ID
	 * 
	 * @param id
	 *            document ID
	 * @return {@code Document}
	 */
	public Document findOneById(Long id);

	/**
	 * Save {@code Document } entity to database
	 * 
	 * @param doc
	 * @return saved {@code Document}
	 */
	public Document save(Document doc);

	/**
	 * Find all the document from database based on AppCode and descendant order by
	 * Last modified date
	 * 
	 * @param AppCode
	 *            appCode
	 * @return {@code List<Document> }
	 */
	public List<Document> findAllByAppCodeOrderByLastModifiedDateDesc(String appCode);

	/**
	 * Find {@code Document } by AppCode and version
	 * 
	 * @param appCode
	 *            AppCode
	 * @param version
	 *            version
	 * @return a list of {@code Document}
	 */
	public List<Document> findByAppCodeAndVersion(String appCode, String version);
}
