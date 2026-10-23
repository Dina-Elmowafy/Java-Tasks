package factory;


import exception.InvalidInputException;
import model.Car;
import model.Motorcycle;
import model.Truck;
import model.Vehicle;

public class VehicleFactory {

    public Vehicle createCar(String plateNumber, String ownerName, int registrationYear, String status, int numberOfDoors) {
        return new Car(plateNumber, ownerName, registrationYear, status, numberOfDoors);
    }

    public Vehicle createTruck(String plateNumber, String ownerName, int registrationYear, String status, double cargoCapacityTons) {
        return new Truck(plateNumber, ownerName, registrationYear, status, cargoCapacityTons);
    }

    public Vehicle createMotorcycle(String plateNumber, String ownerName, int registrationYear, String status, String engineType) {
        return new Motorcycle(plateNumber, ownerName, registrationYear, status, engineType);
    }

    public Vehicle createVehicle(String type, String plateNumber, String ownerName, int registrationYear, String status, String extraValue) {
        if (type.equalsIgnoreCase("Car")) {
            int doors = Integer.parseInt(extraValue);
            return createCar(plateNumber, ownerName, registrationYear, status, doors);
        }

        if (type.equalsIgnoreCase("Truck")) {
            double cargo = Double.parseDouble(extraValue);
            return createTruck(plateNumber, ownerName, registrationYear, status, cargo);
        }

        if (type.equalsIgnoreCase("Motorcycle")) {
            return createMotorcycle(plateNumber, ownerName, registrationYear, status, extraValue);
        }

        throw new InvalidInputException("Invalid type. Use: Car, Truck, Motorcycle");
    }
}