package net.baumink.bzz.m326.db;

import net.baumink.bzz.m326.db.enums.Status;
import net.baumink.bzz.m326.db.pojo.CSOrder;
import net.baumink.bzz.m326.db.pojo.Employee;

import java.time.ZonedDateTime;
import java.util.List;

public class Database {

    private static Database INSTANCE;

    private Database() {
    }

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    public CSOrder getOrderByOrderNr(String orderNumber) {
        return DBConnection.getEntityManager().createQuery("select o from CSOrder o where orderNumber=:num", CSOrder.class).
                setParameter("num", orderNumber).
                getSingleResult();
    }

    public List<CSOrder> getAllOrders() {
        return DBConnection.getEntityManager().createQuery("select o from CSOrder o", CSOrder.class).getResultList();
    }

    public List<Employee> getAllEmployees() {
        return DBConnection.getEntityManager().createQuery("select o from Employee o", Employee.class).getResultList();
    }

    public void updateOrderStatus(String orderNr, Status status, Employee employee) {
        CSOrder order = getOrderByOrderNr(orderNr);

        DBConnection.getEntityManager().getTransaction().begin();

        order.setStatus(status);
        order.setLastEdited(ZonedDateTime.now());
        order.setLastEditor(employee);

        DBConnection.getEntityManager().getTransaction().commit();
    }

    private void deleteOrderNoTransaction(CSOrder order) {
        DBConnection.getEntityManager().remove(order);
    }

    public void insertSplitOrder(CSOrder originalOrder, CSOrder split1, CSOrder split2) {
        DBConnection.getEntityManager().getTransaction().begin();


        deleteOrderNoTransaction(originalOrder);
        DBConnection.getEntityManager().persist(new CSOrder(split1));
        DBConnection.getEntityManager().persist(new CSOrder(split2));

        DBConnection.getEntityManager().getTransaction().commit();
    }

    public boolean orderNrExists(String orderNr) {
        orderNr = orderNr.trim();
        return DBConnection.getEntityManager().createQuery("select count(o)>0 from CSOrder o where o.orderNumber=:num", Boolean.class).
                setParameter("num", orderNr).getSingleResult();
    }

}
