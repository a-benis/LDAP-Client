# A sample LDAP client project

## 1. Running LDAP

In order to run LDAP, you'll need to have docker installed.

From the root directory of this project, run the following command:

```docker-compose up -d```

After the command is successful, you should have LDAP running and accessible
on ports: ```insecure: 389``` and ```secure: 636```

## 2. Testing the connection

A test class can be found under ```src/test/java/com.ldap.ldapclient/service/LdapUserServiceTest```
and two tests can be found in there to test fetching and creating data in LDAP.
