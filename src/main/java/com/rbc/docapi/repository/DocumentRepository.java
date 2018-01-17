package com.rbc.docapi.repository;

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

}
