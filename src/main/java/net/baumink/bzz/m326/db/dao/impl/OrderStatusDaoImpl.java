package net.baumink.bzz.m326.db.dao.impl;

import net.baumink.bzz.m326.db.dao.IOrderDao;
import net.baumink.bzz.m326.db.dao.IOrderStatusDao;
import net.baumink.bzz.m326.db.pojo.OrderStatus;
import net.baumink.bzz.m326.exceptions.InvalidPrimaryKeyException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Order status dao.
 *  @author Benjamin Raison, Jonas Gredig
 *  @version 1.0
 */
public class OrderStatusDaoImpl implements IOrderStatusDao {

    private Connection connection;
    private ZoneOffset offset = ZoneOffset.of("Europe/Zurich");
    private IOrderDao orderDao;

    /**
     * Instantiates a new Order status dao.
     *
     * @param connection the connection
     * @param orderDao   the order dao
     */
    public OrderStatusDaoImpl(Connection connection, IOrderDao orderDao) {
        this.connection = connection;
        this.orderDao = orderDao;
    }

    @Override
    public void insert(OrderStatus orderStatus) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO BestellStatus (Bestellnummer, Status, Bearbeitung, Lieferunggeplant) VALUES (?,?,?,?);")) {
            statement.setString(1, orderStatus.getOrder().getId());
            statement.setString(2, orderStatus.getStatus());
            statement.setTimestamp(3, localDateTimeToTimestamp(orderStatus.getEdited()));
            statement.setTimestamp(4, localDateTimeToTimestamp(orderStatus.getDeliveryPlanned()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert orderStatus!", e);
        }
    }

    @Override
    public void update(OrderStatus orderStatus) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE BestellStatus WHERE BSID=?, set Bestellnummer=?, Status=?, Bearbeitung=?, Lieferunggeplant=?;")) {
            statement.setInt(1, orderStatus.getId());
            statement.setString(2, orderStatus.getOrder().getId());
            statement.setString(3, orderStatus.getStatus());
            statement.setTimestamp(4, localDateTimeToTimestamp(orderStatus.getEdited()));
            statement.setTimestamp(5, localDateTimeToTimestamp(orderStatus.getDeliveryPlanned()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update orderStatus!", e);
        }
    }

    @Override
    public void delete(OrderStatus orderStatus) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM BestellStatus WHERE BSID=?;")) {
            statement.setInt(1, orderStatus.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete orderStatus!", e);
        }
    }

    @Override
    public OrderStatus getById(int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT BSID, Bestellnummer, Status, Bearbeitung, Lieferunggeplant FROM BestellStatus WHERE BSID=?;")) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new OrderStatus(
                        result.getInt("BSID"),
                        orderDao.getById(result.getString("Bestellnummer")),
                        result.getString("Status"),
                        timestampToLocalDateTime(result.getTimestamp("Bearbeitung")),
                        timestampToLocalDateTime(result.getTimestamp("Lieferunggeplant")));
            }
            throw new InvalidPrimaryKeyException("Kunde", "getById");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch orderStatus!", e);
        }
    }

    @Override
    public List<OrderStatus> getAll() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT BSID, Bestellnummer, Status, Bearbeitung, Lieferunggeplant FROM BestellStatus;")) {
            ResultSet result = statement.executeQuery();
            List<OrderStatus> list = new ArrayList<>();
            while (result.next()) {
                list.add(new OrderStatus(
                        result.getInt("BSID"),
                        orderDao.getById(result.getString("Bestellnummer")),
                        result.getString("Status"),
                        timestampToLocalDateTime(result.getTimestamp("Bearbeitung")),
                        timestampToLocalDateTime(result.getTimestamp("Lieferunggeplant"))));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch orderStatus's!", e);
        }
    }

    private Timestamp localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return new Timestamp(localDateTime.toInstant(offset).toEpochMilli());
    }

    private LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp.getTime(), 0, offset);
    }
}
