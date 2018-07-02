package net.baumink.bzz.m326.ui.controller;

import net.baumink.bzz.m326.db.DBConnection;
import net.baumink.bzz.m326.db.enums.EmployeeType;
import net.baumink.bzz.m326.db.enums.Status;
import net.baumink.bzz.m326.db.pojo.CSOrder;
import net.baumink.bzz.m326.db.pojo.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("it")
class TableControllerIntegrationTest {

    private EntityManager entityManager;
    private TableController controller;

    private static Stream<Arguments> test_fetch_all_data() {
        return Stream.of(
                Arguments.of(EmployeeType.LIEFERANT, Status.VERSANDBEREIT),
                Arguments.of(EmployeeType.VERTRIEB, Status.TEILAUFTRAG_VERSPÄTET),
                Arguments.of(EmployeeType.VERTRIEB, Status.BESTELLT),
                Arguments.of(EmployeeType.LAGER, Status.AUFBEREITET)
        );
    }

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
    void test_fetch_by_order_number() {
        CSOrder order = new CSOrder("123A", null, null, null, null, Status.BESTELLT, new ArrayList<>());
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();

        assertDoesNotThrow(() -> controller.getOrderByOrderNr("123A"));
    }

    @ParameterizedTest
    @MethodSource("test_fetch_all_data")
    void test_fetch_all(EmployeeType employeeType, Status status) {
        controller.setSelectedEmployee(new Employee("", "", employeeType));
        System.out.println(controller.getFilteredData().size());

        entityManager.getTransaction().begin();
        CSOrder order = new CSOrder(
                "123A",
                null,
                null,
                null,
                null,
                status,
                new ArrayList<>());
        entityManager.persist(order);

        entityManager.getTransaction().commit();

        assertEquals(1, entityManager.createQuery("select o from CSOrder o").getResultList().size());
        assertEquals(1, controller.getFilteredData().size());
    }
}
