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
@Table(name = "history")
public class History extends BaseEntity {

    @Column(name = "id_website")
    private int idWebsite;

    public History() {
    }

    public History(int createAt, int updateAt) {
        super(createAt, updateAt);
    }

    public History(int idWebsite, int createAt, int updateAt, boolean status) {
        super(createAt, updateAt, status);
        this.idWebsite = idWebsite;
    }

    public int getIdWebsite() {
        return idWebsite;
    }

    public void setIdWebsite(int idWebsite) {
        this.idWebsite = idWebsite;
    }
}
