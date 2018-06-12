package net.baumink.bzz.m326.db.dao;

import net.baumink.bzz.m326.db.pojo.OrderStatus;

import java.util.List;

/**
 * The interface Order status dao.
 */
public interface IOrderStatusDao {

    /**
     * Insert OrderStatus.
     *
     * @param orderStatus the order status
     */
    void insert(OrderStatus orderStatus);

    /**
     * Update OrderStatus.
     *
     * @param orderStatus the order status
     */
    void update(OrderStatus orderStatus);

    /**
     * Delete.
     *
     * @param orderStatus the order status
     */
    void delete(OrderStatus orderStatus);

    /**
     * Gets OrderStatus by id.
     *
     * @param id the id
     * @return the by id
     */
    OrderStatus getById(int id);

    /**
     * Gets all OrderStatus.
     *
     * @return the all
     */
    List<OrderStatus> getAll();

}
