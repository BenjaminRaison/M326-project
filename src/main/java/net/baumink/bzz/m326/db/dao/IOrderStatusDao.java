package net.baumink.bzz.m326.db.dao;

import net.baumink.bzz.m326.db.pojo.OrderStatus;

import java.util.List;

public interface IOrderStatusDao {

    void insert(OrderStatus orderStatus);

    void update(OrderStatus orderStatus);

    void delete(OrderStatus orderStatus);

    OrderStatus getById(int id);

    List<OrderStatus> getAll();

}
