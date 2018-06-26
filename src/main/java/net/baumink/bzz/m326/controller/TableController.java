package net.baumink.bzz.m326.controller;

import net.baumink.bzz.m326.db.DBConnection;
import net.baumink.bzz.m326.db.pojo.CSOrder;
import net.baumink.bzz.m326.db.pojo.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class TableController {

    public List<CSOrder> getTableData() {
        EntityManager entityManager = DBConnection.getEntityManager();
        return entityManager.createQuery("select o from CSOrder o").getResultList();
    }

    public List<Employee> getAllEmployees() {
        EntityManager entityManager = DBConnection.getEntityManager();
        return DBConnection.getEntityManager().createQuery("select o from Employee o").getResultList();
    }
}
