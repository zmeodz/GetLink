/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.History;
import java.util.List;

/**
 *
 * @author TheDuc
 */
public interface HistoryDao extends BaseDao<History> {

    void deleteHistory(History history);

    public List<History> loadHistory();

    public History load_history(int idHistory);

    public List<History> deleteByIdWebsite(int id);

    public int DeleteHistoryById(int idWebsite);
    
    public List<History> getWebsiteById(int idWebsite);
}
