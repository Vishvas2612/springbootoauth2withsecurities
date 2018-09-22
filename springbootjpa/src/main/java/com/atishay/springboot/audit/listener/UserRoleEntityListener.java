package com.atishay.springboot.audit.listener;

import javax.persistence.PostPersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.springframework.stereotype.Service;

import com.atishay.springboot.audit.model.UserRoleAudit;
import com.atishay.springboot.audit.service.GenericAuditCustomService;
import com.atishay.springboot.common.enums.EnumForAuditAction;
import com.atishay.springboot.common.utils.BeanUtils;
import com.atishay.springboot.project.model.UserRole;

/***
 * This class indicates listener class for UserRole.java
 * 
 * @author vishvas
 *
 */
@Service
public class UserRoleEntityListener {

	@PostPersist
	public void prePersist(UserRole target) {

		perform(target, EnumForAuditAction.INSERTED);
	}

	@PreUpdate
	public void preUpdate(UserRole target) {
		perform(target, EnumForAuditAction.UPDATED);
	}

	@PreRemove
	public void preRemove(UserRole target) {
		perform(target, EnumForAuditAction.DELETED);
	}

	private void perform(UserRole target, EnumForAuditAction action) {

		new Runnable() {
			public void run() {
				GenericAuditCustomService genericAuditCustomService = BeanUtils
						.getBean(GenericAuditCustomService.class);
				genericAuditCustomService.save(new UserRoleAudit(target, action));

			}
		}.run();

	}

}
