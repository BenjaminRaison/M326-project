package net.baumink.bzz.m326.ui.controller;

import net.baumink.bzz.m326.db.DBConnection;
import net.baumink.bzz.m326.db.EmployeeType;
import net.baumink.bzz.m326.db.Status;
import net.baumink.bzz.m326.db.pojo.CSOrder;
import net.baumink.bzz.m326.db.pojo.Employee;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("it")
class TableControllerIntegrationTest {

    private EntityManager entityManager;
    private TableController controller;

    @BeforeEach
    void init() {
        DBConnection.setIsTest();
        entityManager = DBConnection.getEntityManager();
        controller = new TableController();
    }

    @AfterEach
    void cleanup() {
        entityManager.close();
        entityManager = null;
        controller = null;
    }

    @Test
    @Disabled
        // FIXME: Fix this test
    void test_fetch_by_order_number() {
        CSOrder order = new CSOrder("123A", null, null, null, null, Status.BESTELLT, new ArrayList<>());
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();

        assertDoesNotThrow(() -> controller.getOrderByOrderNumber("123A"));
    }

    @Test
    @Disabled
        // FIXME: Fix this test
    void test_fetch_all_vertrieb() {
        controller.setSelectedEmployee(new Employee("", "", EmployeeType.VERTRIEB));
        System.out.println(controller.getFilteredData().size());

        CSOrder order = new CSOrder("123A", null, null, null, null, Status.BESTELLT, new ArrayList<>());
        CSOrder order2 = new CSOrder("123B", null, null, null, null, Status.BESTELLT, new ArrayList<>());

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.persist(order2);
        entityManager.getTransaction().commit();

        assertEquals(2, entityManager.createQuery("select o from CSOrder o").getResultList().size());
        assertEquals(2, controller.getFilteredData().size());
    }

}
