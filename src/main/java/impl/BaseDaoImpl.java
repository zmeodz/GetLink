/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import entities.BaseEntity;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import interfaces.BaseDao;
import java.util.List;
import org.hibernate.FlushMode;
import org.hibernate.Transaction;

/**
 *
 * @author TheDuc
 */
public abstract class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Class clazz;
    Session session;

    public BaseDaoImpl(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<T> getAll() {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            List<T> list = session.createQuery("FROM " + clazz.getName()).list();
            session.getTransaction().commit();

            return list;
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
    public T getById(int id) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            T t = (T) session.get(clazz, id);
            session.getTransaction().commit();
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
    public T loadById(int id) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            T t = (T) session.load(clazz, id);
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
    public T insert(T t) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
            System.out.println("insert success!");
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.setHibernateFlushMode( FlushMode.MANUAL );
            session.close();
        }
        return null;
    }

//    @Override
//    public T delete(T t) {
////    public void delete(int id) {
//        Session session = sessionFactory.openSession();
//        try {
//            session.beginTransaction();
//                    Query query = session.createQuery("delete from Foo where id = :id")
//                .setParameter("id", t.getId());
//            List list = query.list();
//            System.out.println("list size " + list.size());
//            if (list.size() == 1) {
//                System.out.println("Login Success");
//            } else {
//                System.out.println("False Success");
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return null;
//    }
    @Override
    public boolean delete(int id) {
//    public void delete(int id) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            T t = (T) session.load(clazz, id);
            session.delete(t);
            session.getTransaction().commit();
            System.out.println("detete success!");
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("false!");
            return false;
        } finally {
            session.setHibernateFlushMode( FlushMode.MANUAL );
            session.close();
        }
        return true;
    }

    @Override
    public T update(T t) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(t);
            session.getTransaction().commit();
            System.out.println("update success!");
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            System.out.println("false update!");
        } finally {
            session.setHibernateFlushMode( FlushMode.MANUAL );
            session.close();
        }
        return null;
    }

    @Override
    public T load(long id) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            T t = (T) session.get(clazz, id);
            session.getTransaction().commit();
            return t;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            System.out.println("false update!");
        } finally {
            session.setHibernateFlushMode( FlushMode.MANUAL );
            session.close();
        }
        return null;
    }
}
