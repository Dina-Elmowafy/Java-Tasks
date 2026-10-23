package model;
import java.util.Objects;

public abstract class Vehicle {

    private final String plateNumber;
    private String ownerName;
    private final String vehicleType;
    private final int registrationYear;
    private String status;

    public Vehicle(String plateNumber, String ownerName, String vehicleType, int registrationYear, String status) {
        this.plateNumber = plateNumber.toUpperCase();
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
        this.registrationYear = registrationYear;
        this.status = status.toUpperCase();
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public String getStatus() {
        return status;
    }

    public void changeOwner(String newOwnerName) {
        this.ownerName = newOwnerName;
    }

    public void changeStatus(String newStatus) {
        this.status = newStatus.toUpperCase();
    }

    public abstract String getRegistrationLabel();

    @Override
    public String toString() {
        return "[" + plateNumber + "] | " + vehicleType
                + " | Owner: " + ownerName
                + " | Year: " + registrationYear
                + " | Status: " + status;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Vehicle vehicle = (Vehicle) object;
        return plateNumber.equalsIgnoreCase(vehicle.plateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNumber.toLowerCase());
    }
}
