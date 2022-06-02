package com.example.jpaAndHinbernateDemo;

import com.example.jpaAndHinbernateDemo.entity.EmployeeEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPADemo {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            EmployeeEntity entity = new EmployeeEntity();
//            entity.setId(7);
            entity.setFirstName("Dalia");
            entity.setLastName("Abo Sheasha");
            entityManager.persist(entity);

            transaction.commit();

        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }

            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
