/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.ucaouut.projet1.legede.client;

import tg.ucaouut.projet1.legede.ui.Connexion;
import tg.ucaouut.projet1.legede.ui.MessageUI;

/**
 *
 * @author bytespoir
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        Service service = new Service();
        Connexion con = new Connexion(service);
        con.setVisible(true);
//        //con.wait();
//        boolean etat=con.isState();
//        while (!etat) {
//            etat=con.isState();
//            if(etat){
//                con.dispose();
//            }
//        }
    //        MessageUI message=new  MessageUI(service);
//        message.setVisible(true);
        
        

    }
}