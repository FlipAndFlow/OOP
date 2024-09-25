package main.java.Vehicle;

// Polymorph
public abstract class Car {
    private String make;
    private String model;
    private double basePrice;

    public Car(String make, String model, double basePrice) {
        this.make = make;
        this.model = model;
        this.basePrice = basePrice;
    }

    // Getter methods
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public double getBasePrice() {
        return basePrice;
    }
}

