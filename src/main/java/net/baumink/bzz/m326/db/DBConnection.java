package net.baumink.bzz.m326.db;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DBConnection {

    public static EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("m326-persistence-unit").createEntityManager();
    }

}
