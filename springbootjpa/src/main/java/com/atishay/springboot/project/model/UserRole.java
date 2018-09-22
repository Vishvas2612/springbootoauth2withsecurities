package com.atishay.springboot.project.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.atishay.springboot.audit.listener.UserRoleEntityListener;
import com.atishay.springboot.common.utils.MethodUtils;
import com.atishay.springboot.common.utils.VariableUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/***
 * This class indicates details for user roles.
 * 
 * @author vishvas
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = VariableUtils.TABLE_NAME_FOR_USER_ROLES)
@JsonInclude(Include.NON_EMPTY)
@EntityListeners(UserRoleEntityListener.class)
public class UserRole extends Auditable<String> implements Serializable {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "userRoleId", nullable = false, insertable = false, updatable = false)
	private String id;

	@Column(name = "userRoleName", columnDefinition = "varchar(100)")
	private String userRoleName;

	@OneToMany(mappedBy = "userRole")
	private Set<User> setOfUsers;

	@Column(name = "description", columnDefinition = "varchar(100)")
	private String description;

	@PreRemove
	protected void preRemove() {
		Set<User> setOfUser = this.getSetOfUsers();
		if (!MethodUtils.isSetIsNullOrEmpty(setOfUser)) {
			setOfUser.forEach(user -> {
				user.setUserRole(null);
			});
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public Set<User> getSetOfUsers() {
		return setOfUsers;
	}

	public void setSetOfUsers(Set<User> setOfUsers) {
		this.setOfUsers = setOfUsers;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
