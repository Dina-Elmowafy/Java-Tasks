package repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import model.Vehicle;

public class InMemoryVehicleRepository implements VehicleRepository {

    private final Map<String, Vehicle> vehicles = new HashMap<>();

    @Override
    public void save(Vehicle vehicle) {
        vehicles.put(normalize(vehicle.getPlateNumber()), vehicle);
    }

    @Override
    public Optional<Vehicle> findByPlate(String plateNumber) {
        return Optional.ofNullable(vehicles.get(normalize(plateNumber)));
    }

    @Override
    public void delete(String plateNumber) {
        vehicles.remove(normalize(plateNumber));
    }

    @Override
    public boolean existsByPlate(String plateNumber) {
        return vehicles.containsKey(normalize(plateNumber));
    }

    @Override
    public List<Vehicle> findAll() {
        return new ArrayList<>(vehicles.values());
    }

    private String normalize(String plateNumber) {
        return plateNumber.toUpperCase();
    }
}