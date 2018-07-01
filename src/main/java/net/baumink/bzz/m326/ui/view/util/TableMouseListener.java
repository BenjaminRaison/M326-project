package net.baumink.bzz.m326.ui.view.util;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.ObjIntConsumer;

public class TableMouseListener extends MouseAdapter {

    private final JTable table;
    private final int targetColumn;
    private final ObjIntConsumer<MouseEvent> consumer;

    public TableMouseListener(JTable table, int targetColumn, ObjIntConsumer<MouseEvent> consumer) {
        this.table = table;
        this.targetColumn = targetColumn;
        this.consumer = consumer;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int column = table.getColumnModel().getColumnIndexAtX(mouseEvent.getX());
        if (column != targetColumn) return;
        int row = mouseEvent.getY() / table.getRowHeight();
        if (row >= table.getRowCount()) return;

        ((JButton) table.getValueAt(row, column)).doClick();
        consumer.accept(mouseEvent, row);
    }
}
