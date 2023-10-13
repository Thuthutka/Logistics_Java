package com.hibernate;


import com.DeliveryService.Forum;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ForumHib {
    EntityManager entityManager = null;
    EntityManagerFactory entityManagerFactory = null;

    public ForumHib(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    //CREATE-----------------------------------------------------------
    public void createForum (Forum forum){
        entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(forum);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //UPDATE--------------------------------------------
    public void updateForum (Forum forum){
        entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(forum);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(entityManager != null) entityManager.close();
        }
    }

    //READ--------------------------------------
    public Forum getForumById(int id){
        entityManager = entityManagerFactory.createEntityManager();
        Forum forum = new Forum();
        try{
            entityManager.getTransaction().begin();
            forum = entityManager.find(Forum.class, id);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println("No such forum with given ID");
        }
        return forum;
    }

    public List<Forum> getAllForums(){
        entityManager = entityManagerFactory.createEntityManager();
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Forum> query = criteriaBuilder.createQuery(Forum.class);
            Root<Forum> root = query.from(Forum.class);

            query.select(root);
            Query q = entityManager.createQuery(query);
            return q.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
        return null;
    }

    //DELETE--------------------------------------------
    public void deleteForum (Forum forum){
        entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Forum.class, forum.getId()));
            entityManager.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(entityManager != null) entityManager.close();
        }
    }
}
