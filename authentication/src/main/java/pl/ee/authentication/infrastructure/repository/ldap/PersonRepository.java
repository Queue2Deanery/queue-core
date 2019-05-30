package pl.ee.authentication.infrastructure.repository.ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.SearchScope;
import org.springframework.stereotype.Service;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
public class PersonRepository {

  private LdapTemplate ldapTemplate;

  @Autowired
  public PersonRepository(LdapTemplate ldapTemplate) {
    this.ldapTemplate = ldapTemplate;
  }

}
