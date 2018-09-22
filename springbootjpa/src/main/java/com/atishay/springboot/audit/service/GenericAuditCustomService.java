package com.atishay.springboot.audit.service;

import java.util.Optional;

/***
 * This service layer interface gives abstraction method (1) to save any history
 * data and (2) to get last modified data.
 * 
 * @author vishvas
 *
 */
public interface GenericAuditCustomService {

	public <T> void save(T t);

	public <T> Optional<T> getLastModifiedData(T t, String idOfEntity);

	public <T> void remove(T t);
}
