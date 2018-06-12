package net.baumink.bzz.m326.view;

import net.baumink.bzz.m326.db.DBConnection;
import net.baumink.bzz.m326.db.dao.impl.EmployeeDaoImpl;
import net.baumink.bzz.m326.db.pojo.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainWindow extends JFrame {

    private JPanel contentPane = new JPanel();

    public MainWindow() {
        super();
        setTitle("M236");
        setLayout(new FlowLayout());
        setSize(new Dimension(800, 500));

        JPanel functionalityPanel = new JPanel();

        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl(DBConnection.getConnection());
        java.util.List<Employee> list = employeeDao.getAll();

        Employee[] employees = new Employee[list.size()];
        employees = list.toArray(employees);
        String[] departments = {"Lager", "Vertrieb", "Lieferant"};

        JComboBox<Employee> employeeBox = new JComboBox<>(employees);
        JComboBox<String> departmentBox = new JComboBox<>(departments);

        functionalityPanel.add(employeeBox);
        functionalityPanel.add(departmentBox);
        add(functionalityPanel);
        add(contentPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        employeeBox.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = employeeBox.getSelectedItem().toString();
            }
        });

        departmentBox.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = departmentBox.getSelectedItem().toString();
            }
        });
    }

    public void switchPanel(Views viewEnum) {
        this.contentPane.removeAll();
        switch (viewEnum) {
            case Lager:
                //this.contentPane.add(...);
                break;
            case Vertrieb:
                //this.contentPane.add(...);
                break;

            case Lieferant:
                //this.contentPane.add(...);
                break;

        }
        this.revalidate();
        this.repaint();
    }
}
