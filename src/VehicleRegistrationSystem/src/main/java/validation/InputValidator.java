package validation;


import java.time.Year;

import exception.InvalidInputException;

public class InputValidator {

    public void validatePlateNumber(String plateNumber) {
        if (plateNumber == null || plateNumber.trim().isEmpty()) {
            throw new InvalidInputException("Plate number is required");
        }

        if (!plateNumber.toUpperCase().matches("[A-Z0-9-]{3,10}")) {
            throw new InvalidInputException("Plate number must be 3-10 alphanumeric characters");
        }
    }

    public void validateOwnerName(String ownerName) {
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new InvalidInputException("Owner name is required");
        }

        if (ownerName.trim().length() < 3) {
            throw new InvalidInputException("Owner name must be at least 3 characters");
        }

        if (!ownerName.matches("[a-zA-Z ]+")) {
            throw new InvalidInputException("Owner name must contain only letters");
        }
    }

    public void validateVehicleType(String vehicleType) {
        if (vehicleType == null ||
                !(vehicleType.equalsIgnoreCase("Car")
                        || vehicleType.equalsIgnoreCase("Truck")
                        || vehicleType.equalsIgnoreCase("Motorcycle"))) {
            throw new InvalidInputException("Invalid type. Use: Car, Truck, Motorcycle");
        }
    }

    public void validateRegistrationYear(int year) {
        int currentYear = Year.now().getValue();

        if (year < 1990 || year > currentYear) {
            throw new InvalidInputException("Year must be between 1990 and " + currentYear);
        }
    }

    public void validateStatus(String status) {
        if (status == null ||
                !(status.equalsIgnoreCase("ACTIVE") || status.equalsIgnoreCase("EXPIRED"))) {
            throw new InvalidInputException("Status must be ACTIVE or EXPIRED");
        }
    }
}