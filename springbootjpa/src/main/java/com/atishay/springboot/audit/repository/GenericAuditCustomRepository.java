package com.atishay.springboot.audit.repository;

import java.util.List;
import java.util.Optional;

/***
 * This dao layer interface gives abstraction method (1) to save any history
 * data and (2) to get last modified data.
 * 
 * @author vishvas
 *
 */
public interface GenericAuditCustomRepository {

	public <T> void save(T t);

	public <T> Optional<T> getLastModifiedData(T t, String idOfEntity);

	public <T> Optional<T> findById(String id, T t);

	public <T> List<T> getAllRecords(T t);

	public <T> void remove(T t);
}
