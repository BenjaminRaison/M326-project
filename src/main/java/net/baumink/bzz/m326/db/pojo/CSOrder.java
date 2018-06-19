package net.baumink.bzz.m326.db.pojo;

import net.baumink.bzz.m326.db.Status;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * CoolShoes order
 */
@Entity
public class CSOrder {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true, nullable = false)
    private String orderNumber;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Employee lastEditor;
    private ZonedDateTime lastEdited;
    private ZonedDateTime deliveryExpected;
    private Status status;
    @ManyToMany
    private List<Item> items;

    public CSOrder() {
        // default constructor
    }

    public CSOrder(String orderNumber, Client client, Employee lastEditor, ZonedDateTime lastEdited, ZonedDateTime deliveryExpected, Status status, List<Item> items) {
        this.orderNumber = orderNumber;
        this.client = client;
        this.lastEditor = lastEditor;
        this.lastEdited = lastEdited;
        this.deliveryExpected = deliveryExpected;
        this.status = status;
        this.items = items;
    }

    public CSOrder(int id, String orderNumber, Client client, Employee lastEditor, ZonedDateTime lastEdited, ZonedDateTime deliveryExpected, Status status, List<Item> items) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.client = client;
        this.lastEditor = lastEditor;
        this.lastEdited = lastEdited;
        this.deliveryExpected = deliveryExpected;
        this.status = status;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getLastEditor() {
        return lastEditor;
    }

    public void setLastEditor(Employee lastEditor) {
        this.lastEditor = lastEditor;
    }

    public ZonedDateTime getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(ZonedDateTime lastEdited) {
        this.lastEdited = lastEdited;
    }

    public ZonedDateTime getDeliveryExpected() {
        return deliveryExpected;
    }

    public void setDeliveryExpected(ZonedDateTime deliveryExpected) {
        this.deliveryExpected = deliveryExpected;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CSOrder order = (CSOrder) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (orderNumber != null ? !orderNumber.equals(order.orderNumber) : order.orderNumber != null) return false;
        if (client != null ? !client.equals(order.client) : order.client != null) return false;
        if (lastEditor != null ? !lastEditor.equals(order.lastEditor) : order.lastEditor != null) return false;
        if (lastEdited != null ? !lastEdited.equals(order.lastEdited) : order.lastEdited != null) return false;
        if (deliveryExpected != null ? !deliveryExpected.equals(order.deliveryExpected) : order.deliveryExpected != null)
            return false;
        if (status != order.status) return false;
        return items != null ? items.equals(order.items) : order.items == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (lastEditor != null ? lastEditor.hashCode() : 0);
        result = 31 * result + (lastEdited != null ? lastEdited.hashCode() : 0);
        result = 31 * result + (deliveryExpected != null ? deliveryExpected.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }
}