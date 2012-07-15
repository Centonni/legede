/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.ucaouut.projet1.legede.service.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author centonni
 */
public interface InterClient extends Remote{
    
    /**
     * 
     * @param message
     * @param sender
     * @throws RemoteException 
     */
    public void receiveMessage(String message,Object sender) throws RemoteException;
    
    /*
     * permet de reactualiser la liste des clients
     */
    public void refreshClient() throws RemoteException;
    //public void receiveMessage(String message,Object[] user);
    
}
