package com.hibernate;

import com.users.Driver;
import com.users.Manager;
import com.users.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserHib {

    EntityManager entityManager = null;
    EntityManagerFactory emf = null;

    public UserHib(EntityManagerFactory entityManagerFactory) {
        this.emf = entityManagerFactory;
    }

    //CREATE
    public void createUser(User user){
        entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //READ by ID
    public Driver getDriverById(int id) {
        entityManager = emf.createEntityManager();
        Driver driverUser = null;
        try {
            entityManager.getTransaction().begin();
            driverUser = entityManager.find(Driver.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such user by given Id");
        }
        return driverUser;
    }

    public Manager getManagerById(int id) {
        entityManager = emf.createEntityManager();
        Manager managerUser = null;
        try {
            entityManager.getTransaction().begin();
            managerUser = entityManager.find(Manager.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such user by given Id");
        }
        return managerUser;
    }

    //READ to login
    public User getUserByLoginData(String login, String psw, boolean isManager) {
        entityManager = emf.createEntityManager();
        Query q = null;
        CriteriaQuery<Driver> queryDriver = null;
        CriteriaQuery<Manager> queryManager = null;
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        if (isManager == false) {
            queryDriver = cb.createQuery(Driver.class);
            Root<Driver> root = queryDriver.from(Driver.class);
            queryDriver.select(root).where(cb.and(cb.like(root.get("username"), login), cb.like(root.get("password"), psw)));
        } else {
            queryManager = cb.createQuery(Manager.class);
            Root<Manager> root = queryManager.from(Manager.class);
            queryManager.select(root).where(cb.and(cb.like(root.get("username"), login), cb.like(root.get("password"), psw)));
        }

        try {
            if (queryDriver != null) q = entityManager.createQuery(queryDriver);
            if (queryManager != null) q = entityManager.createQuery(queryManager);
            return (User) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Driver getDriverByUsername(String username){
        entityManager = emf.createEntityManager();
        Query q = null;
        CriteriaQuery<Driver> queryDriver = null;
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        queryDriver = cb.createQuery(Driver.class);
        Root<Driver> root = queryDriver.from(Driver.class);
        queryDriver.select(root).where(cb.and(cb.like(root.get("username"), username)));

        try {
            if (queryDriver != null) q = entityManager.createQuery(queryDriver);
            return (Driver) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
    public List<Driver> getAllDrivers() {
        entityManager = emf.createEntityManager();
        try {
            CriteriaQuery<Object> query = entityManager.getCriteriaBuilder().createQuery();
            query.select(query.from(Driver.class));
            Query q = entityManager.createQuery(query);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return new ArrayList<>();
    }

    public List<Manager> getAllManagers() {
        entityManager = emf.createEntityManager();
        try {
            CriteriaQuery<Object> query = entityManager.getCriteriaBuilder().createQuery();
            query.select(query.from(Manager.class));
            Query q = entityManager.createQuery(query);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return new ArrayList<>();
    }
    public void updateUser(User user) {
        entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) entityManager.close();
        }
    }

    //DELETE
    public void deleteDriver (Driver driver){
        entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Driver.class, driver.getId()));
            entityManager.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(entityManager != null) entityManager.close();
        }
    }
    public void deleteManager (Manager manager){
        entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Manager.class, manager.getId()));
            entityManager.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(entityManager != null) entityManager.close();
        }
    }


}
