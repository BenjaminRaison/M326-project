package net.baumink.bzz.m326.ui.controller;

import net.baumink.bzz.m326.db.enums.EmployeeType;
import net.baumink.bzz.m326.db.enums.Status;
import net.baumink.bzz.m326.db.pojo.CSOrder;
import net.baumink.bzz.m326.db.pojo.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@Tag("unit")
class TableControllerTest {

    private List<CSOrder> orders;

    @BeforeEach
    void init() {
        orders = new ArrayList<>();
    }

    @Test
    void test_getFilteredData_vertrieb() {
        orders.add(new CSOrder(1, "123A", null, null, null, null, Status.BESTELLT, new ArrayList<>()));
        orders.add(new CSOrder(2, "123B", null, null, null, null, Status.TEILAUFTRAG_VERSPÄTET, new ArrayList<>()));

        genericTestGetFilteredData(EmployeeType.VERTRIEB);
    }

    @Test
    void test_getFilteredData_lager() {
        orders.add(new CSOrder(1, "123A", null, null, null, null, Status.AUFBEREITET, new ArrayList<>()));
        orders.add(new CSOrder(2, "123B", null, null, null, null, Status.AUFBEREITET, new ArrayList<>()));

        genericTestGetFilteredData(EmployeeType.LAGER);
    }

    @Test
    void test_getFilteredData_lieferant() {
        orders.add(new CSOrder(1, "123A", null, null, null, null, Status.VERSANDBEREIT, new ArrayList<>()));
        orders.add(new CSOrder(2, "123B", null, null, null, null, Status.VERSANDBEREIT, new ArrayList<>()));

        genericTestGetFilteredData(EmployeeType.LIEFERANT);
    }

    private void genericTestGetFilteredData(EmployeeType type) {
        TableController controller = mock(TableController.class);
        when(controller.getAllOrders()).thenReturn(orders);
        when(controller.getFilteredData()).thenCallRealMethod();
        doCallRealMethod().when(controller).setSelectedEmployee(anyObject());

        controller.setSelectedEmployee(new Employee("", "", type));

        Assertions.assertIterableEquals(orders, controller.getFilteredData());
    }

}
