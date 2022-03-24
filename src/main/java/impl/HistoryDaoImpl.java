/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import entities.History;
import entities.Website;
import interfaces.HistoryDao;
import java.util.List;
import org.hibernate.FlushMode;
import org.hibernate.Query;

/**
 *
 * @author TheDuc
 * @param <T>
 */
public class HistoryDaoImpl extends BaseDaoImpl<History> implements HistoryDao {

    public HistoryDaoImpl() {
        super(History.class);
    }

    @Override
    public List<History> deleteByIdWebsite(int id) {

        session = sessionFactory.openSession();
        session.beginTransaction();
//        session.getTransaction().commit();
//    String hql = "delete history where id_website = :idWebsite";
//    Query q = session.createQuery(hql).setParameter("idWebsite",id);
//    q.executeUpdate();
//    return null;
        List<History> posts = session.createQuery(
                "select p from History p Where id_website= :id_website").setParameter("name", "%" + id + "%").list();
        for (History post : posts) {
            session.delete(post);
        }
        return null;
    }

    public Website update(int id, String url, String title, int duration, int createAt, int updateAt, boolean status) {
        session = sessionFactory.openSession();
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

    @Override
    public void deleteHistory(History history) {
        session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(history);
            session.getTransaction().commit();
            System.out.println("detete success!");
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("false!");

        } finally {
            session.setHibernateFlushMode( FlushMode.MANUAL );
            session.close();
        }
    }

    @Override
    public History load_history(int idHistory) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        History sinhVien = (History) session.get(History.class, idHistory);
        session.getTransaction().commit();
        return sinhVien;
    }

    @Override
    public List<History> loadHistory() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from history";
        Query query = session.createQuery(hql);
        List<History> list_history = query.list();
        session.getTransaction().commit();
        return list_history;

    }

    @Override
    public int DeleteHistoryById(int idWebsite) {
        int results = -1;
        session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String hql = "DELETE FROM " + History.class.getName() + " WHERE idWebsite = :id";
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
    public List<History> getWebsiteById(int idWebsite) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "FROM " + History.class.getName() + " WHERE idWebsite = :idWebsite ";
            Query query = session.createQuery(hql);
            query.setInteger("idWebsite", idWebsite);
            List<History> results = query.list();
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
