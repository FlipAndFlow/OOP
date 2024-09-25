package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HistoryPage {
    private JFrame frame;
    private List<String> historyList;
    private JTextArea historyArea;

    public HistoryPage(List<String> historyList) {
        this.historyList = historyList;
        frame = new JFrame("Customization History");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setText(buildHistoryText());

        JScrollPane scrollPane = new JScrollPane(historyArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.dispose());

        JButton clearButton = new JButton("Clear History");
        clearButton.addActionListener(e -> clearHistory());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(clearButton); // เพิ่มปุ่มล้างประวัติ
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private String buildHistoryText() {
        if (historyList.isEmpty()) {
            return "No history available.";
        }

        StringBuilder historyBuilder = new StringBuilder();
        for (String historyEntry : historyList) {
            historyBuilder.append(historyEntry).append("\n\n");
        }
        return historyBuilder.toString();
    }

    private void clearHistory() {
        historyList.clear(); // ล้างประวัติ
        historyArea.setText(buildHistoryText()); // อัปเดต JTextArea
        JOptionPane.showMessageDialog(frame, "History cleared successfully."); // แจ้งผู้ใช้
    }
}
