/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.ucaouut.projet1.legede.client;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tg.ucaouut.projet1.legede.service.interfaces.InterClient;
import tg.ucaouut.projet1.legede.service.interfaces.InterServeur;
import tg.ucaouut.projet1.legede.ui.Chat;
import tg.ucaouut.projet1.legede.ui.MessageUI;

/**
 *
 * @author centonni
 */
public class Client extends UnicastRemoteObject implements InterClient,Serializable{

    private String message;
    private String serveur="10.42.43.1";
    private InterServeur sl;
    MessageUI msgUI;
   // private Chat chat;
    private List<Chat> chatList;
    
    public Client() throws RemoteException, NotBoundException, MalformedURLException{
        //super();
        initialiseServer();
        chatList=new ArrayList<Chat>();
    }
    
    private void initialiseServer() throws NotBoundException, MalformedURLException, RemoteException{
//        try{
            sl=(InterServeur)Naming.lookup("rmi://" +serveur+ "/InterServeur");
//        }catch(Exception ex){
//            System.out.println("erreur d'acces objet distant : ");
//            ex.printStackTrace();
//        }
    }
    
    //methode pour nitialiser l'objet distant
    public InterServeur getServeurObject(){
        
        return sl;
        
    }

   //methode qui permet de recevoir le message distant
    public synchronized  void  receiveMessage(String message,Object sender) {
       // Chat chat = this.getChat(sender);
        this.message=message;
        System.out.println(message+" "+sender.toString()+" "+this.toString());
        try{
        getChat(sender).setRecu(message);
        getChat(sender).setVisible(true);
        }catch(Exception ex){
          Chat chat=new Chat(this, (InterClient)sender);  
         chat.setRecu(message);
        chatList.add(chat);
        }
    }
    
    public boolean connect(String login,String password){
        boolean con= false;
        try {
           con=sl.getConnexion(login, password,this);
        } catch (RemoteException ex) {
            con=false;
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return con;
    }

//    public Chat getChat() {
//        return chat;
//    }

    public void setChat(Chat chats) {
        Chat c=this.getChat(chats.getReceiver());
        
        if(c==null){
        chatList.add(chats);
        }
    }
    
    private Chat getChat(Object sender){
        for(Chat j:chatList){
            if(j.getReceiver().equals(sender)){
                return j;
            }
        }
        return null;
    }

    public MessageUI getMsgUI() {
        return msgUI;
    }

    public void setMsgUI(MessageUI msgUI) {
        this.msgUI = msgUI;
    }

    public void refreshClient() {
        msgUI.refreshList();
    }
    
    
}