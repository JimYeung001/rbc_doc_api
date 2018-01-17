package com.rbc.docapi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

	private static final String APPCODE = "appCode";
	private static final String VERSION = "version";
	private static final String LAST_MODIFIED_DATE = "lastModifieddDate";
	private static final String ORDERBY_DESC = "desc";

	/**
	 * Document Repository {@code DocumentRepository}
	 */
	@Autowired
	private DocumentRepository docRepo;

	/**
	 * EmtityManger
	 */
	@Autowired
	private EntityManager em;

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
	public List<Document> findAllByAppCodeOrderByLastModifiedDateDesc(String appCode) {
		Map<String, String> params = new HashMap<>();
		params.put(APPCODE, appCode);
		List<Document> result = criterialQuery(params, ORDERBY_DESC);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Document> findByAppCodeAndVersion(String appCode, String version) {
		Map<String, String> params = new HashMap<>();
		params.put(APPCODE, appCode);
		params.put(VERSION, version);
		return criterialQuery(params, null);
	}

	/**
	 * Fetch result based on params
	 * 
	 * @param params
	 *            Map<String, String>
	 * @return List<Document>
	 */
	private List<Document> criterialQuery(Map<String, String> params, String orderBy) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Document> cq = cb.createQuery(Document.class);
		Root<Document> doc = cq.from(Document.class);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			cq.where(cb.equal(doc.get(entry.getKey()), entry.getValue()));
		}
		if (orderBy != null && orderBy.equalsIgnoreCase(ORDERBY_DESC)) {
			cq.orderBy(cb.desc(doc.get(LAST_MODIFIED_DATE)));
		}

		TypedQuery<Document> q = em.createQuery(cq);
		return q.getResultList();
	}

	/**
	 * @return the {@code DocumentRepository}
	 */
	public DocumentRepository getDocRepo() {
		return docRepo;
	}

}
