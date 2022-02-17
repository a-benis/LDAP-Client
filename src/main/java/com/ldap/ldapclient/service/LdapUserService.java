package com.ldap.ldapclient.service;

import com.ldap.ldapclient.domain.LdapPrincipal;
import com.ldap.ldapclient.util.ILdapConstant;
import com.ldap.ldapclient.util.LdapConnection;
import com.unboundid.ldap.sdk.AddRequest;
import com.unboundid.ldap.sdk.Attribute;
import com.unboundid.ldap.sdk.Filter;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.SearchRequest;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchResultEntry;
import com.unboundid.ldap.sdk.SearchScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Ldap user service.
 */
@Service
public class LdapUserService implements ILdapUserService, ILdapConstant {

	/**
	 * The Ldap connection.
	 */
	@Autowired
	private LdapConnection ldapConnection;

	/**
	 * Find by user id ldap principal.
	 *
	 * @param id the id
	 * @return the ldap principal
	 * @throws LDAPException the ldap exception
	 */
	@Override
	public LdapPrincipal findByUserId(final String id) throws LDAPException {

		LDAPConnection connection = null;

		try {
			LdapPrincipal res = null;

			Filter filter = Filter.createEqualityFilter(ORGANIZATION_NAME, "Irembo");

			connection = ldapConnection.getAdminConnection();
			String searchUid = UID + "=" + id;
			SearchRequest searchRequest = new SearchRequest(searchUid + "," + ldapConnection.getLdapBaseDN(), SearchScope.SUB, filter);
			SearchResult searchResult = connection.search(searchRequest);

			if (searchResult.getEntryCount() == 1) {
				res = this.prepareLdapPrincipal(searchResult.getSearchEntries().get(0));
			}

			if (res == null) {
				throw new RuntimeException("Principal not found");
			}

			return res;
		} finally {
			this.releaseConnection(connection);
		}
	}

	/**
	 * Create ldap principal.
	 *
	 * @param person                         the person
	 * @param mustChangePasswordOnFirstLogin the must change password on first login
	 * @return the ldap principal
	 * @throws LDAPException the ldap exception
	 */
	@Override
	public LdapPrincipal create(final LdapPrincipal person, final boolean mustChangePasswordOnFirstLogin) throws LDAPException {

		LDAPConnection connection = null;

		try {
			List<Attribute> attributes = this.prepareAttributes(person);

			if (mustChangePasswordOnFirstLogin) {
				attributes.add(new Attribute(USER_MUST_CHANGE_PASSWORD, "TRUE"));
			}
			AddRequest addRequest = new AddRequest(ldapConnection.getUserDN(person.getUserName()), attributes);

			connection = ldapConnection.getAdminConnection();
			connection.add(addRequest);

			// lets make sure that the password is not returned
			person.setPassword(null);

			return person;
		} finally {
			this.releaseConnection(connection);
		}
	}

	/**
	 * Prepare ldap principal ldap principal.
	 *
	 * @param entry the entry
	 * @return the ldap principal
	 */
	private LdapPrincipal prepareLdapPrincipal(final SearchResultEntry entry) {
		LdapPrincipal res = new LdapPrincipal();

		res.setUserName(entry.getAttributeValue(UID));
		res.setLastName(entry.getAttributeValue(LAST_NAME));
		res.setFirstName(entry.getAttributeValue(FIRST_NAME));
		res.setCommonName(entry.getAttributeValue(COMMON_NAME));
		res.setDescription(entry.getAttributeValue(DESCRIPTION));
		res.setPhoneNumber(entry.getAttributeValue(PHONE_NUMBER));
		res.setEmailAddress(entry.getAttributeValue(MAIL));
		res.setCellNumber(entry.getAttributeValue(MOBILE));
		res.setEmployeeNumber(entry.getAttributeValue(EMPLOYEE_NUMBER));
		res.setTitle(entry.getAttributeValue(TITLE));
		res.setInstitution(entry.getAttributeValue(ORGANIZATION_NAME));
		res.setPosition(entry.getAttributeValue(EMPLOYEE_TYPE));
		res.setPassCode(entry.getAttributeValue(PASS_CODE));
		res.setUserImage(entry.getAttributeValueBytes(USER_PHOTO));

		res.setLocked(entry.getAttribute(ILdapConstant.USER_ACCOUNT_LOCKED) != null);

		// adding new active flag
		Attribute attribute = entry.getAttribute(ILdapConstant.ACTIVE);
		if (attribute == null) {
			res.setActive(Boolean.TRUE);
		} else {
			res.setActive(entry.getAttribute(ILdapConstant.ACTIVE).getValueAsBoolean());
		}

		return res;
	}

	/**
	 * Prepare attributes list.
	 *
	 * @param person the person
	 * @return the list
	 */
	private List<Attribute> prepareAttributes(final LdapPrincipal person) {
		List<Attribute> result = new ArrayList<>();

		result.add(new Attribute(OBJECT_CLASS, LDAP_CLASS));

		if (null != person.getUserName()) {
			result.add(new Attribute(UID, person.getUserName()));
		}
		if (null != person.getLastName()) {
			result.add(new Attribute(LAST_NAME, person.getLastName()));
		}
		if (null != person.getEmployeeNumber()) {
			result.add(new Attribute(EMPLOYEE_NUMBER, person.getEmployeeNumber()));
		}
		if (null != person.getCommonName()) {
			result.add(new Attribute(COMMON_NAME, person.getCommonName()));
		}
		if (null != person.getTitle()) {
			result.add(new Attribute(TITLE, person.getTitle()));
		}
		if (null != person.getFirstName()) {
			result.add(new Attribute(FIRST_NAME, person.getFirstName()));
		}
		if (null != person.getDescription()) {
			result.add(new Attribute(DESCRIPTION, person.getDescription()));
		}
		if (null != person.getPhoneNumber()) {
			result.add(new Attribute(PHONE_NUMBER, person.getPhoneNumber()));
		}
		if (null != person.getEmailAddress()) {
			result.add(new Attribute(MAIL, person.getEmailAddress()));
		}
		if (null != person.getCellNumber()) {
			result.add(new Attribute(MOBILE, person.getCellNumber()));
		}
		if (person.getPassword() != null) {
			result.add(new Attribute(USER_PASSWORD, person.getPassword()));
		}

		if (null != person.getInstitution()) {
			result.add(new Attribute(ORGANIZATION_NAME, person.getInstitution()));
		}

		if (null != person.getPosition()) {
			result.add(new Attribute(EMPLOYEE_TYPE, person.getPosition()));
		}

		if (null != person.getPassCode()) {
			result.add(new Attribute(PASS_CODE, person.getPassCode()));
		}

		if (null != person.getUserImage()) {
			result.add(new Attribute(USER_PHOTO, person.getUserImage()));
		}

		return result;
	}

	/**
	 * Release connection.
	 *
	 * @param connection the connection
	 */
	private void releaseConnection(final LDAPConnection connection) {
		if (connection != null) {
			connection.close();
		}
	}
}
