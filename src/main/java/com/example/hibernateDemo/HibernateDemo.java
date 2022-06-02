package com.example.hibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateDemo {
    public static void main(String[] args) {

        EmployeeEntity emp = new EmployeeEntity();
        emp.setEmail("demo-user@mail.com");
        emp.setFirstName("demo");
        emp.setLastName("user");

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metaData = new MetadataSources(standardRegistry)
                                .getMetadataBuilder()
                                .build();

        SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(emp);
        session.getTransaction().commit();
        sessionFactory.close();


    }
}
