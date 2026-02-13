package ParkingSystem;

import java.util.*;

class ParkingLot {

    static int totalVehiclesParked = 0;
    static int maxSlots = 4;

    Map<String, Vehicle> parkedVehicles = new HashMap<>();
    Queue<Vehicle> waitingQueue = new LinkedList<>();

    public void parkVehicle(Vehicle v) {

        System.out.println("\nREQUEST: PARK VEHICLE → " + v.plateNumber);

        if (parkedVehicles.size() < maxSlots) {
            parkedVehicles.put(v.plateNumber, v);
            totalVehiclesParked++;

            System.out.println("STATUS : SUCCESS");
            System.out.println("MESSAGE: VEHICLE " + v.plateNumber + " PARKED.");
        } else {
            waitingQueue.add(v);

            System.out.println("STATUS : LOT FULL");
            System.out.println("MESSAGE: VEHICLE " + v.plateNumber + " ADDED TO WAITING QUEUE.");
        }
    }

    public void displayLotStatus() {

        System.out.println("\n===== PARKING LOT STATUS =====");
        System.out.println("TOTAL VEHICLES PARKED : " + totalVehiclesParked);
        System.out.println("CURRENTLY PARKED      : " + parkedVehicles.size());
        System.out.println("WAITING QUEUE SIZE    : " + waitingQueue.size());
    }

    public void getMostCommonVehicleType() {

        System.out.println("\n===== VEHICLE ANALYTICS =====");

        if (parkedVehicles.isEmpty()) {
            System.out.println("MESSAGE: NO VEHICLES PARKED.");
            return;
        }

        Map<VehicleType, Integer> typeCount = new HashMap<>();

        for (Vehicle v : parkedVehicles.values()) {
            typeCount.put(v.vehicleType,
                    typeCount.getOrDefault(v.vehicleType, 0) + 1);
        }

        VehicleType mostCommonType = null;
        int maxCount = 0;

        for (Map.Entry<VehicleType, Integer> entry : typeCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostCommonType = entry.getKey();
            }
        }

        System.out.println("MOST COMMON VEHICLE TYPE : " + mostCommonType);
        System.out.println("COUNT                    : " + maxCount);
    }
}

enum VehicleType {
    CAR, BIKE, TRUCK
}

class Vehicle {

    String plateNumber;
    String ownerName;
    VehicleType vehicleType;

    Vehicle(String plateNumber, String ownerName) {
        this.plateNumber = plateNumber;
        this.ownerName = ownerName;
    }

    Vehicle(String plateNumber, String ownerName, VehicleType type) {
        this(plateNumber, ownerName);
        this.vehicleType = type;
    }
}

public class ParkingSystem {

    public static void main(String[] args) {

        System.out.println("===== SMART PARKING MANAGEMENT SYSTEM =====");

        ParkingLot lot = new ParkingLot();

        Vehicle v1 = new Vehicle("ABC123", "John Doe", VehicleType.CAR);
        Vehicle v2 = new Vehicle("XYZ789", "Jane Smith", VehicleType.BIKE);
        Vehicle v3 = new Vehicle("LMN456", "Alice Johnson", VehicleType.TRUCK);
        Vehicle v4 = new Vehicle("DEF321", "Bob Brown", VehicleType.CAR);
        Vehicle v5 = new Vehicle("GHI654", "Charlie Davis", VehicleType.CAR);

        lot.parkVehicle(v1);
        lot.parkVehicle(v2);
        lot.parkVehicle(v3);
        lot.parkVehicle(v4);
        lot.parkVehicle(v5);

        lot.displayLotStatus();
        lot.getMostCommonVehicleType();
    }
}
