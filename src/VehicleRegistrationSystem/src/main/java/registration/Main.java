package registration;

import repository.InMemoryVehicleRepository;
import repository.VehicleRepository;
import service.VehicleService;
import service.VehicleServiceImpl;
import ui.ConsoleApplication;

public class Main {

    public static void main(String[] args) {
        VehicleRepository repository = new InMemoryVehicleRepository();
        VehicleService service = new VehicleServiceImpl(repository);

        ConsoleApplication app = new ConsoleApplication(service);
        app.start();
    }
}