package net.baumink.bzz.m326.ui.view.util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
/**
 * @author Benjamin Raison, Jonas Gredig
 * @version 1.0
 */
public class DateCellRenderer extends DefaultTableCellRenderer {

    private SimpleDateFormat simpleDateFormat;
    private DateTimeFormatter dateTimeFormatter;

    public DateCellRenderer(String format) {
        simpleDateFormat = new SimpleDateFormat(format);
        dateTimeFormatter = DateTimeFormatter.ofPattern(format, Locale.getDefault());
    }

    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        if (value instanceof Date) {
            value = simpleDateFormat.format(value);
        }
        if (value instanceof ZonedDateTime) {
            value = ((ZonedDateTime) value).format(dateTimeFormatter);
        }
        return super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);
    }
}
