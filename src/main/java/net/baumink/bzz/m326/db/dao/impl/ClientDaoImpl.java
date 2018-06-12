package net.baumink.bzz.m326.db.dao.impl;

import net.baumink.bzz.m326.db.dao.IClientDao;
import net.baumink.bzz.m326.db.pojo.Client;
import net.baumink.bzz.m326.exceptions.InvalidPrimaryKeyException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Client dao.
 * @author Benjamin Raison, Jonas Gredig
 * @version 1.0
 */
public class ClientDaoImpl implements IClientDao {

    private Connection connection;

    /**
     * Instantiates a new Client dao.
     *
     * @param connection the connection
     */
    public ClientDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Client client) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Kunde (KName, KVorname, KAdresse, KPLZ, KOrt, KEmailAdresse, KPWD) VALUES (?,?,?,?,?,?,?);")) {
            statement.setString(1, client.getSurname());
            statement.setString(2, client.getFirstName());
            statement.setString(3, client.getAdress());
            statement.setString(4, client.getPostcode());
            statement.setString(5, client.getTown());
            statement.setString(6, client.getEmail());
            statement.setString(7, client.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert client!", e);
        }
    }

    @Override
    public void update(Client client) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE Kunde WHERE KID=?, set KName=?, KVorname=?, KAdresse=?, KPLZ=?, KOrt=?, KEmailAdresse=?, KPWD=?;")) {
            statement.setInt(1, client.getId());
            statement.setString(2, client.getSurname());
            statement.setString(3, client.getFirstName());
            statement.setString(4, client.getAdress());
            statement.setString(5, client.getPostcode());
            statement.setString(6, client.getTown());
            statement.setString(7, client.getEmail());
            statement.setString(8, client.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update client!", e);
        }
    }

    @Override
    public void delete(Client client) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Kunde WHERE KID=?;")) {
            statement.setInt(1, client.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete client!", e);
        }
    }

    @Override
    public Client getById(int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT KID, KName, KVorname, KAdresse, KPLZ, KOrt, KEmailAdresse, KPWD FROM Kunde WHERE KID=?;")) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Client(
                        result.getInt("KID"),
                        result.getString("KName"),
                        result.getString("KVorname"),
                        result.getString("KAdresse"),
                        result.getString("KPLZ"),
                        result.getString("KOrt"),
                        result.getString("KEmailAdresse"),
                        result.getString("KPWD"));
            }
            throw new InvalidPrimaryKeyException("Kunde", "getById");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch client!", e);
        }
    }

    @Override
    public List<Client> getAll() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT KID, KName, KVorname, KAdresse, KPLZ, KOrt, KEmailAdresse, KPWD FROM Kunde;")) {
            ResultSet result = statement.executeQuery();
            List<Client> list = new ArrayList<>();
            while (result.next()) {
                list.add(new Client(
                        result.getInt("KID"),
                        result.getString("KName"),
                        result.getString("KVorname"),
                        result.getString("KAdresse"),
                        result.getString("KPLZ"),
                        result.getString("KOrt"),
                        result.getString("KEmailAdresse"),
                        result.getString("KPWD")));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch clients!", e);
        }
    }
}
