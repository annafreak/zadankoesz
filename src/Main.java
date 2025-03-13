import java.util.ArrayList;
import java.util.List;


class Equipment {
    private String name;
    private String serialNumber;
    private String manufacturer;
    private double purchasePrice;
    private int availableQuantity;
    private int warrantyMonths;

    public Equipment(String name, String serialNumber, String manufacturer, double purchasePrice, int availableQuantity, int warrantyMonths) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.manufacturer = manufacturer;
        this.purchasePrice = purchasePrice;
        this.availableQuantity = availableQuantity;
        this.warrantyMonths = warrantyMonths;
    }

    public void receiveEquipment(int quantity) {
        if (quantity > 0) {
            availableQuantity += quantity;
            System.out.println(quantity + " units of " + name + " received. Total available: " + availableQuantity);
        } else {
            System.out.println("Invalid quantity received.");
        }
    }

    public void issueEquipment(int quantity) {
        if (quantity > 0 && quantity <= availableQuantity) {
            availableQuantity -= quantity;
            System.out.println(quantity + " units of " + name + " issued. Remaining: " + availableQuantity);
        } else {
            System.out.println("Not enough stock available or invalid quantity.");
        }
    }

    public void displayInformation() {
        System.out.println("Equipment Information:");
        System.out.println("Name: " + name);
        System.out.println("Serial Number: " + serialNumber);
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Purchase Price: $" + purchasePrice);
        System.out.println("Available Quantity: " + availableQuantity);
        System.out.println("Warranty (months): " + warrantyMonths);
        System.out.println("------------------------------");
    }

    public boolean isUnderWarranty(int monthsSincePurchase) {
        return monthsSincePurchase <= warrantyMonths;
    }

    public String getName() {
        return name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
class Warehouse {
    private List<Equipment> inventory;

    public Warehouse() {
        this.inventory = new ArrayList<>();
    }

    public void addEquipment(Equipment equipment) {
        inventory.add(equipment);
        System.out.println("Added " + equipment.getName() + " to the warehouse.");
    }

    public Equipment searchBySerialNumber(String serialNumber) {
        for (Equipment equipment : inventory) {
            if (equipment.getSerialNumber().equalsIgnoreCase(serialNumber)) {
                return equipment;
            }
        }
        return null;
    }

    public Equipment searchByName(String name) {
        for (Equipment equipment : inventory) {
            if (equipment.getName().equalsIgnoreCase(name)) {
                return equipment;
            }
        }
        return null;
    }

    public void displayAllEquipment() {
        if (inventory.isEmpty()) {
            System.out.println("The warehouse is empty.");
        } else {
            for (Equipment equipment : inventory) {
                equipment.displayInformation();
            }
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        Equipment laptop = new Equipment("Laptop", "SN12345", "Dell", 3500.00, 10, 24);
        Equipment smartphone = new Equipment("Smartphone", "SN67890", "Samsung", 2500.00, 15, 12);
        Equipment printer = new Equipment("Printer", "SN11223", "HP", 1200.00, 5, 18);

        warehouse.addEquipment(laptop);
        warehouse.addEquipment(smartphone);
        warehouse.addEquipment(printer);

        System.out.println("\n--- Receiving Equipment ---");
        laptop.receiveEquipment(5);
        smartphone.receiveEquipment(3);

        System.out.println("\n--- Issuing Equipment ---");
        printer.issueEquipment(2);
        laptop.issueEquipment(12);

        System.out.println("\n--- Checking Warranty ---");
        System.out.println("Laptop under warranty: " + laptop.isUnderWarranty(12));
        System.out.println("Smartphone under warranty: " + smartphone.isUnderWarranty(14));

        System.out.println("\n--- Searching Equipment ---");
        Equipment foundEquipment = warehouse.searchBySerialNumber("SN67890");
        if (foundEquipment != null) {
            foundEquipment.displayInformation();
        } else {
            System.out.println("Equipment not found.");
        }

        System.out.println("\n--- Displaying All Equipment ---");
        warehouse.displayAllEquipment();
    }
}




