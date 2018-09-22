package com.atishay.springboot.audit.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.atishay.springboot.audit.model.UserAudit_;
import com.atishay.springboot.audit.repository.GenericAuditCustomRepository;
import com.atishay.springboot.common.utils.MethodUtils;

/***
 * This dao layer impl class gives implementation of abstract method (1) to save
 * any history data and (2) to get last modified data.
 * 
 * @author vishvas
 *
 */
@Repository
public class GenericAuditCustomRepositoryImpl implements GenericAuditCustomRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericAuditCustomRepositoryImpl.class);

	@PersistenceContext(unitName = "auditPersistenceUnit")
	private EntityManager entityManager;

	@Override
	public <T> void save(T t) {
		entityManager.persist(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Optional<T> getLastModifiedData(T t, String idOfEntity) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = (CriteriaQuery<T>) cb.createQuery(t.getClass());
		Root<T> root = (Root<T>) cq.from(t.getClass());

		Path<String> pathForID = root.get(UserAudit_.id.getName());

		Predicate predicate = cb.equal(root.get(UserAudit_.idOfEntity.getName()), idOfEntity);

		cq.where(predicate);
		cq.multiselect(pathForID);

		cq.orderBy(cb.desc(root.get(UserAudit_.createdDate.getName())));

		TypedQuery<T> q = entityManager.createQuery(cq);
		List<T> list = q.getResultList();

		System.out.println("list == null : " + MethodUtils.isListIsNullOrEmpty(list));

		Optional<T> optional = MethodUtils.isListIsNullOrEmpty(list) ? null : Optional.of(list.get(0));

		return optional;

	}

	@Override
	public <T> Optional<T> findById(String id, T t) {

		return Optional.ofNullable((T) entityManager.find(t.getClass(), id));
	}

	@Override
	public <T> List<T> getAllRecords(T t) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteria = (CriteriaQuery<T>) builder.createQuery(t.getClass());
		Root<T> criteriaRoot = (Root<T>) criteria.from(t.getClass());

		criteria.select(criteriaRoot);

		TypedQuery<T> typedQuery = entityManager.createQuery(criteria);
		return typedQuery.getResultList();
	}

	@Override
	public <T> void remove(T t) {
		entityManager.remove(entityManager.merge(t));
	}

}
