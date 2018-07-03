package net.baumink.bzz.m326.ui.view.util;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * JTable Cell renderer for arbitrary Swing/AWT components. Fails silently by returning either .toString() or "null" in a JLabel
 *
 * @author Benjamin Raison, Jonas Gredig
 * @version 1.0
 */

public class ComponentCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value == null) return new JLabel("null");
        if (value instanceof Component) {
            return (Component) value;
        }
        return new JLabel(value.toString());
    }
}

