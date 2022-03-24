/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Link;
import java.util.List;

/**
 *
 * @author TheDuc
 */
public interface LinkDao extends BaseDao<Link> {

    boolean TestLink(String url);

    public List<Link> getAllLinkById(int idWebsite);
    
    public int DeleteHistoryById(int idWebsite);
    
    public List<Link> getLinkByIdHistory(int idHistory,int idWebsite);
}
