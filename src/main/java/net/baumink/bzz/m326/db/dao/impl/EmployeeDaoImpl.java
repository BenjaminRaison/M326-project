package net.baumink.bzz.m326.db.dao.impl;

import net.baumink.bzz.m326.db.dao.IEmployeeDao;
import net.baumink.bzz.m326.db.pojo.Employee;
import net.baumink.bzz.m326.exceptions.InvalidPrimaryKeyException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements IEmployeeDao {

    private Connection connection;

    public EmployeeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Mitarbeiter (MAName, MAVorname) VALUES (?,?);")) {
            statement.setString(1, employee.getSurname());
            statement.setString(2, employee.getFirstName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert employee!", e);
        }
    }

    @Override
    public void update(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE Mitarbeiter WHERE MID=?, set MAName=?, MAVorname=?;")) {
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getSurname());
            statement.setString(3, employee.getFirstName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update employee!", e);
        }
    }

    @Override
    public void delete(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Mitarbeiter WHERE MID=?;")) {
            statement.setInt(1, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete employee!", e);
        }
    }

    @Override
    public Employee getById(int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT MID, MAName, MAVorname FROM Mitarbeiter WHERE MID=?;")) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Employee(
                        result.getInt("MID"),
                        result.getString("MAName"),
                        result.getString("MAVorname"));
            }
            throw new InvalidPrimaryKeyException("Mitarbeiter", "getById");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch employee!", e);
        }
    }

    @Override
    public List<Employee> getAll() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT MID, MAName, MAVorname FROM Mitarbeiter;")) {
            ResultSet result = statement.executeQuery();
            List<Employee> list = new ArrayList<>();
            while (result.next()) {
                list.add(new Employee(
                        result.getInt("MID"),
                        result.getString("MAName"),
                        result.getString("MAVorname")));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch employees!", e);
        }
    }
}
