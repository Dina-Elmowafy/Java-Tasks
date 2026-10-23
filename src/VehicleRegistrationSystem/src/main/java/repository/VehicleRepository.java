package repository;

import java.util.List;
import java.util.Optional;

import model.Vehicle;

public interface VehicleRepository {

    void save(Vehicle vehicle);

    Optional<Vehicle> findByPlate(String plateNumber);

    void delete(String plateNumber);

    boolean existsByPlate(String plateNumber);

    List<Vehicle> findAll();
}
