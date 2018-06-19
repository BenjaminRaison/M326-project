package net.baumink.bzz.m326.view;


import net.baumink.bzz.m326.db.pojo.Employee;

import javax.swing.*;

// TODO: Rename
class TablePanel extends JPanel {

    TablePanel() {
        init();
    }

    private void init() {
        JTable table = new JTable(new Object[][]{}, new String[]{"Nr", "Kunde", "Status", "Lieferung geplant", "Bearbeitet", "Mitarbeiter"});

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.add(table);
        add(scrollPane);
    }

    void updateData(String department, Employee employee) {

    }

}
