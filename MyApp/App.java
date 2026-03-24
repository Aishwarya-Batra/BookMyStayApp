import java.util.*;

class AddOnService {
    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

class AddOnServiceManager {
    private HashMap<String, List<AddOnService>> reservationServices;

    public AddOnServiceManager() {
        reservationServices = new HashMap<>();
    }

    public void addServiceToReservation(String reservationId, AddOnService service) {
        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {
        double total = 0;
        List<AddOnService> services = reservationServices.get(reservationId);
        if (services != null) {
            for (AddOnService s : services) {
                total += s.getCost();
            }
        }
        return total;
    }

    public void displayServices(String reservationId) {
        List<AddOnService> services = reservationServices.get(reservationId);
        if (services != null) {
            System.out.println("Add-On Services for Reservation " + reservationId + ":");
            for (AddOnService s : services) {
                System.out.println(s.getServiceName() + " - Cost: " + s.getCost());
            }
        } else {
            System.out.println("No services selected.");
        }
    }
}

public class App {
    public static void main(String[] args) {
        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "RS101";

        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService wifi = new AddOnService("WiFi", 200);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 1000);

        manager.addServiceToReservation(reservationId, breakfast);
        manager.addServiceToReservation(reservationId, wifi);
        manager.addServiceToReservation(reservationId, airportPickup);

        manager.displayServices(reservationId);

        double totalCost = manager.calculateTotalServiceCost(reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}