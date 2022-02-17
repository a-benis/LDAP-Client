package com.ldap.ldapclient.service;

import com.ldap.ldapclient.domain.LdapPrincipal;
import com.unboundid.ldap.sdk.LDAPException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * The class Ldap user service test.
 */
@SpringBootTest
public class LdapUserServiceTest {

	/**
	 * The constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LdapUserServiceTest.class);

	/**
	 * The Ldap user service.
	 */
	@Autowired
	private ILdapUserService ldapUserService;

	/**
	 * Test find by user id.
	 *
	 * @throws LDAPException the ldap exception
	 */
	@Test
	public void testFindByUserId() throws LDAPException {
		LdapPrincipal ldapPrincipal = ldapUserService.findByUserId("0784508382");
		assert ldapPrincipal.getUserName().equals("0784508382");
	}

	/**
	 * Test create.
	 *
	 * @throws LDAPException the ldap exception
	 */
	@Test
	public void testCreate() throws LDAPException {
		LdapPrincipal ldapPrincipal = new LdapPrincipal();
		ldapPrincipal.setUserName("0788744915");
		ldapPrincipal.setLocked(false);
		ldapPrincipal.setFirstName("Firstname User");
		ldapPrincipal.setLastName("Lastname User");
		ldapPrincipal.setCommonName("Common name User");
		ldapPrincipal.setPassword("Test@123");
		ldapPrincipal.setDescription("Description to be set here");
		ldapPrincipal.setPhoneNumber("0788744915");
		ldapPrincipal.setCellNumber("0788744915");
		ldapPrincipal.setEmailAddress("support.external@mail.com");
		ldapPrincipal.setEmployeeNumber("22222222222222");

		LdapPrincipal savedUser = ldapUserService.create(ldapPrincipal, false);
		assert savedUser.getUserName().equals("0788744915");
	}
}
