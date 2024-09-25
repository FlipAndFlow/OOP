package main.java.billing;

import main.java.Vehicle.CustomCar;
import main.java.accessories.CarAccessory;

import java.util.ArrayList;
import java.util.List;

public class Bill {
    private CustomCar customCar;
    private List<CarAccessory> accessories;
    private double totalPrice;

    public Bill(CustomCar customCar) {
        this.customCar = customCar;
        this.accessories = customCar.getAccessories(); // Assuming getAccessories() returns the list of accessories
        this.totalPrice = customCar.calculateTotalPrice();
    }

    public String getCarType() {
        return customCar.getModel(); // Assuming getCarType() returns the type of car
    }

    public List<CarAccessory> getAccessories() {
        return accessories;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getBillSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Car Type: ").append(getCarType()).append("\n");
        summary.append("Accessories:\n");
        for (CarAccessory accessory : accessories) {
            summary.append("- ").append(accessory.getName()).append(" ($").append(accessory.getPrice()).append(")\n");
        }
        summary.append("Total Price: $").append(totalPrice);
        return summary.toString();
    }
}
