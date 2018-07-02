package net.baumink.bzz.m326.ui.view;

import net.baumink.bzz.m326.db.enums.Status;
import net.baumink.bzz.m326.db.pojo.CSOrder;
import net.baumink.bzz.m326.db.pojo.Employee;
import net.baumink.bzz.m326.ui.controller.TableController;
import net.baumink.bzz.m326.ui.view.util.ComponentCellRenderer;
import net.baumink.bzz.m326.ui.view.util.DateCellRenderer;
import net.baumink.bzz.m326.ui.view.util.TableMouseListener;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Vector;


public class MainWindow extends JFrame {

    private final JComboBox<Employee> comboEmployee;
    private TableController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<Status> comboStatus;

    private boolean changing = false;

    public MainWindow() {
        super();
        controller = new TableController();

        setupWindow();


        JPanel panelTopBar = new JPanel();
        FlowLayout layoutComboBoxes = new FlowLayout();
        layoutComboBoxes.setAlignment(FlowLayout.RIGHT);
        panelTopBar.setLayout(layoutComboBoxes);


        java.util.List<Employee> list = controller.getAllEmployees();

        Employee[] employees = new Employee[list.size()];
        employees = list.toArray(employees);

        comboEmployee = new JComboBox<>(employees);
        comboEmployee.addActionListener(e -> updateData());
        panelTopBar.add(comboEmployee);

        initTable();

        add(panelTopBar, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);
    }

    private void setupWindow() {
        setTitle("M236");
        setLayout(new BorderLayout());
        setSize(new Dimension(800, 500));
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initTable() {
        Vector<String> headerColumn = new Vector<>();
        headerColumn.addElement("Bestell-Nr.");
        headerColumn.addElement("Kunde");
        headerColumn.addElement("Status");
        headerColumn.addElement("Lieferung");
        headerColumn.addElement("Zuletzt bearbeitet");
        headerColumn.addElement("Zuletzt bearbeitet von");
        headerColumn.addElement("Details");
        headerColumn.addElement("Teilen");

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 || column == 3;
            }
        };

        tableModel.setColumnIdentifiers(headerColumn);

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        table.setAutoCreateRowSorter(true);
        TableRowSorter sorter = (TableRowSorter) table.getRowSorter();
        sorter.setSortable(6, false);

        comboStatus = new JComboBox<>();

        initTableColumns();

        tableModel.addTableModelListener(e -> {
                    int row = table.getSelectedRow();
                    if (e.getType() == TableModelEvent.UPDATE && !changing && row > -1) {
                        changing = true;

                        String orderNr = table.getValueAt(row, 0).toString();
                        Status status = (Status) table.getValueAt(row, 2);

                        controller.updateOrderStatus(orderNr, status);

                        SwingUtilities.invokeLater(() -> {
                            this.updateData();
                            changing = false;
                        });
                    }
                }
        );

        updateData();
    }

    private void initTableColumns() {
        TableColumn columnStatus = table.getColumnModel().getColumn(2);
        columnStatus.setCellEditor(new DefaultCellEditor(comboStatus));


        TableColumn columnDeliveryExpected = table.getColumnModel().getColumn(3);
        columnDeliveryExpected.setCellRenderer(new DateCellRenderer("dd-MM-yyyy"));


        TableColumn columnLastEdited = table.getColumnModel().getColumn(5);
        columnLastEdited.setCellRenderer(new DateCellRenderer("dd-MM-yyyy hh:mm"));

        table.addMouseListener(new TableMouseListener(table, 6, (event, row) -> {
            String orderNr = table.getValueAt(row, 0).toString();
            CSOrder order = controller.getOrderByOrderNr(orderNr);
            new DialogItems(order);
        }));
        table.getColumnModel().getColumn(6).setCellRenderer(new ComponentCellRenderer());

        table.addMouseListener(new TableMouseListener(table, 7, (event, row) -> {
            String orderNr = table.getValueAt(row, 0).toString();
            CSOrder order = controller.getOrderByOrderNr(orderNr);
            new DialogSplitOrder(order, controller.getSelectedEmployee()).showDialog();
            updateData();
        }));
        table.getColumnModel().getColumn(7).setCellRenderer(new ComponentCellRenderer());
    }

    private void updateData() {
        // So we don't have to cast
        controller.setSelectedEmployee(comboEmployee.getModel().getElementAt(comboEmployee.getSelectedIndex()));
        updateTable();
        revalidate();
        repaint();
    }

    private void updateTable() {
        java.util.List<CSOrder> orders = controller.getFilteredData();

        comboStatus.removeAllItems();
        for (Status s : controller.getAvailableStatuses()) {
            comboStatus.addItem(s);
        }

        tableModel.setRowCount(orders.size());

        int row = 0;
        int column = 0;
        for (CSOrder order : orders) {
            tableModel.setValueAt(order.getOrderNumber(), row, column++);
            tableModel.setValueAt(order.getClient().toString(), row, column++);
            tableModel.setValueAt(order.getStatus(), row, column++);
            tableModel.setValueAt(order.getDeliveryExpected(), row, column++);
            tableModel.setValueAt(order.getLastEditor(), row, column++);
            tableModel.setValueAt(order.getLastEdited(), row, column++);
            tableModel.setValueAt(new JButton("Details"), row, column++);
            JButton btnSplit = new JButton("Bestellung teilen");
            if (order.getItems().size() < 2) btnSplit.setEnabled(false);
            tableModel.setValueAt(btnSplit, row, column);
            column = 0;
            row++;
        }
    }

}

