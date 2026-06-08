package service;


import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import exception.DuplicatePlateException;
import exception.VehicleNotFoundException;
import model.Vehicle;
import repository.VehicleRepository;

public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository repository;

    public VehicleServiceImpl(VehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registerVehicle(Vehicle vehicle) {
        if (repository.existsByPlate(vehicle.getPlateNumber())) {
            throw new DuplicatePlateException(vehicle.getPlateNumber());
        }

        repository.save(vehicle);
    }

    @Override
    public Vehicle findByPlate(String plateNumber) {
        return repository.findByPlate(plateNumber)
                .orElseThrow(() -> new VehicleNotFoundException(plateNumber));
    }

    @Override
    public void updateOwner(String plateNumber, String newOwner) {
        Vehicle vehicle = findByPlate(plateNumber);
        vehicle.changeOwner(newOwner);
        repository.save(vehicle);
    }

    @Override
    public void updateStatus(String plateNumber, String newStatus) {
        Vehicle vehicle = findByPlate(plateNumber);
        vehicle.changeStatus(newStatus);
        repository.save(vehicle);
    }

    @Override
    public void deleteVehicle(String plateNumber) {
        findByPlate(plateNumber);
        repository.delete(plateNumber);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return repository.findAll();
    }

    @Override
    public List<Vehicle> filterByType(String type) {
        return repository.findAll()
                .stream()
                .filter(vehicle -> vehicle.getVehicleType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> getVehiclesByOwner(String ownerName) {
        return repository.findAll()
                .stream()
                .filter(vehicle -> vehicle.getOwnerName().toLowerCase().contains(ownerName.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> getExpiredRegistrations(int currentYear) {
        return repository.findAll()
                .stream()
                .filter(vehicle -> currentYear - vehicle.getRegistrationYear() > 5)
                .sorted(Comparator.comparingInt(Vehicle::getRegistrationYear))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> getSortedByYear(boolean ascending) {
        Comparator<Vehicle> comparator = Comparator.comparingInt(Vehicle::getRegistrationYear);

        if (!ascending) {
            comparator = comparator.reversed();
        }

        return repository.findAll()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public void printStatistics() {
        List<Vehicle> vehicles = repository.findAll();

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        IntSummaryStatistics stats = vehicles.stream()
                .mapToInt(Vehicle::getRegistrationYear)
                .summaryStatistics();

        Map<String, Long> vehiclesByType = vehicles.stream()
                .collect(Collectors.groupingBy(Vehicle::getVehicleType, Collectors.counting()));

        Map<Boolean, Long> vehiclesByStatus = vehicles.stream()
                .collect(Collectors.partitioningBy(
                        vehicle -> vehicle.getStatus().equalsIgnoreCase("ACTIVE"),
                        Collectors.counting()
                ));

        System.out.println("========== REGISTRATION STATISTICS ==========");
        System.out.println("Total Vehicles : " + stats.getCount());
        System.out.printf("Average Year   : %.0f%n", stats.getAverage());
        System.out.println("Newest Vehicle : " + stats.getMax());
        System.out.println("Oldest Vehicle : " + stats.getMin());
        System.out.println("----------------------------------------------");
        System.out.println("Vehicles by Type:");

        vehiclesByType.forEach((type, count) ->
                System.out.println(" " + type + " : " + count)
        );

        System.out.println("----------------------------------------------");
        System.out.println("ACTIVE vehicles : " + vehiclesByStatus.get(true));
        System.out.println("EXPIRED vehicles: " + vehiclesByStatus.get(false));
        System.out.println("==============================================");
    }
}