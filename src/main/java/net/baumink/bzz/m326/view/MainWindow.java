package net.baumink.bzz.m326.view;

import net.baumink.bzz.m326.db.DBConnection;
import net.baumink.bzz.m326.db.pojo.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;


public class MainWindow extends JFrame {

    private static final String[] DEPARTMENTS = {"Lager", "Vertrieb", "Lieferant"};
    private final JComboBox<Employee> comboEmployee;

    private JTable table;
    public MainWindow() {
        super();


        setTitle("M236");
        setLayout(new BorderLayout());
        setSize(new Dimension(800, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JPanel panelComboBoxes = new JPanel(); // TODO: Rename
        FlowLayout layoutComboBoxes = new FlowLayout();
        layoutComboBoxes.setAlignment(FlowLayout.RIGHT);
        panelComboBoxes.setLayout(layoutComboBoxes);


        java.util.List<Employee> list = (java.util.List<Employee>) DBConnection.getEntityManager().createQuery("select o from Employee o").getResultList();


        Employee[] employees = new Employee[list.size()];
        employees = list.toArray(employees);

        comboEmployee = new JComboBox<>(employees);

        Vector<String> headerColumn = new Vector<>();
        headerColumn.addElement("ID");
        headerColumn.addElement("Client");
        headerColumn.addElement("Status");
        headerColumn.addElement("Delivery planned");
        headerColumn.addElement("Last Edited by");
        headerColumn.addElement("");

        DefaultTableModel modelTable = new DefaultTableModel(2, 2);
        modelTable.setColumnIdentifiers(headerColumn);
        table = new JTable(modelTable);

        panelComboBoxes.add(comboEmployee);
        add(panelComboBoxes, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        comboEmployee.addActionListener(e -> updateData());

        setVisible(true);
    }

    private void updateData() {
        //TODO
    }

}
