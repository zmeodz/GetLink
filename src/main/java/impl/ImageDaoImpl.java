/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import entities.Image;
import interfaces.ImageDao;
import java.util.List;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author TheDuc
 */
public class ImageDaoImpl extends BaseDaoImpl<Image> implements ImageDao {

    public ImageDaoImpl() {
        super(Image.class);
    }
    Session session;
   @Override
    public boolean TestLinkImage(String url) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "SELECT id FROM " + Image.class.getName() + " WHERE url = :url";
            Query query = session.createQuery(hql);
            query.setString("url", url);
            List<Image> results = query.list();
            session.getTransaction().commit();
            if (results == null || results.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Khong lay duoc du lieu");
        }
        finally {
            session.setHibernateFlushMode( FlushMode.MANUAL );
            session.close();
        }
        return false;
    }

    @Override
    public List<Image> getAllImageById(int idWebsite) {
        session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String hql = "FROM " + Image.class.getName() + " WHERE idWebsite = :url ";
            Query query = session.createQuery(hql);
            query.setInteger("url", idWebsite);
            List<Image> results = query.list();
            session.getTransaction().commit();
            return results;
        } catch (Exception e) {
            System.out.println("Khong lay duoc du lieu");
        }
        finally {
            session.setHibernateFlushMode( FlushMode.MANUAL );
            session.close();
        }
        return null;
    }
        @Override
    public int DeleteHistoryById(int idWebsite) {
        int results = -1;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "DELETE FROM " + Image.class.getName() + " WHERE idWebsite = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", idWebsite);
            results = query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Khong lay duoc du lieu");
        }
        finally {
            session.setHibernateFlushMode( FlushMode.MANUAL );
            session.close();
        }
        return results;
    }

    @Override
    public List<Image> getImageByIdHistory(int idHistory,int idWebsite) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "FROM " + Image.class.getName() + " WHERE idHistory = :idHistory AND idWebsite = :idWebsite ";
            Query query = session.createQuery(hql);
            query.setInteger("idHistory", idHistory);
            query.setInteger("idWebsite", idWebsite);
            List<Image> results = query.list();
            session.getTransaction().commit();
            return results;
        } catch (Exception e) {
            System.out.println("Khong lay duoc du lieu");
        } finally {
            session.setHibernateFlushMode( FlushMode.MANUAL );
            session.close();
        }
        return null;
    }
}
