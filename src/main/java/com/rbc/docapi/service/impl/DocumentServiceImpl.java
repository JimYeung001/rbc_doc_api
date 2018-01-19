package com.rbc.docapi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.docapi.model.Document;
import com.rbc.docapi.repository.DocumentRepository;
import com.rbc.docapi.service.DocumentService;

/**
 * this class will implement all the operations defined in
 * {@code DocumentService } interface.
 * 
 * @author YEUNGJ4
 *
 */
@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

	/**
	 * Document Repository {@code DocumentRepository}
	 */
	@Autowired
	private DocumentRepository docRepo;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Document findOneById(Long id) {
		return getDocRepo().getOne(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Document save(Document doc) {
		Document save = getDocRepo().save(doc);
		return save;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Document> findByAppCodeOrderByLastModifiedDateDesc(String appCode) {
		return getDocRepo().findByAppCodeOrderByLastModifiedDateDesc(appCode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Document> findByAppCodeAndVersion(String appCode, String version) {
		return getDocRepo().findByAppCodeAndVersion(appCode, version);
	}

	/**
	 * @return the {@code DocumentRepository}
	 */
	public DocumentRepository getDocRepo() {
		return docRepo;
	}

}
