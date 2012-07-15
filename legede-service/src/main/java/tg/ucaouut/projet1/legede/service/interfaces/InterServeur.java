/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.ucaouut.projet1.legede.service.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import tg.ucaouut.projet1.legede.service.entities.Person;

/**
 *
 * @author centonni
 */
public interface InterServeur extends Remote {
    
    /**
     * Cette methode verifie qu'un utilisateur fait bien partie des utilisateurs autorisés
     * @param login le nom d'authentification
     * @param password le mot de passe
     * @param user Objet remote de l'interface InterClient 
     * @return true si le login et le mot de passe sont corrects
     * @throws RemoteException 
     */
    public boolean getConnexion(String login,String password,Object user)throws RemoteException;
  
    /**
     * Cette methode permet a un utilisateur de se deconnecter
     * @see InterClient
     * @param user objet remote de l'interface InterClient
     * @throws RemoteException 
     */
    public void exitLegede(Object user) throws RemoteException;
    
    /**
     * @see InterClient
     * @param user objet remote de l'interface InterClient
     * @return la liste de tous les utilisateurs du service
     * @throws RemoteException 
     */
    public List<Person> getUsers(Object user) throws RemoteException;
    
    /**
     * 
     * @return la liste des utilisateurs en ligne
     * @throws RemoteException 
     */
    public HashMap<Object,Person> getUsersOnLine() throws RemoteException;
    
    /**
     * 
     * @return la liste des utilisateur en ligne avec pour clef leur nom d'utilisateur
     * @throws RemoteException 
     */
    public HashMap<String, Object> getRuserOnLine() throws RemoteException;
    
    /**
     * 
     * @param message le message a envoyer
     * @param user  l'utilisateur qui envoie le message
     * @param senders celui a qui le message est envoye
     * @throws RemoteException 
     */
    public void sendMessage(String message,Object user,Object senders) throws RemoteException;
    
    /**
     * 
     * @param login
     * @return la liste des message hors-ligne
     * @throws RemoteException 
     */
    public HashMap<Object,String> getOutlineMessages(String login)throws RemoteException;
    
    /**
     * Permet d'envoyer un message a un groupe d'utilisateurs
     * @param message
     * @param user
     * @param sender
     * @throws RemoteException 
     */
    public void sendMessage(String message, Object[] user,Object sender) throws RemoteException;
    
    public String test() throws RemoteException;
    
//    public void registerUser(String nom,Object user);
//    
//    public void removeUser(String nom);
}
