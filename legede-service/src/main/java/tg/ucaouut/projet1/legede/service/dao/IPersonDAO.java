/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.ucaouut.projet1.legede.service.dao;

import java.util.List;
import tg.ucaouut.projet1.legede.service.entities.Person;

/**
 *
 * @author centonni
 */
public interface IPersonDAO {

    public List getPersonNamesByLastName(String lastName);

    public void create(Person person);

    public Person findByPrimaryKey(String uid);

    public void update(Person person);

    public void delete(Person person);

    public List findAll();
}
