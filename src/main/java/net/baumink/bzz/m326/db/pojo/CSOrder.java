package net.baumink.bzz.m326.db.pojo;

import net.baumink.bzz.m326.db.enums.Status;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Benjamin Raison, Jonas Gredig
 * @version 1.0
 */
@Entity
public class CSOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String orderNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;
    @ManyToOne(fetch = FetchType.EAGER)
    private Employee lastEditor;
    private ZonedDateTime lastEdited;
    private ZonedDateTime deliveryExpected;
    private Status status;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Item> items;
    private boolean archived = false;

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

    public CSOrder(String orderNumber, Client client, Employee lastEditor, ZonedDateTime lastEdited, ZonedDateTime deliveryExpected, Status status, List<Item> items, boolean archived) {
        this.orderNumber = orderNumber;
        this.client = client;
        this.lastEditor = lastEditor;
        this.lastEdited = lastEdited;
        this.deliveryExpected = deliveryExpected;
        this.status = status;
        this.items = items;
        this.archived = archived;
    }

    /**
     * Cloning constructor
     *
     * @param order the order to clone
     */
    public CSOrder(CSOrder order) {
        this.id = order.id;
        this.orderNumber = order.getOrderNumber();
        this.client = order.getClient();
        this.lastEditor = order.getLastEditor();
        this.lastEdited = order.getLastEdited();
        this.deliveryExpected = order.getDeliveryExpected();
        this.status = order.getStatus();
        this.items = new ArrayList<>(order.getItems());
        this.archived = order.isArchived();
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

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CSOrder order = (CSOrder) o;

        if (archived != order.archived) return false;
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
        result = 31 * result + (archived ? 1 : 0);
        return result;
    }
}
