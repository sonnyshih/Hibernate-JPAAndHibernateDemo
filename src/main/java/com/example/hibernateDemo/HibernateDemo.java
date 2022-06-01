package com.example.hibernateDemo;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateDemo {
    public static void main(String[] args) {

        Alien alien = new Alien();
        alien.setAid(101);
        alien.setAname("Hello");
        alien.setColor("Green");

        try {
            Configuration con = new Configuration().configure().addAnnotatedClass(Alien.class);
            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();

            Transaction tx = session.beginTransaction();
            session.save(alien);


        } catch (HibernateException e) {

        }
    }
}
