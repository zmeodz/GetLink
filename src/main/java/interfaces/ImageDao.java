/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Image;
import java.util.List;

/**
 *
 * @author TheDuc
 */
public interface ImageDao extends BaseDao<Image> {

    boolean TestLinkImage(String url);

    public List<Image> getAllImageById(int idWebsite);
    
    public int DeleteHistoryById(int idWebsite);
    
    public List<Image> getImageByIdHistory(int idHistory,int idWebsite);
}
