
package helloworldexample;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * otworzenie bazy messagerepository, odczyt istniejących i zapis wprowadzonego
 * tekstu do tablicy message
 */
public class HelloWorldExample {
    private static ServiceRegistry registry;
    private static SessionFactory sessionFactory;

      public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        String m = "";
        System.out.println("Enter a message: ");
        m = in.nextLine();
        try {
            registry = new StandardServiceRegistryBuilder().configure().build();   //tu configure wciąga hibernate.cfg.xml
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Nie powiodło się utworzenie session factory obiektu " + ex);
            throw new ExceptionInInitializerError(ex);
        }

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