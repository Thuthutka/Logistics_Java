package com.hibernate;

import com.DeliveryService.Comment;
import com.users.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CommentHib {

    EntityManager entityManager = null;
    EntityManagerFactory entityManagerFactory = null;

    public CommentHib(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    //CREATE-----------------------------------------------------------
    public void createComment (Comment comment){
        entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(comment);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //UPDATE--------------------------------------------
    public void updateComment (Comment comment){
        entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(comment);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(entityManager != null) entityManager.close();
        }
    }

    //READ--------------------------------------
    public Comment getCommentById(int id){
        entityManager = entityManagerFactory.createEntityManager();
        Comment comment = new Comment();
        try{
            entityManager.getTransaction().begin();
            comment = entityManager.find(Comment.class, id);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println("No such vehicle with given ID");
        }
        return comment;
    }

    public List<Comment> getAllComments(){
        entityManager = entityManagerFactory.createEntityManager();
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Comment> query = criteriaBuilder.createQuery(Comment.class);
            Root<Comment> root = query.from(Comment.class);

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
    public void deleteComment (Comment comment){
        entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Comment.class, comment.getId()));
            entityManager.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(entityManager != null) entityManager.close();
        }
    }
}
