/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.ucaouut.projet1.legede.service.conf;

import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 *
 * @author centonni
 */
public class LdapContextSourceFactory {
    
    private static String serveur="localhost";
    private static String port="389";
    private static String base="dc=example,dc=com";
    

    public static ContextSource getLdapContextSource() throws Exception {
        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setUrl("ldap://"+serveur+":"+port);
        ldapContextSource.setBase(base);
        ldapContextSource.afterPropertiesSet();
        return ldapContextSource;
    }
}
