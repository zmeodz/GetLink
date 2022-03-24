/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author TheDuc
 */
@Entity
@Table(name = "link")
public class Link extends BaseEntity {

    @Column(name = "url")
    private String url;

    @Column(name = "id_website")
    public int idWebsite;

    @Column(name = "id_history")
    public int idHistory;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdWebsite() {
        return idWebsite;
    }

    public void setIdWebsite(int idWebsite) {
        this.idWebsite = idWebsite;
    }

    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public Link(int createAt, int updateAt, boolean status) {
        super(createAt, updateAt, status);
    }

    public Link() {
    }

    public Link(String url, int idWebsite, int idHistory, int createAt, int updateAt, boolean status) {
        super(createAt, updateAt, status);
        this.url = url;
        this.idWebsite = idWebsite;
        this.idHistory = idHistory;
    }
}
