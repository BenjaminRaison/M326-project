package net.baumink.bzz.m326.db.pojo;

import net.baumink.bzz.m326.db.EmployeeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String surname;
    private EmployeeType type;

    public Employee() {
    }

    public Employee(String firstName, String surname, EmployeeType type) {
        this.firstName = firstName;
        this.surname = surname;
        this.type = type;
    }

    public Employee(int id, String firstName, String surname, EmployeeType type) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (surname != null ? !surname.equals(employee.surname) : employee.surname != null) return false;
        return type == employee.type;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %s [%s]", firstName, surname, type.getValue());
    }
}
