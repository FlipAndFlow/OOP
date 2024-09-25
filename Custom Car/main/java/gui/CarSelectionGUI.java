package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarSelectionGUI {
    private JFrame frame;
    private JComboBox<String> carTypeComboBox;

    public CarSelectionGUI() {
        frame = new JFrame("Select Car Type");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        String[] carTypes = {"Toyota Supra", "Honda Civic", "Ford Mustang"};
        carTypeComboBox = new JComboBox<>(carTypes);
        JButton proceedButton = new JButton("Proceed");

        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCarType = (String) carTypeComboBox.getSelectedItem();
                // Pass the selected car type to the CustomCarGUI
                new CustomCarGUI(selectedCarType);
                frame.dispose(); // Close the selection frame
            }
        });

        frame.add(new JLabel("Select Car Type:"));
        frame.add(carTypeComboBox);
        frame.add(proceedButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CarSelectionGUI());
    }
}