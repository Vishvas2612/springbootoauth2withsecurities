package com.atishay.springboot.audit.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atishay.springboot.audit.repository.GenericAuditCustomRepository;
import com.atishay.springboot.audit.service.GenericAuditCustomService;

/***
 * This service layer impl class gives implementation of abstract method (1) to
 * save any history data and (2) to get last modified data.
 * 
 * @author vishvas
 *
 */
@Service
public class GenericAuditCustomServiceImpl implements GenericAuditCustomService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericAuditCustomServiceImpl.class);

	@Autowired
	private GenericAuditCustomRepository genericAuditCustomRepository;

	@Transactional(value = "transactionManagerForAudit")
	@Override
	public <T> void save(T t) {
		genericAuditCustomRepository.save(t);
	}

	@Override
	public <T> Optional<T> getLastModifiedData(T t, String idOfEntity) {
		return genericAuditCustomRepository.getLastModifiedData(t, idOfEntity);
	}

	@Transactional(value = "transactionManagerForAudit")
	@Override
	public <T> void remove(T t) {
		genericAuditCustomRepository.remove(t);
	}

}
