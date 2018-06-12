package net.baumink.bzz.m326.db.dao;

import net.baumink.bzz.m326.db.pojo.Employee;

import java.util.List;

/**
 * The interface Employee dao.
 */
public interface IEmployeeDao {

    /**
     * Insert Employee.
     *
     * @param employee the employee
     */
    void insert(Employee employee);

    /**
     * Update Employee.
     *
     * @param employee the employee
     */
    void update(Employee employee);

    /**
     * Delete Employee.
     *
     * @param employee the employee
     */
    void delete(Employee employee);

    /**
     * Gets Employee by id.
     *
     * @param id the id
     * @return the by id
     */
    Employee getById(int id);

    /**
     * Gets all Employees.
     *
     * @return the all Employees
     */
    List<Employee> getAll();

}
