/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Profile;
import java.util.Date;


/**
 *
 * @author TheDuc
 */
public interface ProfileDao extends BaseDao<Profile>{

    public Profile loadIdUser (int id);
    public Profile update(int idUser, String firstname, String lastname, String phone, String address, Date birthday, String email, String gender, int createAt, int updateAt, boolean status);
}
