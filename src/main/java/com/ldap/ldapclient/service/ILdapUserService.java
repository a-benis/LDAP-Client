package com.ldap.ldapclient.service;

import com.ldap.ldapclient.domain.LdapPrincipal;
import com.unboundid.ldap.sdk.LDAPException;

/**
 * The interface Ldap user service.
 */
public interface ILdapUserService {

	/**
	 * Find by user id ldap principal.
	 *
	 * @param id the id
	 * @return the ldap principal
	 * @throws LDAPException the ldap exception
	 */
	LdapPrincipal findByUserId(final String id) throws LDAPException;

	/**
	 * Create ldap principal.
	 *
	 * @param person                         the person
	 * @param mustChangePasswordOnFirstLogin the must change password on first login
	 * @return the ldap principal
	 * @throws LDAPException the ldap exception
	 */
	LdapPrincipal create(LdapPrincipal person, boolean mustChangePasswordOnFirstLogin) throws LDAPException;
}
