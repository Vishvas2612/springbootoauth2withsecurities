package com.atishay.springboot.common.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/***
 * This class indicates request data for sign up.
 * 
 * @author vishvas
 *
 */
public class SignUpRequest {

	@NotBlank
	@Size(min = 2, max = 100)
	private String organizationName;

	@NotBlank
	@Size(min = 2, max = 35)
	private String firstName;

	@NotBlank
	@Size(min = 2, max = 35)
	private String lastName;

	@NotBlank
	@Size(max = 254)
	@Email
	private String email;

	@NotBlank
	@Size(min = 2, max = 15)
	private String mobileNumber;

	@NotBlank
	@Size(min = 2, max = 50)
	private String countryId;

	@NotBlank
	@Size(min = 5, max = 25)
	private String username;

	@NotBlank
	@Size(min = 6, max = 25)
	private String password;

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
