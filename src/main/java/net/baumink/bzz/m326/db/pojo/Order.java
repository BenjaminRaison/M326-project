package net.baumink.bzz.m326.db.pojo;

/**
 * The type Order.
 * @author Benjamin Raison, Jonas Gredig
 * @version 1.0
 */
public class Order {

    private String id;
    private Client client;
    private OrderStatus status;
    private Employee employee;

    /**
     * Instantiates a new Order.
     *
     * @param client   the client
     * @param status   the status
     * @param employee the employee
     */
    public Order(Client client, OrderStatus status, Employee employee) {
        this.client = client;
        this.status = status;
        this.employee = employee;
    }

    /**
     * Instantiates a new Order.
     *
     * @param id       the id
     * @param client   the client
     * @param status   the status
     * @param employee the employee
     */
    public Order(String id, Client client, OrderStatus status, Employee employee) {
        this.id = id;
        this.client = client;
        this.status = status;
        this.employee = employee;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets client.
     *
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Sets client.
     *
     * @param client the client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * Gets employee.
     *
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets employee.
     *
     * @param employee the employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (client != null ? !client.equals(order.client) : order.client != null) return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;
        return employee != null ? employee.equals(order.employee) : order.employee == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", client=" + client +
                ", status=" + status +
                ", employee=" + employee +
                '}';
    }
}
