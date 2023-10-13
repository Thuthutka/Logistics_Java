package com.hibernate;

import com.users.Manager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class testHibernate {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LogisticsComp");
        UserHib userHib = new UserHib(entityManagerFactory);

        Manager admin = new Manager("admin", "admin", "admin", "admin", true);
        userHib.createUser(admin);

    }
}
