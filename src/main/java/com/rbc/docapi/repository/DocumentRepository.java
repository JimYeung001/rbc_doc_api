package com.rbc.docapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbc.docapi.model.Document;

/**
 * Declared operations for document to persist to database, essentially CRUD
 * operations
 * 
 * @author YEUNGJ4
 *
 */
public interface DocumentRepository extends JpaRepository<Document, Long> {

	/**
	 * Find all the document from database based on AppCode and descendant order by
	 * Last modified date
	 * 
	 * @param AppCode
	 *            appCode
	 * @return {@code List<Document> }
	 */
	public List<Document> findByAppCodeOrderByLastModifiedDateDesc(String appCode);

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
