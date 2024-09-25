package main.java.Vehicle;

import main.java.accessories.CarAccessory;
import java.util.ArrayList;
import java.util.List;

public class CustomCar extends Car {
    private ArrayList<CarAccessory> accessories;

    public CustomCar(String make, String model, double basePrice) {
        super(make, model, basePrice);
        accessories = new ArrayList<>();
    }

    public void addAccessory(CarAccessory accessory) {
        accessories.add(accessory);
    }

    public void removeAccessory(CarAccessory accessory) {
        accessories.remove(accessory);
    }

    public double calculateTotalPrice() {
        double totalPrice = getBasePrice();
        for (CarAccessory accessory : accessories) {
            totalPrice += accessory.getPrice();
        }
        return totalPrice;
    }

    public ArrayList<CarAccessory> getAccessories() {
        return accessories;
    }
    
        public List<String> getAccessoriesName() {
        List<String> accessoryNames = new ArrayList<>();
        for (CarAccessory accessory : accessories) {
            accessoryNames.add(accessory.getName() + " ($" + accessory.getPrice() + ")");
        }
        return accessoryNames;
    }

    public void clearAccessories() {
        accessories.clear();
    }
}
