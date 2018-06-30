package net.baumink.bzz.m326.ui.controller;

import net.baumink.bzz.m326.db.DBConnection;
import net.baumink.bzz.m326.db.Status;
import net.baumink.bzz.m326.db.pojo.CSOrder;
import net.baumink.bzz.m326.db.pojo.Employee;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TableController {

    private Employee selectedEmployee;

    private List<CSOrder> getTableData() {
        return DBConnection.getEntityManager().createQuery("select o from CSOrder o", CSOrder.class).getResultList();
    }

    public List<Employee> getAllEmployees() {
        return DBConnection.getEntityManager().createQuery("select o from Employee o", Employee.class).getResultList();
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
        return getTableData().stream().filter(csOrder -> status.contains(csOrder.getStatus())).collect(Collectors.toList());
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }
}
