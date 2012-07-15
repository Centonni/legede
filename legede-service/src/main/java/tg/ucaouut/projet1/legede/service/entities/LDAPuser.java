/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.ucaouut.projet1.legede.service.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author centonni
 */
public class LDAPuser {
    
    private String nom;
    private String login;
    private String prenom;
    private String password;
    
    private static List<LDAPuser> ldapUser=new ArrayList<LDAPuser>();
    
    public LDAPuser(){
        
    }
    
    public LDAPuser(String nom,String prenom,String login,String password){
        this.nom=nom;
        this.prenom=prenom;
        this.login=login;
        this.password=password;
        initList(this);
        
    }
    
    private void initList(LDAPuser ldpu){
        
        ldapUser.add(ldpu);
    }
    
    public static List<LDAPuser> getUsersFromLdap(){
        return ldapUser;
    }
    
    public String getPassword(){
 
        return password;
    }
    
    public static LDAPuser getLdapUser(String login,List<LDAPuser> ldapUsers){
        
        for(int i=0;i<ldapUsers.size();i++){
            if(login.equals(ldapUsers.get(i).getLogin())){
                return ldapUsers.get(i);
            }
        }
        
        return null;
    }
    
    public String getLogin(){
        return login;
    }
}
