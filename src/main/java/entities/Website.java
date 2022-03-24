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
@Table(name = "website")
public class Website extends BaseEntity {

    @Column(name = "url")
    private String url;
    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private int duration;

    public Website(int createAt, int updateAt, boolean status) {
        super(createAt, updateAt, status);
    }

    public Website(String url, String title, int duration, int createAt, int updateAt, boolean status) {
        super(createAt, updateAt, status);
        this.url = url;
        this.title = title;
        this.duration = duration;
    }

    public Website(String url, String title, int createAt, int updateAt, boolean status) {
        super(createAt, updateAt, status);
        this.url = url;
        this.title = title;
    }

    public Website() {
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
