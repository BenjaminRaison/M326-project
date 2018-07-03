package net.baumink.bzz.m326.ui.controller;

import net.baumink.bzz.m326.db.Database;
import net.baumink.bzz.m326.db.enums.Status;
import net.baumink.bzz.m326.db.pojo.CSOrder;
import net.baumink.bzz.m326.db.pojo.Employee;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TableController {

    private Employee selectedEmployee;


    /**
     * Fetches all orders from the database.
     * Pagination / lazy loading is not necessary for the amount of data present
     * Package-private for testing purposes
     *
     * @return all orders
     */
    List<CSOrder> getAllOrders() {
        return Database.getInstance().getAllOrders();
    }

    public CSOrder getOrderByOrderNr(String orderNumber) {
        return Database.getInstance().getOrderByOrderNr(orderNumber);
    }

    public List<Employee> getAllEmployees() {
        return Database.getInstance().getAllEmployees();
    }

    public void updateOrderStatus(String orderNr, Status status) {
        Database.getInstance().updateOrderStatus(orderNr, status, selectedEmployee);
    }

    public List<CSOrder> getFilteredData() {
        Set<Status> status = new HashSet<>();
        switch (selectedEmployee.getType()) {
            case VERTRIEB:
                status.add(Status.BESTELLT);
                status.add(Status.TEILAUFTRAG_VERSPÄTET);
                break;
            case LAGER:
                status.add(Status.AUFBEREITET);
                break;
            case LIEFERANT:
                status.add(Status.VERSANDBEREIT);
                break;
        }
        return getAllOrders().stream().filter(csOrder -> status.contains(csOrder.getStatus())).collect(Collectors.toList());
    }

    /**
     * @return a set of statuses the user can set on an order.
     * Also includes the filter criteria (the visible statuses),
     * so users can revert mistakes
     */
    public Set<Status> getAvailableStatuses() {
        Set<Status> status = new HashSet<>();
        switch (selectedEmployee.getType()) {
            case VERTRIEB:
                status.add(Status.BESTELLT);
                status.add(Status.AUFBEREITET);
                status.add(Status.TEILAUFTRAG_VERSPÄTET);
                break;
            case LAGER:
                status.add(Status.AUFBEREITET);
                status.add(Status.VERSANDBEREIT);
                break;
            case LIEFERANT:
                status.add(Status.VERSANDBEREIT);
                status.add(Status.ABGEHOLT);
                status.add(Status.GELIEFERT);
                break;
        }
        return status;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }
}
