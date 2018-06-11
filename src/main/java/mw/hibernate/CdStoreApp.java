package mw.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CDSTORE **
 */
public class CdStoreApp {
    public static void main(String[] args) {
//        //Disable SQL logging
//        Logger.getLogger("org.hibernate.type").setLevel(Level.OFF);    - coś nie chce działać


        System.out.println("Łączenie do bazy CDSTORE");

        StandardServiceRegistry registry = new
                StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        SessionFactory sessionFactory = new
                MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Cds cds = session.get(Cds.class, 1L);
        Cds cds1 = session.get(Cds.class, 2L);

        System.out.println(cds.title);
        System.out.println(cds.hasHardCover);
        System.out.println(cds.releaseDate);
        System.out.println(cds1.title);
        System.out.println(cds1.releaseDate);
        session.close();

        sessionFactory.close();
    }
}
