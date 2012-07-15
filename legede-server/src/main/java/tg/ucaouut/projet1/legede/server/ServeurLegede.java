/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.ucaouut.projet1.legede.server;

import java.util.logging.Level;
import java.util.logging.Logger;
import tg.ucaouut.projet1.legede.service.interfaces.InterServeur;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import tg.ucaouut.projet1.legede.service.conf.LdapContextSourceFactory;
import tg.ucaouut.projet1.legede.service.dao.impl.PersonDAO;
import tg.ucaouut.projet1.legede.service.entities.Person;
import tg.ucaouut.projet1.legede.service.interfaces.InterClient;

/**
 * 
 * @author centonni
 */
public class ServeurLegede extends UnicastRemoteObject implements InterServeur {

    private List<Person> Persons;
    private HashMap<Object, Person> userOnLine;
    private HashMap<String,Object>  userOnLineLogin;
    private List<Object> userOnLineL;
    private Enumeration eUserOnLine;
    private ContextSource ldapContextSource = null;
    private LdapTemplate ldapTemplate = null;
    private PersonDAO dao;
//    private HashMap<String,Object> simpleUser;
//    private Enumeration eSimpleUser;

    /**
     * 
     * @throws RemoteException 
     */
    public ServeurLegede() throws RemoteException {
        dao=new PersonDAO();
        userOnLine = new HashMap<Object, Person>();
        userOnLineLogin=new HashMap<String, Object>();
        Persons = new ArrayList<Person>();
        userOnLineL=new ArrayList<Object>();
        //simpleUser=new HashMap<String,Object>();
        try {
            ldapContextSource = LdapContextSourceFactory.getLdapContextSource();
            ldapTemplate = new LdapTemplate();
            ldapTemplate.setContextSource(ldapContextSource);
            dao.setLdapTemplate(ldapTemplate);
        } catch (Exception e) {
            System.out.println("Impossible d'avoir un LdapContextSource. ");
            e.printStackTrace();
        }

    }

    public String test() {
        return "it works!!!";
    }

    /**
     * 
     * @param login
     * @param password
     * @param user
     * @return
     * @throws RemoteException 
     */
    public boolean getConnexion(String login, String password, Object user) throws RemoteException {
        
        boolean b=ldapTemplate.authenticate("", "(uid="+login+")", password);
        if(b){
            Person p=dao.findByPrimaryKey(login);
            if(!userOnLineLogin.containsKey(p.getUid())){
            userOnLine.put(user, p);
            userOnLineLogin.put(p.getUid(), user);
            refreshRemoteUsersList();
            userOnLineL.add(user);
            
            }else{
                b=false;
            }
            
        }
        
        return b;
    }
    
    /**
     * 
     * @param user 
     */
    public void exitLegede(Object user){
        userOnLineLogin.remove(userOnLine.get(user).getUid());
        userOnLine.remove(user);
        userOnLineL.remove(user);
        refreshRemoteUsersList();
        
    }
    
    /**
     * 
     * @param user
     * @return 
     */
    public boolean isAuthentificated(Object user){

        return userOnLine.containsKey(user);
    }

    /**
     * 
     * @param user
     * @return
     * @throws RemoteException 
     */
    public List<Person> getUsers(Object user) throws RemoteException {
        if(isAuthentificated(user)){
            System.out.println("dans le get user");
            List<Person> tmp=new ArrayList<Person>();
               tmp.addAll(dao.findAll());
        return tmp;
        }else{
            return null;
        }
    }

    /**
     * 
     * @return
     * @throws RemoteException 
     */
    public HashMap<Object, Person> getUsersOnLine() throws RemoteException {
         return userOnLine;
    }

    /**
     * 
     * @return
     * @throws RemoteException 
     */
    public HashMap<String, Object> getRuserOnLine() throws RemoteException{
        return userOnLineLogin;
    }
    

    /**
     * 
     * @param message
     * @param receiver
     * @param sender
     * @throws RemoteException 
     */
    public synchronized void sendMessage(String message, Object receiver, Object sender) throws RemoteException {
       if(userOnLine.containsKey(receiver)){
           InterClient contact=(InterClient) receiver;
           try{
               contact.receiveMessage(message, sender);
               System.out.println(java.net.InetAddress.getLocalHost());
           }catch(Exception ex){
               ex.printStackTrace();
           }
       }else{
           
           
       }
    }

    /**
     * 
     * @param login
     * @return
     * @throws RemoteException 
     */
    public HashMap<Object, String> getOutlineMessages(String login) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @param message
     * @param user
     * @param sender
     * @throws RemoteException 
     */
    public void sendMessage(String message, Object[] user, Object sender) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * 
     */
    private void refreshRemoteUsersList(){
        for(Object clients:userOnLineL){
            InterClient client=(InterClient) clients;
            try {
                client.refreshClient();
            } catch (RemoteException ex) {
                Logger.getLogger(ServeurLegede.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    //    @Override
////methode accessible a distances
//    public boolean getConnexion(String login, String password,Object user) throws RemoteException {
//        Persons=Person.getUsersFromLdap();
//        Person ldapu=Person.getPerson(login, Persons);
//        
//        if(password.equals(ldapu.getPassword())){
//            userOnLine.put(user,ldapu);
//            return true;
//        }else{
//            return false;
//        }
//        
//    }
//
//
//    //methode accessible a distances
//    public List<Person> getUsers() throws RemoteException {
//        Persons=Person.getUsersFromLdap();
//        return Persons;
//    }
//
//    //methode accessible a distances
//    public HashMap<Object,Person> getUsersOnLine() throws RemoteException {
//        return userOnLine;
//    }
//
//    //methode accessible a distances
//    public synchronized void sendMessage(String message, Object user,Object sender) throws RemoteException {
//        if(userOnLine.containsKey(user)){
//         // Client contact=userOnLine.ge  
//            InterClient contact= (InterClient) user;
//            try{
//            contact.receiveMessage(message,sender);
//            }catch(Exception ex){
//                
//            }
//        }else{
//             
//            
//        }
//        
//    }
//
//    //methode accessible a distances
//    public synchronized void sendMessage(String message, Object[] user,Object sender) throws RemoteException {
//        
//        eUserOnLine=(Enumeration) userOnLine.values();
//        while(eUserOnLine.hasMoreElements()){
//            InterClient contact= (InterClient) eUserOnLine.nextElement();
//            try{
//            contact.receiveMessage(message,sender);
//            }catch(Exception ex){}
//        }
//        
//    }
//
////    public void registerUser(String nom, Object user) {
////        simpleUser.put(nom, user);
////    }
////
////    public void removeUser(String nom) {
////        simpleUser.remove(nom);
////    }
//
//    //methode accessible a distances
//    public HashMap<Object, String> getOutlineMessages(String login) {
//         HashMap<Object, String> messages=new HashMap<Object, String>();
//         EntityManager em=Persistence.createEntityManagerFactory("tg.ucaouut.projet1.legede_legede-service_jar_1.0-SNAPSHOTPU").createEntityManager();
//         Query mq;
//         List<Message>msg=new ArrayList<Message>();
//         mq=em.createQuery("select * from message where users ="+login+"");
//         msg=mq.getResultList();
//         
//         for(int i=0;i<msg.size();i++){
//             Message a=msg.get(i);
//             messages.put(a.getUsername(),a.getMessage());
//         }
//         return messages;
//    }
    
}
