package net.baumink.bzz.m326.db.dao;

import net.baumink.bzz.m326.db.pojo.Order;

import java.util.List;

public interface IOrderDao {

    void insert(Order order);

    void update(Order order);

    void delete(Order order);

    Order getById(String id);

    List<Order> getAll();

}
