package main.java.gui;

import main.java.billing.Bill;
import main.java.Vehicle.CustomCar;
import main.java.accessories.SpecificForToyota.*;
import main.java.accessories.SpecificForCivic.*;
import main.java.accessories.SpecificForFord.*;
import main.java.accessories.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CustomCarGUI {
    private CustomCar customCar;
    private JFrame frame;
    private DefaultListModel<String> selectedAccessoriesListModel;
    private JLabel totalPriceLabel;

    // Store accessories specific to each car
    private AlloyWheels standardWheels;
    private Spoiler standardSpoiler;
    private Sunroof standardSunroof;

    private SupraSpoiler spoiler;
    private CarbonHood hood;

    private CivicAlloyWheels alloyWheels;
    private CivicPerformanceEngine engine;
    private CivicBodyKit bodyKit;

    private MustangSunroof sunroof;
    private Mustang50Body bodykit;
    private WideBody wideBody;

    // Store history of custom cars
    private static List<String> historyList = new ArrayList<>(); // Make historyList static

    // Constructor with car type parameter
    public CustomCarGUI(String carType) {
        // Set the base price based on the car type
        double basePrice = 0;
        standardWheels = new AlloyWheels();
        standardSpoiler = new Spoiler();
        standardSunroof = new Sunroof();

        switch (carType) {
            case "Toyota Supra":
                basePrice = 30000;
                spoiler = new SupraSpoiler();
                hood = new CarbonHood();
                break;
            case "Honda Civic":
                basePrice = 20000;
                alloyWheels = new CivicAlloyWheels();
                engine = new CivicPerformanceEngine();
                bodyKit = new CivicBodyKit();
                break;
            case "Ford Mustang":
                basePrice = 40000;
                sunroof = new MustangSunroof();
                bodykit = new Mustang50Body();
                wideBody = new WideBody();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Unknown car type selected.");
                return;
        }

        customCar = new CustomCar(carType, carType, basePrice);

        frame = new JFrame("Custom Car - " + carType);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLayout(new BorderLayout());

        // Accessories selection panel
        JPanel accessoriesPanel = new JPanel();
        accessoriesPanel.setLayout(new BoxLayout(accessoriesPanel, BoxLayout.Y_AXIS));

        // Only show the relevant checkboxes based on the car type
        if ("Toyota Supra".equals(carType)) {
            createAccessoryCheckBox(accessoriesPanel, spoiler.getName(), spoiler.getPrice(), spoiler);
            createAccessoryCheckBox(accessoriesPanel, hood.getName(), hood.getPrice(), hood);
            createAccessoryCheckBox(accessoriesPanel, standardWheels.getName(), standardWheels.getPrice(),
                    standardWheels);
            createAccessoryCheckBox(accessoriesPanel, standardSpoiler.getName(), standardSpoiler.getPrice(),
                    standardSpoiler);
            createAccessoryCheckBox(accessoriesPanel, standardSunroof.getName(), standardSunroof.getPrice(),
                    standardSunroof);

        } else if ("Honda Civic".equals(carType)) {
            createAccessoryCheckBox(accessoriesPanel, alloyWheels.getName(), alloyWheels.getPrice(), alloyWheels);
            createAccessoryCheckBox(accessoriesPanel, engine.getName(), engine.getPrice(), engine);
            createAccessoryCheckBox(accessoriesPanel, bodyKit.getName(), bodyKit.getPrice(), bodyKit);
            createAccessoryCheckBox(accessoriesPanel, standardWheels.getName(), standardWheels.getPrice(),
                    standardWheels);
            createAccessoryCheckBox(accessoriesPanel, standardSpoiler.getName(), standardSpoiler.getPrice(),
                    standardSpoiler);
            createAccessoryCheckBox(accessoriesPanel, standardSunroof.getName(), standardSunroof.getPrice(),
                    standardSunroof);
        } else if ("Ford Mustang".equals(carType)) {
            createAccessoryCheckBox(accessoriesPanel, sunroof.getName(), sunroof.getPrice(), sunroof);
            createAccessoryCheckBox(accessoriesPanel, bodykit.getName(), bodykit.getPrice(), bodykit);
            createAccessoryCheckBox(accessoriesPanel, wideBody.getName(), wideBody.getPrice(), wideBody);
            createAccessoryCheckBox(accessoriesPanel, standardWheels.getName(), standardWheels.getPrice(),
                    standardWheels);
            createAccessoryCheckBox(accessoriesPanel, standardSpoiler.getName(), standardSpoiler.getPrice(),
                    standardSpoiler);
            createAccessoryCheckBox(accessoriesPanel, standardSunroof.getName(), standardSunroof.getPrice(),
                    standardSunroof);
        }

        // Wrap the accessories panel in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(accessoriesPanel);
        scrollPane.setPreferredSize(new Dimension(300, 0));

        // Selected accessories list
        selectedAccessoriesListModel = new DefaultListModel<>();
        JList<String> selectedAccessoriesList = new JList<>(selectedAccessoriesListModel);
        JScrollPane selectedListScrollPane = new JScrollPane(selectedAccessoriesList);

        // Price panel
        JPanel pricePanel = new JPanel();
        totalPriceLabel = new JLabel("Total Price: $" + customCar.calculateTotalPrice());
        pricePanel.add(totalPriceLabel);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(80, 30));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showMainMenu();
            }
        });

        // Checkout button
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setPreferredSize(new Dimension(100, 30));
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action for Checkout
                JOptionPane.showMessageDialog(frame, "Proceeding to checkout...");
                // Save the customization to history
                addHistory(carType, customCar.getAccessories(), customCar.calculateTotalPrice());
                // Add payment processing logic here
            }
        });

        // History button
        JButton historyButton = new JButton("History");
        historyButton.setPreferredSize(new Dimension(100, 30));
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action for showing history
                showHistory();
            }
        });

        // Create a new panel to hold the selected accessories list and accessories
        // checkboxes
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.EAST);
        mainPanel.add(selectedListScrollPane, BorderLayout.CENTER);

        // Create a new panel to hold both total price and buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Add buttons
        bottomPanel.add(backButton);
        bottomPanel.add(historyButton);
        bottomPanel.add(Box.createRigidArea(new Dimension(235, 0))); // Add space between buttons
        bottomPanel.add(pricePanel); // Assuming pricePanel is a JLabel or JPanel
        bottomPanel.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons
        bottomPanel.add(checkoutButton);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    // Method to create a checkbox for accessories
    private void createAccessoryCheckBox(JPanel panel, String accessoryName, double price, CarAccessory accessory) {
        JCheckBox checkBox = new JCheckBox(accessoryName + " ($" + price + ")");
        panel.add(checkBox);

        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected()) {
                    customCar.addAccessory(accessory);
                    selectedAccessoriesListModel.addElement(accessoryName + " ($" + price + ")");
                } else {
                    customCar.removeAccessory(accessory);
                    removeAccessory(accessoryName);
                }
                updateTotalPrice();
            }
        });
    }

    private void removeAccessory(String accessoryName) {
        for (int i = 0; i < selectedAccessoriesListModel.size(); i++) {
            if (selectedAccessoriesListModel.get(i).contains(accessoryName)) {
                selectedAccessoriesListModel.remove(i);
                break;
            }
        }
    }

    private void updateTotalPrice() {
        totalPriceLabel.setText("Total Price: $" + customCar.calculateTotalPrice());
    }

    private void showMainMenu() {
        SwingUtilities.invokeLater(() -> new CarSelectionGUI());
    }

    private void addHistory(String carType, List<CarAccessory> selectedAccessories, double totalPrice) {
        StringBuilder historyEntry = new StringBuilder();
        historyEntry.append("Car Type: ").append(carType).append(", ");
        historyEntry.append("Accessories: ");
        for (CarAccessory accessory : selectedAccessories) {
            historyEntry.append(accessory.getName()).append(", ");
        }
        historyEntry.append("Total Price: $").append(totalPrice);
        historyList.add(historyEntry.toString());
    }

    private void showHistory() {
        if (historyList.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No history available.");
            return;
        }
        new HistoryPage(historyList);
    }

    public static void main(String[] args) {
        String carType = JOptionPane.showInputDialog("Enter car type (Toyota Supra, Honda Civic, Ford Mustang):");
        new CustomCarGUI(carType);
    }
}
