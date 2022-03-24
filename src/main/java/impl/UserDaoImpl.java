/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import interfaces.UserDao;
import entities.User;
import java.util.List;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author TheDuc
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public List<User> findByUsername(String username) {
        Session session = sessionFactory.openSession();
        List<User> list = session.createQuery("FROM User WHERE username LIKE :name")
                .setParameter("name", "%" + username + "%").list();
        for (User customer : list) {
            System.out.println(customer.getPassword());
        }
        return null;
    }

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE username = :user_name AND password=:password");
            query.setString("user_name", username);
            query.setString("password", password);
            query.setMaxResults(1);
            User user = (User) query.uniqueResult();
            if (user != null) {
                if (user.getStatus() == true) {
                    System.out.println("Dang nhap thanh cong");
                    return user;
                } else {
                    System.out.println("Tai khoan bi vo hieu hoa!");
                    return null;
                }
            } else {
                System.out.println("False Success");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public User update(String username, String password) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String hql = "update User set password=:password where username = :name";
            Query query = session.createQuery(hql);
            query.setString("name", username);
            query.setString("password", password);
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
    public User update1(int id, String password) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String hql = "update User set password=:password where id = :id";
            Query query = session.createQuery(hql);
            query.setString("password", password);
            query.setInteger("id", id);
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
    public boolean testUserAdd(String username) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "SELECT id FROM " + User.class.getName() + " WHERE username = :username ";
            Query query = session.createQuery(hql);
            query.setString("username", username);
            List<User> results = query.list();
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
    public void DeleteUserById(int idProfile) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "DELETE FROM " + User.class.getName() + " WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", idProfile);
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("delete success");
        } catch (Exception e) {
            System.out.println("Khong lay duoc du lieu");
        } finally {
            session.setHibernateFlushMode( FlushMode.MANUAL );
            session.close();
        }
    }
}
