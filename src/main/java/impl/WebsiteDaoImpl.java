/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import entities.Website;
import interfaces.WebsiteDao;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author TheDuc
 */
public class WebsiteDaoImpl extends BaseDaoImpl<Website> implements WebsiteDao {

    public WebsiteDaoImpl() {
        super(Website.class);
    }

    @Override
    public Website update(int id, String url, String title, int duration, int createAt, int updateAt, boolean status) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String hql = "update Website set url = :url, title = :title, duration = :duration, created_date = :created_date, updated_date = :updated_date, status= :status  where id= :id";
            Query query = session.createQuery(hql);
            query.setString("url", url);
            query.setString("title", title);
            query.setInteger("id", id);
            query.setInteger("duration", duration);
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
