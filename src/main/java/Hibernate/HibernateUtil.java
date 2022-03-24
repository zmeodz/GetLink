package hibernate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entities.History;
import entities.Image;
import entities.Link;
import entities.Profile;
import entities.User;
import entities.Website;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration conf = new Configuration();
            conf.addAnnotatedClass(User.class);
            conf.addAnnotatedClass(Profile.class);
            conf.addAnnotatedClass(History.class);
            conf.addAnnotatedClass(Image.class);
            conf.addAnnotatedClass(Link.class);
            conf.addAnnotatedClass(Website.class);
            conf.configure("cfg/hibernate.cfg.xml");
            StandardServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
            //Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            return conf.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}
