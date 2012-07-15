/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.ucaouut.projet1.legede.client.client;

import java.util.HashMap;
import java.util.List;
import javax.swing.AbstractListModel;
import tg.ucaouut.projet1.legede.service.entities.Person;

/**
 *
 * @author centonni
 */
public class ListModel extends AbstractListModel {

    private Object[] data;
    List<Person> person;
    private String uid[];
    
    
    public ListModel(HashMap<Object,Person> p){
        person=(List<Person>) p.values();
    }
    
    private void initList(){
        data=new Object[person.size()];
        uid=new String[person.size()];
        for(int i=0;i<data.length;i++){  
            data[i]=person.get(i).getFirstName()+" "+person.get(i).getLastName();
            uid[i]=person.get(i).getUid();
        }
    }
    
    public int getSize() {
        return  data.length;
    }

    public Object getElementAt(int arg0) {
        return data[arg0];
    }

    public String[] getUid() {
        return uid;
    }
    
    public String getUidAt(int index){
        return uid[index];
    }
    
}
