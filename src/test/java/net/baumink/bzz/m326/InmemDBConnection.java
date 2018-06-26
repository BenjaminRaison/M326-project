package net.baumink.bzz.m326;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class InmemDBConnection {

    public static EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("m326-persistence-unit-inmem").createEntityManager();
    }

}
