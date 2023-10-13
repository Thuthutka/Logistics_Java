package com.hibernate;

import com.DeliveryService.Checkpoint;
import com.DeliveryService.Delivery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class CheckpointHib {

    EntityManager entityManager = null;
    EntityManagerFactory emf = null;

    public CheckpointHib(EntityManagerFactory entityManagerFactory) {
        this.emf = entityManagerFactory;
    }
    //CREATE
    public void createCheckpoint(Checkpoint checkpoint){
        entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(checkpoint);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //READ
    public Checkpoint getCheckpointById(int id){
        entityManager = emf.createEntityManager();
        Checkpoint checkpoint = new Checkpoint();
        try{
            entityManager.getTransaction().begin();
            checkpoint = entityManager.find(Checkpoint.class, id);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println("No checkpoint found by ID");
        }
        return checkpoint;
    }

    //DELETE
    public void deleteCheckpoint(Checkpoint comment){
        entityManager = emf.createEntityManager();
        
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Checkpoint.class, comment.getId()));
            entityManager.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(entityManager != null) entityManager.close();
        }
    }

    //UPDATE
    public void updateCheckpoint (Checkpoint checkpoint){
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(checkpoint);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(entityManager != null) entityManager.close();
        }
    }

    public List<Checkpoint> getAllCheckpoints() {
        entityManager = emf.createEntityManager();
        try {
            CriteriaQuery<Object> query = entityManager.getCriteriaBuilder().createQuery();
            query.select(query.from(Checkpoint.class));
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
}
