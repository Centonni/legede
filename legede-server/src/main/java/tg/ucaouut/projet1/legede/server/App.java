package tg.ucaouut.projet1.legede.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws RemoteException {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
        System.out.println("Hello World! salut didier\n");
       // EntityManager em = Persistence.createEntityManagerFactory("legedePU").createEntityManager();
        //ServeurLegede sl=new ServeurLegede();

        try {
            //demarre le serveur sur le port 1099
            LocateRegistry.createRegistry(1099);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        ServeurLegede sl = null;
        try {
            sl = new ServeurLegede();
        } catch (RemoteException ex) {
            ex.detail.printStackTrace();
        }
        try {
            Naming.rebind("InterServeur", sl);
        } catch (MalformedURLException ex) {
        } catch (RemoteException ex) {
        }
    }
}
