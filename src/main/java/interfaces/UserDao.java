/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.User;
import java.util.List;

/**
 *
 * @author TheDuc
 */
public interface UserDao extends BaseDao<User> {
    
    
    public List<User> findByUsername(String username);

    public User getByUsernameAndPassword(String username, String password);
    
    public User update(String username,String password);
    
    public User update1(int id,String password);
    
    public boolean testUserAdd(String username);
    
    public void DeleteUserById(int idProfile);
}
