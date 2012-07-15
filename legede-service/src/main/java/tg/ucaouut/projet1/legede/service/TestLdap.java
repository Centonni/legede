/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.ucaouut.projet1.legede.service;

import tg.ucaouut.projet1.legede.service.conf.LdapContextSourceFactory;
import java.util.ArrayList;
import tg.ucaouut.projet1.legede.service.entities.Person;
import tg.ucaouut.projet1.legede.service.dao.impl.PersonDAO;
import java.util.List;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;

/**
 *
 * @author centonni
 */
public class TestLdap {

    /** Retrieve a Kurt Person from ldap server and display Kurt in
    Standard Out */
    public static void main(String[] args) {
// 1 Retrieve a LdapContextSource
        ContextSource ldapContextSource = null;
        try {
            ldapContextSource = LdapContextSourceFactory.getLdapContextSource();
        } catch (Exception e) {
            System.out.println("Impossible to get a LdapContextSource. ");
            e.printStackTrace();
        }
// 2 Instanciate a LdapTemplate
        LdapTemplate ldapTemplate = new LdapTemplate();
        ldapTemplate.setContextSource(ldapContextSource);
        boolean authenticated = ldapTemplate.authenticate("", "(uid=serge.innocent)", "serge");
// 3 instanciate a PersonDAO
        PersonDAO dao = new PersonDAO();
        dao.setLdapTemplate(ldapTemplate);
// 4 retrieve a Person and display it
        ArrayList<Person> a = new ArrayList<Person>();
        boolean e;
        e = a.addAll(dao.findAll());
        int y;y=a.size();
        System.out.println("*************debut************************");
        for (Person person : a) {
            
            System.out.println("Uid: " + person.getUid());
            System.out.println("FirstName: " + person.getFirstName());
            System.out.println("LastName: " + person.getLastName());
            System.out.println("Email: " + person.getEmail() + "\n");

            }System.out.println("**************fin***********************");
        


        Person person = dao.findByPrimaryKey("serge.innocent");
        System.out.println("Uid: " + person.getUid());
        System.out.println("FirstName: " + person.getFirstName());
        System.out.println("LastName: " + person.getLastName());
        System.out.println("Email: " + person.getEmail() + "\n");

// 5 retrieve a list of person
        List listPerson = dao.getPersonNamesByLastName("*e*");

        for (Object object : listPerson) {
            System.out.println("Person: " + object);
        }
    }
}
