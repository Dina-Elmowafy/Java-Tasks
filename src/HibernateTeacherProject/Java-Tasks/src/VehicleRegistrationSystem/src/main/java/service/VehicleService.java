package service;


import java.util.List;

import model.Vehicle;

public interface VehicleService {

    void registerVehicle(Vehicle vehicle);

    Vehicle findByPlate(String plateNumber);

    void updateOwner(String plateNumber, String newOwner);

    void updateStatus(String plateNumber, String newStatus);

    void deleteVehicle(String plateNumber);

    List<Vehicle> getAllVehicles();

    List<Vehicle> filterByType(String type);

    List<Vehicle> getVehiclesByOwner(String ownerName);

    List<Vehicle> getExpiredRegistrations(int currentYear);

    List<Vehicle> getSortedByYear(boolean ascending);

    void printStatistics();
}