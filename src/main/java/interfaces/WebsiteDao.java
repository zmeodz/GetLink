/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Website;

/**
 *
 * @author TheDuc
 */
public interface WebsiteDao extends BaseDao <Website>{
    
        public Website update(int id, String url, String title, int duration, int createAt, int updateAt, boolean status);
}
