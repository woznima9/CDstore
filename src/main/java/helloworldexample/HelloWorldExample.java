/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworldexample;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Producer
 */
public class HelloWorldExample {
    private static ServiceRegistry registry;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        String m = "";
        System.out.println("Enter a message: ");
        m = in.nextLine();

            StandardServiceRegistry registry = new
                    StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//            Configuration conf = new Configuration().configure();
//            registry = new StandardServiceRegistryBuilder().applySettings(
//                    conf.getProperties()).build();
//            factory = conf.buildSessionFactory(registry);

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Short msgId = null;
        try {
            tx = session.beginTransaction();
            Message msg = new Message(m);
            msgId = (Short) session.save(msg);
            List messages = session.createQuery("FROM Message").list();
            for (Iterator iterator = messages.iterator(); iterator.hasNext(); ) {
                Message message = (Message) iterator.next();
                System.out.println("message: " + message.getMessage());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        StandardServiceRegistryBuilder.destroy(registry);
    }

}