package net.baumink.bzz.m326.db.dao;

import net.baumink.bzz.m326.db.pojo.Order;

import java.util.List;

/**
 * The interface Order dao.
 */
public interface IOrderDao {

    /**
     * Insert Order.
     *
     * @param order the order
     */
    void insert(Order order);

    /**
     * Update Order.
     *
     * @param order the order
     */
    void update(Order order);

    /**
     * Delete Order.
     *
     * @param order the order
     */
    void delete(Order order);

    /**
     * Gets Order by id.
     *
     * @param id the id
     * @return the by id
     */
    Order getById(String id);

    /**
     * Gets all Orders.
     *
     * @return the all
     */
    List<Order> getAll();

}
