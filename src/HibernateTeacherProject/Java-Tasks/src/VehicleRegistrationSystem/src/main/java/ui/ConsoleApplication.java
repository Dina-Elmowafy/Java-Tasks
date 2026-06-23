package ui;


import java.time.Year;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import exception.DuplicatePlateException;
import exception.InvalidInputException;
import exception.VehicleNotFoundException;
import factory.VehicleFactory;
import model.Car;
import model.Motorcycle;
import model.Truck;
import model.Vehicle;
import service.VehicleService;
import validation.InputValidator;

public class ConsoleApplication {

    private final VehicleService vehicleService;
    private final VehicleFactory vehicleFactory;
    private final InputValidator inputValidator;
    private final MenuPrinter menuPrinter;
    private final Scanner scanner;

    public ConsoleApplication(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
        this.vehicleFactory = new VehicleFactory();
        this.inputValidator = new InputValidator();
        this.menuPrinter = new MenuPrinter();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        loadSampleData();

        boolean running = true;

        while (running) {
            menuPrinter.printMainMenu();

            try {
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                running = handleChoice(choice);

            } catch (InputMismatchException exception) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();

            } catch (DuplicatePlateException | VehicleNotFoundException exception) {
                System.out.println("Error: " + exception.getMessage());

            } catch (InvalidInputException exception) {
                System.out.println("Input Error: " + exception.getMessage());

            } catch (NumberFormatException exception) {
                System.out.println("Input Error: Invalid extra value for this vehicle type.");
            }

            System.out.println();
        }
    }

    private boolean handleChoice(int choice) {
        switch (choice) {
            case 1:
                registerNewVehicle();
                return true;

            case 2:
                searchVehicleByPlate();
                return true;

            case 3:
                updateOwnerName();
                return true;

            case 4:
                deleteVehicle();
                return true;

            case 5:
                listAllVehicles();
                return true;

            case 6:
                filterByVehicleType();
                return true;

            case 7:
                showOwnerHistory();
                return true;

            case 8:
                showExpiredRegistrations();
                return true;

            case 9:
                vehicleService.printStatistics();
                return true;

            case 0:
                System.out.println("Goodbye!");
                return false;

            default:
                System.out.println("Invalid choice. Please try again.");
                return true;
        }
    }

    private void loadSampleData() {
        vehicleService.registerVehicle(new Car("CAR-001", "Ahmed Ali", 2019, "EXPIRED", 4));
        vehicleService.registerVehicle(new Car("CAR-002", "Sara Kamel", 2023, "ACTIVE", 2));
        vehicleService.registerVehicle(new Truck("TRK-001", "Mohamed Said", 2021, "ACTIVE", 10.0));
        vehicleService.registerVehicle(new Truck("TRK-002", "Laila Nour", 2017, "EXPIRED", 5.5));
        vehicleService.registerVehicle(new Motorcycle("MOT-001", "Omar Fathi", 2022, "ACTIVE", "Sport"));
        vehicleService.registerVehicle(new Motorcycle("MOT-002", "Nadia Hamed", 2024, "ACTIVE", "Cruiser"));
    }

    private void registerNewVehicle() {
        System.out.print("Enter plate number: ");
        String plateNumber = scanner.nextLine();

        System.out.print("Enter owner name: ");
        String ownerName = scanner.nextLine();

        System.out.print("Enter vehicle type (Car / Truck / Motorcycle): ");
        String vehicleType = scanner.nextLine();

        System.out.print("Enter registration year: ");
        int registrationYear = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter status (ACTIVE / EXPIRED): ");
        String status = scanner.nextLine();

        inputValidator.validatePlateNumber(plateNumber);
        inputValidator.validateOwnerName(ownerName);
        inputValidator.validateVehicleType(vehicleType);
        inputValidator.validateRegistrationYear(registrationYear);
        inputValidator.validateStatus(status);

        System.out.print(getExtraValueMessage(vehicleType));
        String extraValue = scanner.nextLine();

        Vehicle vehicle = vehicleFactory.createVehicle(
                vehicleType,
                plateNumber,
                ownerName,
                registrationYear,
                status,
                extraValue
        );

        vehicleService.registerVehicle(vehicle);
        System.out.println("Vehicle registered successfully.");
    }

    private String getExtraValueMessage(String vehicleType) {
        if (vehicleType.equalsIgnoreCase("Car")) {
            return "Enter number of doors: ";
        }

        if (vehicleType.equalsIgnoreCase("Truck")) {
            return "Enter cargo capacity in tons: ";
        }

        return "Enter engine type: ";
    }

    private void searchVehicleByPlate() {
        String plateNumber = readPlateNumber();

        Vehicle vehicle = vehicleService.findByPlate(plateNumber);

        System.out.println(vehicle);
        System.out.println(vehicle.getRegistrationLabel());
    }

    private void updateOwnerName() {
        String plateNumber = readPlateNumber();

        System.out.print("Enter new owner name: ");
        String newOwnerName = scanner.nextLine();

        inputValidator.validateOwnerName(newOwnerName);

        vehicleService.updateOwner(plateNumber, newOwnerName);
        System.out.println("Owner updated successfully.");
    }

    private void deleteVehicle() {
        String plateNumber = readPlateNumber();

        vehicleService.deleteVehicle(plateNumber);
        System.out.println("Vehicle deleted successfully.");
    }

    private void listAllVehicles() {
        printVehicles(vehicleService.getAllVehicles());
    }

    private void filterByVehicleType() {
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine();

        inputValidator.validateVehicleType(vehicleType);

        printVehicles(vehicleService.filterByType(vehicleType));
    }

    private void showOwnerHistory() {
        System.out.print("Enter owner name: ");
        String ownerName = scanner.nextLine();

        printVehicles(vehicleService.getVehiclesByOwner(ownerName));
    }

    private void showExpiredRegistrations() {
        int currentYear = Year.now().getValue();

        printVehicles(vehicleService.getExpiredRegistrations(currentYear));
    }

    private String readPlateNumber() {
        System.out.print("Enter plate number: ");
        String plateNumber = scanner.nextLine();

        inputValidator.validatePlateNumber(plateNumber);

        return plateNumber;
    }

    private void printVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        vehicles.forEach(System.out::println);
    }
}
