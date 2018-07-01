package net.baumink.bzz.m326.ui.view;

import net.baumink.bzz.m326.db.pojo.CSOrder;
import net.baumink.bzz.m326.db.pojo.Item;

import javax.swing.*;

public class DialogItems extends JDialog {

    private final CSOrder order;

    public DialogItems(CSOrder order) {
        super();
        this.order = order;
        init();
    }

    private void init() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setTitle("Bestellung " + order.getOrderNumber());

        Object[][] data = new Object[order.getItems().size()][3];
        for (int i = 0; i < data.length; i++) {
            Item item = order.getItems().get(i);
            data[i] = new Object[]{
                    item.getName(),
                    item.getDescription(),
                    item.getPrice()};
        }

        JTable table = new JTable(data, new Object[]{"Name", "Beschreibung", "Preis"});
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
        pack();
        requestFocus();
        setVisible(true);
    }

}
