/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author TheDuc
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(boolean status) {
        super(status);
    }

    public User(int createAt, int updateAt, boolean status) {
        super(createAt, updateAt, status);
    }

    public User(String username, String password, int createAt, int updateAt, boolean status) {
        super(createAt, updateAt, status);
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, boolean status) {
        super(status);
        this.username = username;
        this.password = password;
    }
    
        public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
