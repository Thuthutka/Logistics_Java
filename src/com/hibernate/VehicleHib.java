package com.hibernate;

import com.DeliveryService.Delivery;
import com.DeliveryService.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class VehicleHib {
    EntityManager entityManager = null;
    EntityManagerFactory emf = null;

    public VehicleHib(EntityManagerFactory entityManagerFactory) {
        this.emf = entityManagerFactory;
    }
    //CREATE
    public void createVehicle(Vehicle vehicle){
        entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(vehicle);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //READ
    public Vehicle getVehicleById(int id){
        entityManager = emf.createEntityManager();
        Vehicle vehicle = new Vehicle();
        try{
            entityManager.getTransaction().begin();
            vehicle = entityManager.find(Vehicle.class, id);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println("No vehicle found by ID");
        }
        return vehicle;
    }

    //DELETE
    public void deleteVehicle(Vehicle vehicle){
        entityManager = emf.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Vehicle.class, vehicle.getId()));
            entityManager.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(entityManager != null) entityManager.close();
        }
    }

    //UPDATE
    public void updateVehicle(Vehicle vehicle){
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(vehicle);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(entityManager != null) entityManager.close();
        }
    }

    public List<Vehicle> getAllVehicles() {
        entityManager = emf.createEntityManager();
        try {
            CriteriaQuery<Object> query = entityManager.getCriteriaBuilder().createQuery();
            query.select(query.from(Vehicle.class));
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
