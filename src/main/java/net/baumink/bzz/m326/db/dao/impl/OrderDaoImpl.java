package net.baumink.bzz.m326.db.dao.impl;

import net.baumink.bzz.m326.db.dao.IClientDao;
import net.baumink.bzz.m326.db.dao.IEmployeeDao;
import net.baumink.bzz.m326.db.dao.IOrderDao;
import net.baumink.bzz.m326.db.dao.IOrderStatusDao;
import net.baumink.bzz.m326.db.pojo.Order;
import net.baumink.bzz.m326.exceptions.InvalidPrimaryKeyException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Order dao.
 *  @author Benjamin Raison, Jonas Gredig
 *  @version 1.0
 */
public class OrderDaoImpl implements IOrderDao {

    private Connection connection;
    private IClientDao clientDao;
    private IEmployeeDao employeeDao;
    private IOrderStatusDao orderStatusDao;

    public OrderDaoImpl(Connection connection, IClientDao clientDao, IEmployeeDao employeeDao, IOrderStatusDao orderStatusDao) {
        this.connection = connection;
        this.clientDao = clientDao;
        this.employeeDao = employeeDao;
        this.orderStatusDao = orderStatusDao;
    }

    @Override
    public void insert(Order order) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Bestellung (FKKunde, FKStatus, FKMitarbeiter) VALUES (?,?,?);")) {
            statement.setInt(1, order.getClient().getId());
            statement.setInt(2, order.getStatus().getId());
            statement.setInt(3, order.getEmployee().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert order!", e);
        }
    }

    @Override
    public void update(Order order) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE Bestellung WHERE BID=?, set FKKKunde=?, FKStatus=?, FKMitarbeiter=?;")) {
            statement.setString(1, order.getId());
            statement.setInt(2, order.getClient().getId());
            statement.setInt(3, order.getStatus().getId());
            statement.setInt(4, order.getEmployee().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update order!", e);
        }
    }

    @Override
    public void delete(Order order) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Bestellung WHERE BID=?;")) {
            statement.setString(1, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete order!", e);
        }
    }

    @Override
    public Order getById(String id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT BID, FKKunde, FKStatus, FKMitarbeiter FROM Bestellung WHERE BID=?;")) {
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Order(
                        result.getString("BID"),
                        clientDao.getById(result.getInt("FKKunde")),
                        orderStatusDao.getById(result.getInt("FKStatus")),
                        employeeDao.getById(result.getInt("FKMitarbeiter")));
            }
            throw new InvalidPrimaryKeyException("Bestellung", "getById");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch order!", e);
        }
    }

    @Override
    public List<Order> getAll() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT BID, FKKunde, FKStatus, FKMitarbeiter FROM Bestellung;")) {
            ResultSet result = statement.executeQuery();
            List<Order> list = new ArrayList<>();
            while (result.next()) {
                list.add(new Order(
                        result.getString("BID"),
                        clientDao.getById(result.getInt("FKKunde")),
                        orderStatusDao.getById(result.getInt("FKStatus")),
                        employeeDao.getById(result.getInt("FKMitarbeiter"))));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch orders!", e);
        }
    }
}
