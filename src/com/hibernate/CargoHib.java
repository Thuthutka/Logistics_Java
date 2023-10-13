package com.hibernate;

import com.DeliveryService.Cargo;
import com.DeliveryService.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class CargoHib {
    EntityManager entityManager = null;
    EntityManagerFactory emf = null;

    public CargoHib(EntityManagerFactory entityManagerFactory) {
        this.emf = entityManagerFactory;
    }
    //CREATE
    public void createCargo(Cargo cargo){
        entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cargo);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //READ
    public Cargo getCargoById(int id){
        entityManager = emf.createEntityManager();
        Cargo cargo = new Cargo();
        try{
            entityManager.getTransaction().begin();
            cargo = entityManager.find(Cargo.class, id);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println("No cargo found by ID");
        }
        return cargo;
    }

    //DELETE
    public void deleteCargo(Cargo cargo){
        entityManager = emf.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Cargo.class, cargo.getId()));
            entityManager.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(entityManager != null) entityManager.close();
        }
    }

    //UPDATE
    public void updateCargo(Cargo cargo){
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(cargo);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(entityManager != null) entityManager.close();
        }
    }

    public List<Cargo> getAllCargo() {
        entityManager = emf.createEntityManager();
        try {
            CriteriaQuery<Object> query = entityManager.getCriteriaBuilder().createQuery();
            query.select(query.from(Cargo.class));
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
