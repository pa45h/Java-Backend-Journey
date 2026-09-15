package org.pa45h;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory()) {

            Session session = sessionFactory.openSession();

            Student s1 = new Student();
            s1.setId(11);
            s1.setName("Pa45h");
            s1.setAge(21);
            s1.setDob(new Date(2005 - 1900, 12 - 1, 1));

            Transaction transaction = session.beginTransaction();

            session.persist(s1);
            Student s = session.get(Student.class, 11);
            System.out.println(s);
            System.out.println("done");

            transaction.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
