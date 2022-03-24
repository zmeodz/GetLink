/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import interfaces.ProfileDao;
import entities.Profile;
import java.util.Date;
import org.hibernate.FlushMode;
import org.hibernate.Query;

/**
 *
 * @author TheDuc
 */
public class ProfileDaoImpl extends BaseDaoImpl<Profile> implements ProfileDao {

    public ProfileDaoImpl() {
        super(Profile.class);
    }

    @Override
    public Profile loadIdUser(int id) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Profile t = (Profile) session.createQuery("FROM " + Profile.class.getName() + " WHERE id_user = :id_user").setParameter("id_user", id);
            session.getTransaction().commit();
            System.out.println("find by id success!");
            return t;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.setHibernateFlushMode( FlushMode.MANUAL );
            session.close();
        }
        return null;
    }

    @Override
    public Profile update(int idUser, String firstname, String lastname,
            String phone, String address, Date birthday, String email, String gender, int createAt, int updateAt, boolean status) {
        session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String hql = "update Website set firstname = :firstname , lastname = :lastname, phone = :phone , address = :address,birthday = :birthday, email = :email, gender = :gender, created_date = :created_date, updated_date = :updated_date, status= :status  where idUser= :idUser";
            Query query = session.createQuery(hql);
            query.setInteger("idUser", idUser);
            query.setString("phone", phone);
            query.setString("firstname", firstname);
            query.setString("lastname", lastname);
            query.setString("email", email);
            query.setDate("birthday", birthday);
            query.setString("address", address);
            query.setString("gender", gender);
            query.setInteger("created_date", createAt);
            query.setInteger("updated_date", updateAt);
            query.setBoolean("status", status);
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            session.getTransaction().commit();
            System.out.println("update success!");
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("false!");
        } finally {
            session.setHibernateFlushMode( FlushMode.MANUAL );
            session.close();
        }
        return null;
    }
}
