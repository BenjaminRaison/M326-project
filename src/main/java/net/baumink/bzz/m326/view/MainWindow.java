package net.baumink.bzz.m326.view;

import net.baumink.bzz.m326.db.pojo.Employee;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MainWindow extends JFrame {

    private static final String[] DEPARTMENTS = {"Lager", "Vertrieb", "Lieferant"};
    private final JComboBox<Employee> comboEmployee;
    private final JComboBox<String> comboDepartment;

    private final TablePanel tablePanel;

    public MainWindow() {
        super();


        setTitle("M236");
        setLayout(new FlowLayout());
        setSize(new Dimension(800, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panelComboBoxes = new JPanel(); // TODO: Rename

        java.util.List<Employee> list = new ArrayList<>();// FIXME employeeDao.getAllEmployees();

        Employee[] employees = new Employee[list.size()];
        employees = list.toArray(employees);

        comboEmployee = new JComboBox<>(employees);
        comboDepartment = new JComboBox<>(DEPARTMENTS);


        comboEmployee.addActionListener(e -> updateData());
        comboDepartment.addActionListener(e -> updateData());

        tablePanel = new TablePanel();

        panelComboBoxes.add(comboEmployee);
        panelComboBoxes.add(comboDepartment);

        add(panelComboBoxes);
        add(tablePanel);

        setVisible(true);
    }

    private void updateData() {
        tablePanel.updateData(
                (String) comboDepartment.getSelectedItem(),
                (Employee) comboEmployee.getSelectedItem());
    }

}
