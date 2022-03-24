/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author TheDuc
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status")
    private boolean status;

    @Column(name = "created_date")
    private int createAt;
    
    @Column(name = "updated_date")
    private int updateAt;

    public BaseEntity() {
    }

    public BaseEntity(boolean status) {
        this.status = status;
    }

    public BaseEntity(int createAt, int updateAt) {
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public BaseEntity(int createAt, int updateAt, boolean status) {
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCreateAt() {
        return createAt;
    }

    public void setCreateAt(int createAt) {
        this.createAt = createAt;
    }

    public int getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(int updateAt) {
        this.updateAt = updateAt;
    }
}
