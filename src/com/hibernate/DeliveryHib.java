package com.hibernate;

import com.DeliveryService.Comment;
import com.DeliveryService.Delivery;
import com.DeliveryService.Vehicle;
import com.users.Driver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DeliveryHib {

    EntityManager entityManager = null;
    EntityManagerFactory emf = null;

    public DeliveryHib(EntityManagerFactory entityManagerFactory) {
        this.emf = entityManagerFactory;
    }

    //CREATE
    public void createDelivery(Delivery delivery){
        entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(delivery);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Delivery getDeliveryById(int id) {
        entityManager = emf.createEntityManager();
        Delivery delivery = null;
        try {
            entityManager.getTransaction().begin();
            delivery = entityManager.find(Delivery.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such Delivery by given Id");
        }
        return delivery;
    }

    //READ
    public List<Delivery> getAllDeliveries() {
        entityManager = emf.createEntityManager();
        try {
            CriteriaQuery<Object> query = entityManager.getCriteriaBuilder().createQuery();
            query.select(query.from(Delivery.class));
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

    //UPDATE
    public void updateDelivery(Delivery delivery) {
        entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(delivery);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) entityManager.close();
        }

    }

    //DELETE
    public void deleteDelivery(Delivery delivery){
        entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Delivery.class, delivery.getId()));
            entityManager.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(entityManager != null) entityManager.close();
        }

    }
    public List<Delivery> getSelected(LocalDate min, LocalDate max, String status) {
        entityManager = emf.createEntityManager();
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Delivery> query = criteriaBuilder.createQuery(Delivery.class);
            Root<Delivery> root = query.from(Delivery.class);
            query.select(root).where(criteriaBuilder.and(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"), min),
                    criteriaBuilder.lessThanOrEqualTo(root.get("deliveryDate"), max),criteriaBuilder.equal(root.get("deliveryStatus"), status))));
            query.select(root);
            Query q = entityManager.createQuery(query);
            return q.getResultList();
        }catch (Exception e){
            return null;
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }


    public List<Delivery> getSelectedByAll(LocalDate min, LocalDate max, Driver driver, Vehicle vehicle, String status) {
        entityManager = emf.createEntityManager();
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Delivery> query = criteriaBuilder.createQuery(Delivery.class);
            Root<Delivery> root = query.from(Delivery.class);
            query.select(root).where(criteriaBuilder.and(
                    criteriaBuilder.and
                            (criteriaBuilder.and(
                                            criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"), min), criteriaBuilder.equal(root.get("status"), status)),
                                    criteriaBuilder.lessThanOrEqualTo(root.get("deliveryDate"), max)), criteriaBuilder.equal(root.get("driver_id"), driver.getId())));
            Query q;
            q = entityManager.createQuery(query);
            return (List<Delivery>) q.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
        return null;

    }
}
