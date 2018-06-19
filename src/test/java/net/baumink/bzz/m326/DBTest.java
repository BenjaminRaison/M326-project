package net.baumink.bzz.m326;

import net.baumink.bzz.m326.db.DBConnection;
import net.baumink.bzz.m326.db.pojo.Item;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DBTest {

    @Test
    void test_DB_connects() {
        EntityManager entityManager = DBConnection.getEntityManager();
        entityManager.close();
    }

    @Test
    void test_DB_insert_item() {
        Item item = new Item("Test item", "Superduper testitem 2.0 with AI", new BigDecimal("123.45"));
        EntityManager entityManager = DBConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();

        assertEquals(1, entityManager.createQuery("select t from Item t").getResultList().size());

        entityManager.close();
    }

}
