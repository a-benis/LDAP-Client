package com.ldap.ldapclient.domain;

import java.io.Serializable;

/**
 * The class Ldap principal.
 */
public class LdapPrincipal implements Serializable {

	/**
	 * The constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The User name.
	 */
	private String userName;

	/**
	 * The Locked.
	 */
	private Boolean locked;

	/**
	 * The First name.
	 */
	private String firstName;

	/**
	 * The Last name.
	 */
	private String lastName;

	/**
	 * The Common name.
	 */
	private String commonName;

	/**
	 * The Password.
	 */
	private String password;

	/**
	 * The Description.
	 */
	private String description;

	/**
	 * The Phone number.
	 */
	private String phoneNumber;

	/**
	 * The Cell number.
	 */
	private String cellNumber;

	/**
	 * The Email address.
	 */
	private String emailAddress;

	/**
	 * The Employee number.
	 */
	private String employeeNumber;

	/**
	 * The Title.
	 */
	private String title;

	/**
	 * The Position.
	 */
	private String position;

	/**
	 * The Institution.
	 */
	private String institution;

	/**
	 * The Pass code.
	 */
	private String passCode;

	/**
	 * The User image.
	 */
	private byte[] userImage;

	/**
	 * The Active.
	 */
	private Boolean active;

	/**
	 * Gets active.
	 *
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * Sets active.
	 *
	 * @param active the active
	 */
	public void setActive(final Boolean active) {
		this.active = active;
	}

	/**
	 * Gets phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets phone number.
	 *
	 * @param phoneNumber the phone number
	 */
	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password.
	 *
	 * @param password the password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Gets description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets description.
	 *
	 * @param description the description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Gets user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets user name.
	 *
	 * @param userName the user name
	 */
	public void setUserName(final String userName) {
		this.userName = userName;
	}

	/**
	 * Gets first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets first name.
	 *
	 * @param firstName the first name
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets last name.
	 *
	 * @param lastName the last name
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets cell number.
	 *
	 * @return the cell number
	 */
	public String getCellNumber() {
		return cellNumber;
	}

	/**
	 * Sets cell number.
	 *
	 * @param cellNumber the cell number
	 */
	public void setCellNumber(final String cellNumber) {
		this.cellNumber = cellNumber;
	}

	/**
	 * Gets email address.
	 *
	 * @return the email address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets email address.
	 *
	 * @param emailAddress the email address
	 */
	public void setEmailAddress(final String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Get user image byte [ ].
	 *
	 * @return the byte [ ]
	 */
	public byte[] getUserImage() {
		return userImage;
	}

	/**
	 * Sets user image.
	 *
	 * @param userImage the user image
	 */
	public void setUserImage(final byte[] userImage) {
		this.userImage = userImage;
	}

	/**
	 * Gets common name.
	 *
	 * @return the common name
	 */
	public String getCommonName() {
		return commonName;
	}

	/**
	 * Sets common name.
	 *
	 * @param commonName the common name
	 */
	public void setCommonName(final String commonName) {
		this.commonName = commonName;
	}

	/**
	 * Gets locked.
	 *
	 * @return the locked
	 */
	public Boolean getLocked() {
		return locked;
	}

	/**
	 * Sets locked.
	 *
	 * @param locked the locked
	 */
	public void setLocked(final Boolean locked) {
		this.locked = locked;
	}

	/**
	 * Gets title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets title.
	 *
	 * @param title the title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Gets employee number.
	 *
	 * @return the employee number
	 */
	public String getEmployeeNumber() {
		return employeeNumber;
	}

	/**
	 * Sets employee number.
	 *
	 * @param employeeNumber the employee number
	 */
	public void setEmployeeNumber(final String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	/**
	 * Gets position.
	 *
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Sets position.
	 *
	 * @param position the position
	 */
	public void setPosition(final String position) {
		this.position = position;
	}

	/**
	 * Gets institution.
	 *
	 * @return the institution
	 */
	public String getInstitution() {
		return institution;
	}

	/**
	 * Gets pass code.
	 *
	 * @return the pass code
	 */
	public String getPassCode() {
		return passCode;
	}

	/**
	 * Sets pass code.
	 *
	 * @param passCode the pass code
	 */
	public void setPassCode(final String passCode) {
		this.passCode = passCode;
	}

	/**
	 * Sets institution.
	 *
	 * @param institution the institution
	 */
	public void setInstitution(final String institution) {
		this.institution = institution;
	}

	/**
	 * Is new boolean.
	 *
	 * @return the boolean
	 */
	public boolean isNew() {
		return this.active == null;
	}
}
