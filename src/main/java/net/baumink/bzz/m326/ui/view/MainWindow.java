package net.baumink.bzz.m326.ui.view;

import net.baumink.bzz.m326.db.Status;
import net.baumink.bzz.m326.db.pojo.CSOrder;
import net.baumink.bzz.m326.db.pojo.Employee;
import net.baumink.bzz.m326.ui.controller.TableController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;


public class MainWindow extends JFrame {

    private final JComboBox<Employee> comboEmployee;
    private TableController tableController;
    private JTable table;
    private DefaultTableModel tableModel;

    public MainWindow() {
        super();
        tableController = new TableController();

        setTitle("M236");
        setLayout(new BorderLayout());
        setSize(new Dimension(800, 500));
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JPanel panelComboBoxes = new JPanel(); // TODO: Rename
        FlowLayout layoutComboBoxes = new FlowLayout();
        layoutComboBoxes.setAlignment(FlowLayout.RIGHT);
        panelComboBoxes.setLayout(layoutComboBoxes);


        java.util.List<Employee> list = tableController.getAllEmployees();


        Employee[] employees = new Employee[list.size()];
        employees = list.toArray(employees);

        comboEmployee = new JComboBox<>(employees);

        initTable();

        panelComboBoxes.add(comboEmployee);
        add(panelComboBoxes, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        comboEmployee.addActionListener(e -> updateData());

        setVisible(true);
    }

    private void initTable() {
        Vector<String> headerColumn = new Vector<>();
        headerColumn.addElement("Order Number");
        headerColumn.addElement("Client");
        headerColumn.addElement("Status");
        headerColumn.addElement("Delivery Expected");
        headerColumn.addElement("Last Editor");
        headerColumn.addElement("Last Edited");
        headerColumn.addElement(""); // Empty Header -> Column for buttons

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 || column == 3;
            }
        };

        tableModel.setColumnIdentifiers(headerColumn);

        table = new JTable(tableModel);
        updateData();

        table.getModel().addTableModelListener(e -> {
            // TODO
        });
    }

    private void updateData() {
        // So we don't have to cast
        tableController.setSelectedEmployee(comboEmployee.getModel().getElementAt(comboEmployee.getSelectedIndex()));
        updateTable();
    }

    private void updateTable() {
        java.util.List<CSOrder> orders = tableController.getFilteredData();
        JComboBox<Status> comboStatus = new JComboBox<>();
        comboStatus.addItem(Status.BESTELLT);
        comboStatus.addItem(Status.VERSANDBEREIT);
        comboStatus.addItem(Status.AUFBEREITET);
        comboStatus.addItem(Status.ABGEHOLT);
        comboStatus.addItem(Status.GELIEFERT);
        comboStatus.addItem(Status.TEILAUFTRAG_VERSPÄTET);


        TableColumn columnStatus = table.getColumnModel().getColumn(2);
        columnStatus.setCellEditor(new DefaultCellEditor(comboStatus));

        TableColumn columnDeliveryExpected = table.getColumnModel().getColumn(3);
        columnDeliveryExpected.setCellRenderer(new DateCellRenderer("dd-MM-yyyy"));

        TableColumn columnLastEdited = table.getColumnModel().getColumn(5);
        columnLastEdited.setCellRenderer(new DateCellRenderer("dd-MM-yyyy hh:mm"));

        tableModel.setRowCount(orders.size());

        int row = 0;
        int column = 0;
        for (CSOrder order : orders) {
            tableModel.setValueAt(order.getOrderNumber(), row, column++);
            tableModel.setValueAt(order.getClient().toString(), row, column++);
            tableModel.setValueAt(order.getStatus(), row, column++);
            tableModel.setValueAt(order.getDeliveryExpected(), row, column++);
            tableModel.setValueAt(order.getLastEditor(), row, column++);
            tableModel.setValueAt(order.getLastEdited(), row, column);
            column = 0;
            row++;
        }
    }

}

