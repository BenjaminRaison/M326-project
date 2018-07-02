package net.baumink.bzz.m326.db;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DBConnection {

    private static boolean isProd = true;
    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = isProd ? getProdEntityManager() : getTestEntityManager();
        }
        return entityManager;
    }

    private static EntityManager getProdEntityManager() {
        return Persistence.createEntityManagerFactory("m326-persistence-unit").createEntityManager();
    }

    private static EntityManager getTestEntityManager() {
        return Persistence.createEntityManagerFactory("m326-persistence-unit-inmem").createEntityManager();
    }

    public static void setIsTest() {
        isProd = false;
    }

}
