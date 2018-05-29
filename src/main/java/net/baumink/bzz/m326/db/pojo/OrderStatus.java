package net.baumink.bzz.m326.db.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderStatus {

    private int id;
    private Order order;
    private String status;
    private LocalDateTime edited;
    private LocalDate deliveryPlanned;

    public OrderStatus(Order order, String status, LocalDateTime edited, LocalDate deliveryPlanned) {
        this.order = order;
        this.status = status;
        this.edited = edited;
        this.deliveryPlanned = deliveryPlanned;
    }

    public OrderStatus(int id, Order order, String status, LocalDateTime edited, LocalDate deliveryPlanned) {
        this.id = id;
        this.order = order;
        this.status = status;
        this.edited = edited;
        this.deliveryPlanned = deliveryPlanned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getEdited() {
        return edited;
    }

    public void setEdited(LocalDateTime edited) {
        this.edited = edited;
    }

    public LocalDate getDeliveryPlanned() {
        return deliveryPlanned;
    }

    public void setDeliveryPlanned(LocalDate deliveryPlanned) {
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
