package com.example.jpaAndHinbernateDemo;

import com.example.hibernateDemo.AnimalEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPADemo {
    public static void main(String[] args) {
        // "Default" is in the META-INF/persistence.xml
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            AnimalEntity entity = new AnimalEntity();
//            entity.setId(7);
            entity.setName("Fish");
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
