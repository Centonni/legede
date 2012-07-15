/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.ucaouut.projet1.legede.service.dao.impl;

import java.util.*;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.LikeFilter;
import tg.ucaouut.projet1.legede.service.dao.IPersonDAO;
import tg.ucaouut.projet1.legede.service.entities.Person;

/**
 *
 * @author centonni
 */
public class PersonDAO implements IPersonDAO {

    private LdapTemplate ldapTemplate;

    private static class PersonAttributMapper implements AttributesMapper {

        public Person mapFromAttributes(Attributes attrs) throws javax.naming.NamingException {
            Person p = new Person();
            p.setFirstName(attrs.get("givenName").get().toString());
            p.setLastName(attrs.get("sn").get().toString());
            p.setUid(attrs.get("uid").get().toString());
            p.setEmail(attrs.get("mail").get().toString());
            return p;
        }
    }

    public Person findByPrimaryKey(String uid) {
        Name dn = buildDn(uid);
        return (Person) ldapTemplate.lookup(dn, new PersonAttributMapper());
    }

    private Name buildDn(String uid) {

        DistinguishedName dn = new DistinguishedName();

        dn.add("ou", "People");
        dn.add("uid", uid);

        return dn;
    }

    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public List getPersonNamesByLastName(String lastName) {
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectclass", "person"));
        filter.and(new LikeFilter("sn", lastName));
        return ldapTemplate.search("", filter.encode(), new PersonAttributMapper());
    }

    public void create(Person person) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(Person person) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(Person person) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List findAll() {
        
        return ldapTemplate.search("", "(objectclass=person)", new PersonAttributMapper());
    }
}
