package pl.ee.authentication.infrastructure.repository.ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

@Service
public class PersonRepository {

  private LdapTemplate ldapTemplate;

  @Autowired
  public PersonRepository(LdapTemplate ldapTemplate) {
    this.ldapTemplate = ldapTemplate;
  }

}
