/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.ucaouut.projet1.legede.client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tg.ucaouut.projet1.legede.service.interfaces.InterServeur;

/**
 *
 * @author bytespoir
 */
public class Service {

    Client client=null;
    boolean etatConnexion = false;

    public Service() {
    }

    public void startClient() throws RemoteException, NotBoundException, MalformedURLException {
        
            client = new Client();
        
    }

    public boolean connectServer(String login, String password) {

        return client.connect(login, password);
    }

    public boolean isEtatConnexion() {
        return etatConnexion;
    }

    public Client getClient() {
        return client;
    }

    public InterServeur getRemoteObject() {
        return client.getServeurObject();
    }
}
