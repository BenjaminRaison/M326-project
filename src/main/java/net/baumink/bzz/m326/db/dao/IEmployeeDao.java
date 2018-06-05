package net.baumink.bzz.m326.db.dao;

import net.baumink.bzz.m326.db.pojo.Employee;

import java.util.List;

public interface IEmployeeDao {

    void insert(Employee employee);

    void update(Employee employee);

    void delete(Employee employee);

    Employee getById(int id);

    List<Employee> getAll();

}
