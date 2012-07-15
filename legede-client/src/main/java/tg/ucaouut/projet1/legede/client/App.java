package tg.ucaouut.projet1.legede.client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import tg.ucaouut.projet1.legede.service.entities.Person;
import tg.ucaouut.projet1.legede.service.interfaces.InterClient;
import tg.ucaouut.projet1.legede.service.interfaces.InterServeur;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws RemoteException, NotBoundException, MalformedURLException
    {
        System.out.println( "Hello World!" );
        Client a =new Client();
        InterServeur is=a.getServeurObject();
         System.out.println(is.test());
boolean b =is.getConnexion("didier.kouame", "didier", a);
            //  boolean b =is.getConnexion("serge.innocent", "serge", a);
        int y=is.getUsersOnLine().size();
        while(is.getUsersOnLine().size()<2){
            
        }
        HashMap<Object,Person> person= is.getUsersOnLine();
        HashMap<String,Object> po=is.getRuserOnLine();
        Collection<Person> p=person.values();
        for(Person j:p){
            InterClient contact=(InterClient) po.get(j.getUid());
            try{
            contact.receiveMessage("c'est moi",a);
            }catch(Exception ex){ex.printStackTrace();}
        }
//        System.out.println(is.test()+" "+a.toString());
//        ArrayList<Person> p=new ArrayList<Person>();
       // List<Person> ap= is.getUsers(a);
//        for(int i=0;i<ap.size();i++){
//            System.out.println(" personne : "+ap.get(i).getLastName());
//        }
//        boolean r =p.addAll(ap);
//        System.out.println("avec la collection ");
//                
//        int e=p.size();
//        String z=p.get(1).getEmail();
//        for(Person g:p){
//            System.out.println(g.getEmail()+" : "+g.getLastName());
//        }
    }
}
