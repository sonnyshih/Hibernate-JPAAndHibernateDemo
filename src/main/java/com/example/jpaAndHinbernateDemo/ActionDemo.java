package com.example.jpaAndHinbernateDemo;

import com.example.jpaAndHinbernateDemo.entity.EmployeeEntity;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import javax.persistence.*;
import java.sql.*;
import java.util.List;

public class ActionDemo {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Use entityManager
        add(entityManager);
//        update(entityManager);
//        delete(entityManager);
//        selectSingle(entityManager);
//        useSelectSQL(entityManager);
//        findAll(entityManager);
//        findByCondition(entityManager);

        // Use JDBC
//        testConnectionInsert(entityManager);
//        testConnectionUpdate(entityManager);
//        testConnectionDelete(entityManager);
//        testConnectionSelect(entityManager);

        entityManager.close();
        entityManagerFactory.close();
    }

    // Insert
    private static void add(EntityManager entityManager){
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        EmployeeEntity entity = new EmployeeEntity();
        entity.setFirstName("Dalia");
        entity.setLastName("Abo Sheasha");
        entityManager.persist(entity);
        transaction.commit();
    }

    // update
    private static void update(EntityManager entityManager){
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(1);
        entity.setFirstName("Dalia123");
        entity.setLastName("Abo Sheasha 456");
        entityManager.merge(entity);
        transaction.commit();
    }

    // delete
    private static void delete(EntityManager entityManager){
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        EmployeeEntity entity = entityManager.find(EmployeeEntity.class, Long.valueOf(5));    // ID = 5
        entityManager.remove(entity);
        transaction.commit();

    }

    // select signal data
    private static void selectSingle(EntityManager entityManager){
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        EmployeeEntity entity = entityManager.find(EmployeeEntity.class, Long.valueOf(1));    // ID = 1

        transaction.commit();
        System.out.println("First Name="+ entity.getFirstName());
    }

    // 找出所有
    private static void findAll(EntityManager entityManager){
        Query query = entityManager.createQuery("from AnimalEntity"); // EmployeeEntity 是 java 物件
        List<EmployeeEntity> list = query.getResultList();
        for (EmployeeEntity entity : list) {
            System.out.println(entity.getFirstName());
        }
    }

    private static void findByCondition(EntityManager entityManager){
        Query query = entityManager.createQuery("from AnimalEntity where firstName like ?0"); // EmployeeEntity 是 java 物件
        List<EmployeeEntity> list = query.setParameter(0, "%o%").getResultList();
        for (EmployeeEntity entity : list) {
            System.out.println("FirstName111="+entity.getFirstName());
        }
    }

    // Use the Native Query
    private static void useSelectSQL(EntityManager entityManager){
        Query query = entityManager.createNativeQuery("SELECT FirstName, LastName FROM Employee");
        List<Object[]> list = query.getResultList();
        for (Object[] entity : list) {
            System.out.println(entity[0]);
        }
    }

    // Use the connection to do insert
    private static void testConnectionInsert(EntityManager entityManager){

        try {
            String sqlString = "Insert into Employee(FirstName, LastName) values('Hello', 'World')";
            Connection connection = entityManager.unwrap(SessionImpl.class).connection();
            PreparedStatement ps = connection.prepareStatement(sqlString,Statement.RETURN_GENERATED_KEYS);
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt("ID");
            }

            System.out.println("Auto Generated Primary Key " + generatedKey);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Use the connection to update
    private static void testConnectionUpdate(EntityManager entityManager){

        try {
            String sqlString = "Update Employee set FirstName='goood33333' where ID='1'";
            Connection connection = entityManager.unwrap(SessionImpl.class).connection();
            Statement statement = connection.createStatement();
            boolean isSuccess = statement.execute(sqlString);   // true: 表示是select, false: 表示只是沒有回傳值
            System.out.println("Update success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Use the connection to update
    private static void testConnectionDelete(EntityManager entityManager){

        try {
            String sqlString = "Delete from Employee where ID='6'";
            Connection connection = entityManager.unwrap(SessionImpl.class).connection();
            Statement statement = connection.createStatement();
            boolean isSuccess = statement.execute(sqlString);   // true: 表示是select, false: 表示只是沒有回傳值
            System.out.println("Delete success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Use the connection to do select
    private static void testConnectionSelect(EntityManager entityManager){

        try {
            Connection connection = entityManager.unwrap(SessionImpl.class).connection();
            Statement statement = connection.createStatement();
            String selectSql = "select * from Employee";
            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("aaaa="+resultSet.getString("FirstName") + "--" + resultSet.getString("LastName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //用 session do work (比較少用)
    private static void testConnection1(EntityManager entityManager){

        Session hibernateSession = entityManager.unwrap(Session.class);

        hibernateSession.doWork(new org.hibernate.jdbc.Work() {

            @Override
            public void execute(Connection connection) throws SQLException {
                // do whatever you need to do with the connection
                Statement statement = connection.createStatement();
                String selectSql = "select * from Employee";
                ResultSet resultSet = statement.executeQuery(selectSql);
                while (resultSet.next()) {
                    System.out.println("aaaa="+resultSet.getString("FirstName") + "--" + resultSet.getString("LastName"));
                }
            }//End execute
        });

    }

}
