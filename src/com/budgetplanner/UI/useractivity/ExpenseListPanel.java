package com.budgetplanner.UI.useractivity;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ExpenseListPanel extends JPanel {

    List<String[]> list;

    public ExpenseListPanel(List<String[]> list) {
        this.list = list;
        setLayout(new BorderLayout());
        String[] columns = {"Id", "Date", "Name", "Description", "Price", "Payment Method"};
        if (list == null) {
            JOptionPane.showMessageDialog(getParent(), "No data available");
            return;
        }
        String[][] data = this.list.stream().toArray(String[][]::new);
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane, BorderLayout.CENTER);
    }

}
