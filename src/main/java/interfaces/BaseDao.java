/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.BaseEntity;
import java.util.List;

/**
 *
 * @author TheDuc
 */
public interface BaseDao<T extends BaseEntity> {

    public List<T> getAll();

    public T loadById(int id);
    
    public T getById(int id);
    
    public T insert(T t);

    public T update(T t);

    public boolean delete(int id);
            
    public T load(long id);
    
    

}
