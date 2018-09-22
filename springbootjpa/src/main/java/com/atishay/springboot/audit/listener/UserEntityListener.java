package com.atishay.springboot.audit.listener;

import java.util.Optional;

import javax.persistence.PostPersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.springframework.stereotype.Service;

import com.atishay.springboot.audit.model.UserAudit;
import com.atishay.springboot.audit.model.UserRoleAudit;
import com.atishay.springboot.audit.service.GenericAuditCustomService;
import com.atishay.springboot.common.enums.EnumForAuditAction;
import com.atishay.springboot.common.utils.BeanUtils;
import com.atishay.springboot.common.utils.MethodUtils;
import com.atishay.springboot.project.model.User;

/***
 * This class indicates listener class for User.java
 * 
 * @author vishvas
 *
 */
@Service
public class UserEntityListener {

	@PostPersist
	public void prePersist(User target) {
		perform(target, EnumForAuditAction.INSERTED);
	}

	@PreUpdate
	public void preUpdate(User target) {
		perform(target, EnumForAuditAction.UPDATED);
	}

	@PreRemove
	public void preRemove(User target) {
		perform(target, EnumForAuditAction.DELETED);
	}

	private void perform(User target, EnumForAuditAction action) {

		new Runnable() {
			public void run() {
				GenericAuditCustomService genericAuditCustomService = BeanUtils
						.getBean(GenericAuditCustomService.class);

				Optional<UserRoleAudit> userRoleAudit = Optional.ofNullable(null);
				if (!MethodUtils.isObjectisNullOrEmpty(target.getUserRole())) {
					userRoleAudit = genericAuditCustomService.getLastModifiedData(new UserRoleAudit(),
							target.getUserRole().getId());
				}

				genericAuditCustomService
						.save(new UserAudit(target, userRoleAudit.isPresent() ? userRoleAudit.get() : null, action));

			}
		}.run();

	}

}
