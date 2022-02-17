/*
 * Copyright (c) Irembo 2018.
 *
 * All rights reserved.
 */

package com.ldap.ldapclient.util;

import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPConnectionPool;
import com.unboundid.ldap.sdk.LDAPConnectionPoolStatistics;
import com.unboundid.ldap.sdk.SimpleBindRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


/**
 * The class Ldap connection.
 */
@Component
public class LdapConnection {

	/**
	 * The constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LdapConnection.class);

	/**
	 * The Ldap host.
	 */
	@Value("${ldap.host}")
	private String ldapHost;

	/**
	 * The Ldap port.
	 */
	@Value("${ldap.port}")
	private int ldapPort;

	/**
	 * The Ldap admin login.
	 */
	@Value("${ldap.username}")
	private String ldapAdminLogin;

	/**
	 * The Ldap admin password.
	 */
	@Value("${ldap.password}")
	private String ldapAdminPassword;

	/**
	 * The Ldap base dn.
	 */
	@Value("${ldap.basedn}")
	private String ldapBaseDN;

	/**
	 * The Connection pool.
	 */
	private LDAPConnectionPool connectionPool;

	/**
	 * The Pool size.
	 */
	private int poolSize = 10;

	/**
	 * Gets admin connection.
	 *
	 * @return the admin connection
	 */
	public LDAPConnection getAdminConnection() {
		try {
			LDAPConnection connection = this.getConnectionPool().getConnection();
			connection.bind(new SimpleBindRequest(ldapAdminLogin, ldapAdminPassword));
			return connection;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Gets user connection.
	 *
	 * @return the user connection
	 */
	public LDAPConnection getUserConnection() {
		try {
			return this.getConnectionPool().getConnection();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Log statistics info.
	 */
	private void logStatisticsInfo() {
		if (connectionPool != null) {
			LOGGER.info(connectionPool.getConnectionPoolStatistics().toString());
		} else {
			LOGGER.error("Ldap Connection Pool is NULL");
		}
	}

	/**
	 * Gets connection pool.
	 *
	 * @return the connection pool
	 */
	private LDAPConnectionPool getConnectionPool() {
		try {
			if (connectionPool == null) {
				SSLSocketFactory sslSocketFactory = this.defaultSslSocketFactory();
				connectionPool = new LDAPConnectionPool(new LDAPConnection(ldapHost, ldapPort), this.poolSize);
			}
			this.logStatisticsInfo();
			return connectionPool;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Default ssl socket factory ssl socket factory.
	 *
	 * @return the ssl socket factory
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	private SSLSocketFactory defaultSslSocketFactory() throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		}};

		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, trustAllCerts, null);
		return sslContext.getSocketFactory();
	}

	/**
	 * Gets stats.
	 *
	 * @return the stats
	 */
	public LDAPConnectionPoolStatistics getStats() {
		return this.getConnectionPool().getConnectionPoolStatistics();
	}

	/**
	 * Gets user dn.
	 *
	 * @param userId the user id
	 * @return the user dn
	 */
	public String getUserDN(final String userId) {
		return "uid=" + userId + "," + ldapBaseDN;
	}

	/**
	 * Gets ldap host.
	 *
	 * @return the ldap host
	 */
	public String getLdapHost() {
		return ldapHost;
	}

	/**
	 * Sets ldap host.
	 *
	 * @param ldapHost the ldap host
	 */
	public void setLdapHost(final String ldapHost) {
		this.ldapHost = ldapHost;
	}

	/**
	 * Gets ldap port.
	 *
	 * @return the ldap port
	 */
	public int getLdapPort() {
		return ldapPort;
	}

	/**
	 * Sets ldap port.
	 *
	 * @param ldapPort the ldap port
	 */
	public void setLdapPort(final int ldapPort) {
		this.ldapPort = ldapPort;
	}

	/**
	 * Gets ldap admin login.
	 *
	 * @return the ldap admin login
	 */
	public String getLdapAdminLogin() {
		return ldapAdminLogin;
	}

	/**
	 * Sets ldap admin login.
	 *
	 * @param ldapAdminLogin the ldap admin login
	 */
	public void setLdapAdminLogin(final String ldapAdminLogin) {
		this.ldapAdminLogin = ldapAdminLogin;
	}

	/**
	 * Gets ldap admin password.
	 *
	 * @return the ldap admin password
	 */
	public String getLdapAdminPassword() {
		return ldapAdminPassword;
	}

	/**
	 * Sets ldap admin password.
	 *
	 * @param ldapAdminPassword the ldap admin password
	 */
	public void setLdapAdminPassword(final String ldapAdminPassword) {
		this.ldapAdminPassword = ldapAdminPassword;
	}

	/**
	 * Gets ldap base dn.
	 *
	 * @return the ldap base dn
	 */
	public String getLdapBaseDN() {
		return ldapBaseDN;
	}

	/**
	 * Sets ldap base dn.
	 *
	 * @param ldapBaseDN the ldap base dn
	 */
	public void setLdapBaseDN(final String ldapBaseDN) {
		this.ldapBaseDN = ldapBaseDN;
	}

	/**
	 * Gets pool size.
	 *
	 * @return the pool size
	 */
	public int getPoolSize() {
		return poolSize;
	}

	/**
	 * Sets pool size.
	 *
	 * @param poolSize the pool size
	 */
	public void setPoolSize(final int poolSize) {
		this.poolSize = poolSize;
	}
}
