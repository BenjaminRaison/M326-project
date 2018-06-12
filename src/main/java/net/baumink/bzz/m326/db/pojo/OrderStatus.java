package net.baumink.bzz.m326.db.pojo;

import java.time.LocalDateTime;

/**
 * The type Order status.
 * @author Benjamin Raison, Jonas Gredig
 * @version 1.0
 */
public class OrderStatus {

    private int id;
    private Order order;
    private String status;
    private LocalDateTime edited;
    private LocalDateTime deliveryPlanned;

    /**
     * Instantiates a new Order status.
     *
     * @param order           the order
     * @param status          the status
     * @param edited          the edited
     * @param deliveryPlanned the delivery planned
     */
    public OrderStatus(Order order, String status, LocalDateTime edited, LocalDateTime deliveryPlanned) {
        this.order = order;
        this.status = status;
        this.edited = edited;
        this.deliveryPlanned = deliveryPlanned;
    }

    /**
     * Instantiates a new Order status.
     *
     * @param id              the id
     * @param order           the order
     * @param status          the status
     * @param edited          the edited
     * @param deliveryPlanned the delivery planned
     */
    public OrderStatus(int id, Order order, String status, LocalDateTime edited, LocalDateTime deliveryPlanned) {
        this.id = id;
        this.order = order;
        this.status = status;
        this.edited = edited;
        this.deliveryPlanned = deliveryPlanned;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets order.
     *
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets order.
     *
     * @param order the order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets edited.
     *
     * @return the edited
     */
    public LocalDateTime getEdited() {
        return edited;
    }

    /**
     * Sets edited.
     *
     * @param edited the edited
     */
    public void setEdited(LocalDateTime edited) {
        this.edited = edited;
    }

    /**
     * Gets delivery planned.
     *
     * @return the delivery planned
     */
    public LocalDateTime getDeliveryPlanned() {
        return deliveryPlanned;
    }

    /**
     * Sets delivery planned.
     *
     * @param deliveryPlanned the delivery planned
     */
    public void setDeliveryPlanned(LocalDateTime deliveryPlanned) {
        this.deliveryPlanned = deliveryPlanned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderStatus that = (OrderStatus) o;

        if (id != that.id) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (edited != null ? !edited.equals(that.edited) : that.edited != null) return false;
        return deliveryPlanned != null ? deliveryPlanned.equals(that.deliveryPlanned) : that.deliveryPlanned == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (edited != null ? edited.hashCode() : 0);
        result = 31 * result + (deliveryPlanned != null ? deliveryPlanned.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                ", order=" + order +
                ", status='" + status + '\'' +
                ", edited=" + edited +
                ", deliveryPlanned=" + deliveryPlanned +
                '}';
    }
}
