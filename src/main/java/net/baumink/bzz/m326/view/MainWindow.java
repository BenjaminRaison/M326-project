package net.baumink.bzz.m326.view;

import net.baumink.bzz.m326.controller.TableController;
import net.baumink.bzz.m326.db.DBConnection;
import net.baumink.bzz.m326.db.Status;
import net.baumink.bzz.m326.db.pojo.CSOrder;
import net.baumink.bzz.m326.db.pojo.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;


public class MainWindow extends JFrame {

    private final JComboBox<Employee> comboEmployee;
    private TableController tableController;
    private JTable table;
    private DefaultTableModel modelTable;

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


        java.util.List<Employee> list = (java.util.List<Employee>) DBConnection.getEntityManager().createQuery("select o from Employee o").getResultList();


        Employee[] employees = new Employee[list.size()];
        employees = list.toArray(employees);

        comboEmployee = new JComboBox<>(employees);

        Vector<String> headerColumn = new Vector<>();
        headerColumn.addElement("Order Number");
        headerColumn.addElement("Client");
        headerColumn.addElement("Status");
        headerColumn.addElement("Delivery Expected");
        headerColumn.addElement("Last Editor");
        headerColumn.addElement("Last Edited");
        headerColumn.addElement(""); // Empty Header -> Column for buttons

        modelTable = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 2 || column == 3) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        modelTable.setColumnIdentifiers(headerColumn);

        table = new JTable(modelTable);
        updateData();


        panelComboBoxes.add(comboEmployee);
        add(panelComboBoxes, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        comboEmployee.addActionListener(e -> updateData());

        setVisible(true);
    }

    private void updateData() {
        updateTable();
    }

    private void updateTable() {
        java.util.List<CSOrder> allOrders = tableController.getTableData();
        int row = 0;
        int column = 0;
        JComboBox<Status> comboStatus = new JComboBox<>();
        comboStatus.addItem(Status.BESTELLT);
        comboStatus.addItem(Status.VERSANDBEREIT);
        comboStatus.addItem(Status.AUFBEREITEN);
        comboStatus.addItem(Status.ABGEHOLT);
        comboStatus.addItem(Status.GELIEFERT);
        comboStatus.addItem(Status.TEILAUFTRAG_VERSPÄTET);


        TableColumn columnStatus = table.getColumnModel().getColumn(2);
        columnStatus.setCellEditor(new DefaultCellEditor(comboStatus));

        TableColumn columnDeliveryExpected = table.getColumnModel().getColumn(3);
        columnDeliveryExpected.setCellRenderer(new DateCellRenderer("dd-MM-yyyy"));

        TableColumn columnLastEdited = table.getColumnModel().getColumn(5);
        columnLastEdited.setCellRenderer(new DateCellRenderer("dd-MM-yyyy hh:mm"));

        modelTable.setRowCount(allOrders.size());
        for (CSOrder order : allOrders) {
            modelTable.setValueAt(order.getOrderNumber(), row, column++);
            modelTable.setValueAt(order.getClient().toString(), row, column++);
            modelTable.setValueAt(order.getStatus(), row, column++);
            modelTable.setValueAt(order.getDeliveryExpected(), row, column++);
            modelTable.setValueAt(order.getLastEditor(), row, column++);
            modelTable.setValueAt(order.getLastEdited(), row, column);
            column = 0;
            row++;
        }
    }
}

