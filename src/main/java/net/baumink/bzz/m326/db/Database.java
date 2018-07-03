package net.baumink.bzz.m326.db;

import net.baumink.bzz.m326.db.enums.Status;
import net.baumink.bzz.m326.db.pojo.CSOrder;
import net.baumink.bzz.m326.db.pojo.Employee;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Benjamin Raison, Jonas Gredig
 * @version 1.0
 */
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
        List<CSOrder> list = DBConnection.getEntityManager().createQuery("select o from CSOrder o where o.orderNumber=:num and o.archived=false", CSOrder.class).
                setParameter("num", orderNumber).
                getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    public List<CSOrder> getAllOrders() {
        return DBConnection.getEntityManager().createQuery("select o from CSOrder o where o.archived=false", CSOrder.class).getResultList();
    }

    public List<Employee> getAllEmployees() {
        return DBConnection.getEntityManager().createQuery("select o from Employee o", Employee.class).getResultList();
    }

    public void updateOrderStatus(String orderNr, Status status, Employee employee) {
        CSOrder order = getOrderByOrderNr(orderNr);
        if (order == null) return; // Can be null because of thread timings

        CSOrder copy = new CSOrder(order);
        copy.setId(null);

        DBConnection.getEntityManager().getTransaction().begin();

        order.setArchived(true);
        copy.setStatus(status);
        copy.setLastEdited(ZonedDateTime.now());
        copy.setLastEditor(employee);
        DBConnection.getEntityManager().persist(copy);

        DBConnection.getEntityManager().getTransaction().commit();
    }

    public void insertSplitOrder(CSOrder originalOrder, CSOrder split1, CSOrder split2) {
        DBConnection.getEntityManager().getTransaction().begin();

        originalOrder.setArchived(true);

        DBConnection.getEntityManager().persist(split1);
        DBConnection.getEntityManager().persist(split2);

        DBConnection.getEntityManager().getTransaction().commit();
    }

    public boolean orderNrExists(String orderNr) {
        orderNr = orderNr.trim();
        return DBConnection.getEntityManager().createQuery("select count(o)>0 from CSOrder o where o.orderNumber=:num", Boolean.class).
                setParameter("num", orderNr).getSingleResult();
    }
}
