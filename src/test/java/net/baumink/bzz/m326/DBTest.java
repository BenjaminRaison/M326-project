package net.baumink.bzz.m326;

import net.baumink.bzz.m326.db.EmployeeType;
import net.baumink.bzz.m326.db.Status;
import net.baumink.bzz.m326.db.pojo.CSOrder;
import net.baumink.bzz.m326.db.pojo.Client;
import net.baumink.bzz.m326.db.pojo.Employee;
import net.baumink.bzz.m326.db.pojo.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DBTest {

    private EntityManager entityManager;

    @BeforeEach
    void init() {
        entityManager = InmemDBConnection.getEntityManager();
        System.out.println(entityManager.createQuery("select t from Item t").getResultList().size());
    }

    @AfterEach
    void cleanup() {
        entityManager.close();
        entityManager = null;
    }

    @Test
    void test_DB_insert_item() {
        Item item = new Item("Test item", "Superduper testitem 2.0 with AI", new BigDecimal("123.45"));
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();

        assertEquals(1, entityManager.createQuery("select t from Item t").getResultList().size());
    }

    @Test
    void test_DB_insert_employee() {
        Employee employee = new Employee("Jonas", "Gredig", EmployeeType.LIEFERANT);
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();

        assertEquals(1, entityManager.createQuery("select e from Employee e").getResultList().size());
    }

    @Test
    void test_DB_insert_client() {
        Client client = new Client("Raison", "Benjamin", "some place", "SJBM",
                "MyTown", "benji@raison.local", "imagethisisahash");
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();

        assertEquals(1, entityManager.createQuery("select c from Client c").getResultList().size());
    }

    @Test
    void test_DB_insert_order_null_dependencies() {
        CSOrder order = new CSOrder("123", null, null, ZonedDateTime.now(), ZonedDateTime.now(), Status.AUFBEREITEN, new ArrayList<>());
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();

        assertEquals(1, entityManager.createQuery("select o from CSOrder o").getResultList().size());
    }

    @Test
    void test_DB_insert_order_with_dependencies() {
        Item item = new Item("Test item", "Superduper testitem 2.0 with AI", new BigDecimal("123.45"));
        Employee employee = new Employee("Jonas", "Gredig", EmployeeType.LIEFERANT);
        Client client = new Client("Raison", "Benjamin", "some place", "SJBM",
                "MyTown", "benji@raison.local", "imagethisisahash");
        CSOrder order = new CSOrder("123", client, employee, ZonedDateTime.now(), ZonedDateTime.now(), Status.AUFBEREITEN, Collections.singletonList(item));
        entityManager.getTransaction().begin();

        entityManager.persist(item);
        entityManager.persist(employee);
        entityManager.persist(client);
        entityManager.persist(order);
        entityManager.getTransaction().commit();

        assertEquals(1, entityManager.createQuery("select o from CSOrder o").getResultList().size());
    }
}
