/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.ucaouut.projet1.legede.client.client;

import javax.swing.AbstractListModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

/**
 *
 * @author centonni
 */
public class TreeModel extends AbstractListModel {

    private Object [] data=new Object[10];
    
    public TreeModel(){
        initData();
    }
    
    private void initData(){
        for(int i=0;i<10;i++){
            data[i]="moi"+" "+i;
        }
    }
    
    public int getSize() {
        return data.length;
    }

    public Object getElementAt(int arg0) {
        return data[arg0];
    }
    
}
