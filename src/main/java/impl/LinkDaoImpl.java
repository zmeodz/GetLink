/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import entities.Link;
import interfaces.LinkDao;
import java.util.List;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author TheDuc
 */
public class LinkDaoImpl extends BaseDaoImpl<Link> implements LinkDao {

    public LinkDaoImpl() {
        super(Link.class);
    }
    Session session;

    @Override
    public boolean TestLink(String url) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "SELECT id FROM " + Link.class.getName() + " WHERE url = :url";
            Query query = session.createQuery(hql);
            query.setString("url", url);
            List<Link> results = query.list();
            session.getTransaction().commit();
            if (results == null || results.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Khong lay duoc du lieu");
        } finally {
            session.setHibernateFlushMode( FlushMode.MANUAL );
            session.close();
        }
        return false;
    }

    @Override
    public List<Link> getAllLinkById(int idWebsite) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "FROM " + Link.class.getName() + " WHERE idWebsite = :url ";
            Query query = session.createQuery(hql);
            query.setInteger("url", idWebsite);
            List<Link> results = query.list();
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
            String hql = "DELETE FROM " + Link.class.getName() + " WHERE idWebsite = :id";
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
    public List<Link> getLinkByIdHistory(int idHistory,int idWebsite) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "FROM " + Link.class.getName() + " WHERE idHistory = :idHistory AND idWebsite = :idWebsite";
            Query query = session.createQuery(hql);
            query.setInteger("idHistory", idHistory);
            query.setInteger("idWebsite", idWebsite);
            List<Link> results = query.list();
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
