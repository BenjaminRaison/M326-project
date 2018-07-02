package net.baumink.bzz.m326.ui.view;

import net.baumink.bzz.m326.db.Database;
import net.baumink.bzz.m326.db.enums.Status;
import net.baumink.bzz.m326.db.pojo.CSOrder;
import net.baumink.bzz.m326.db.pojo.Employee;
import net.baumink.bzz.m326.db.pojo.Item;

import javax.swing.*;
import java.awt.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class DialogSplitOrder extends JDialog {

    private static final char[] ALPHABET =
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                    'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private JList<Item> listLeft;
    private JList<Item> listRight;


    public DialogSplitOrder(CSOrder order, Employee currentEmployee) {
        // FIXME: Cleanup

        DefaultListModel<Item> modelLeft = new DefaultListModel<>();
        order.getItems().forEach(modelLeft::addElement);

        listLeft = new JList<>(modelLeft);
        listRight = new JList<>(new DefaultListModel<>());

        String orderNrLeft = getNextOrderNumber(order.getOrderNumber());
        String orderNrRight = getNextOrderNumber(orderNrLeft);

        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
        panelLeft.add(new JLabel(orderNrLeft));
        panelLeft.add(new JScrollPane(listLeft));

        JPanel panelCentre = new JPanel();
        panelCentre.setLayout(new BoxLayout(panelCentre, BoxLayout.Y_AXIS));
        JButton btnMoveRight = new JButton("→");
        btnMoveRight.addActionListener(e -> {
            moveToOtherList(listLeft, listRight);
        });

        JButton btnMoveLeft = new JButton("←");
        btnMoveLeft.addActionListener(e -> {
            moveToOtherList(listRight, listLeft);
        });


        panelCentre.add(btnMoveRight);
        panelCentre.add(btnMoveLeft);

        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
        panelRight.add(new JLabel(orderNrRight));
        panelRight.add(new JScrollPane(listRight));


        JPanel panelContent = new JPanel(new GridLayout(0, 3));
        panelContent.add(panelLeft);
        panelContent.add(panelCentre);
        panelContent.add(panelRight);

        JPanel panelBtn = new JPanel();
        JButton btnCancel = new JButton("Abbrechen");
        btnCancel.addActionListener(e -> dispose());

        JButton btnSplit = new JButton("Bestellung teilen");
        btnSplit.addActionListener(e -> {
            doSplit(order, orderNrLeft, orderNrRight, currentEmployee);
            dispose();
        });


        panelBtn.add(btnSplit);
        panelBtn.add(btnCancel);


        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().add(panelContent);
        this.getContentPane().add(panelBtn);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        pack();
    }

    public void showDialog() {
        setVisible(true);
    }

    private void moveToOtherList(JList<Item> source, JList<Item> target) {
        Item item = source.getSelectedValue();
        if (item != null) {
            ((DefaultListModel<Item>) source.getModel()).remove(source.getSelectedIndex());
            ((DefaultListModel<Item>) target.getModel()).addElement(item);
        }
    }

    String getNextOrderNumber(String orderNumber) {
        if (orderNumber == null || orderNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid order number: " + orderNumber);
        }

        orderNumber = orderNumber.trim();
        if (orderNumber.matches("^[0-9]*$")) {
            return orderNumber + ALPHABET[0];
        }

        if (orderNumber.matches("^[0-9]+[A-Y]$")) {
            for (char letter : ALPHABET) {
                String result = orderNumber.substring(0, orderNumber.length() - 1) + letter;
                if (!Database.getInstance().orderNrExists(result) && !result.equals(orderNumber)) {
                    return result;
                }
            }
        }
        throw new IllegalArgumentException("Invalid order number: " + orderNumber);
    }

    private void doSplit(CSOrder originalOrder, String orderNrLeft, String orderNrRight, Employee employee) {
        CSOrder order = new CSOrder(
                orderNrLeft,
                originalOrder.getClient(),
                employee, ZonedDateTime.now(),
                ZonedDateTime.now(),
                Status.TEILAUFTRAG_VERSPÄTET,
                getItemsFromJList(listLeft));
        CSOrder order2 = new CSOrder(
                orderNrRight,
                originalOrder.getClient(),
                employee, ZonedDateTime.now(),
                ZonedDateTime.now(),
                Status.TEILAUFTRAG_VERSPÄTET,
                getItemsFromJList(listRight));

        Database.getInstance().insertSplitOrder(originalOrder, order, order2);
    }

    private java.util.List<Item> getItemsFromJList(JList<Item> list) {
        java.util.List<Item> collection = new ArrayList<>();
        for (int i = 0; i < list.getModel().getSize(); i++) {
            collection.add(list.getModel().getElementAt(i));
        }
        return collection;
    }

}
