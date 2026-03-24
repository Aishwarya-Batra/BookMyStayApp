abstract class Room {
    String roomType;
    int availableRooms;
    double price;

    Room(String roomType, int availableRooms, double price) {
        this.roomType = roomType;
        this.availableRooms = availableRooms;
        this.price = price;
    }

    abstract void displayRoomDetails();
}

class StandardRoom extends Room {
    StandardRoom(int availableRooms, double price) {
        super("Standard Room", availableRooms, price);
    }

    void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println("Price per night: " + price);
        System.out.println("---------------------------");
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(int availableRooms, double price) {
        super("Deluxe Room", availableRooms, price);
    }

    void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println("Price per night: " + price);
        System.out.println("---------------------------");
    }
}

class SuiteRoom extends Room {
    SuiteRoom(int availableRooms, double price) {
        super("Suite Room", availableRooms, price);
    }

    void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println("Price per night: " + price);
        System.out.println("---------------------------");
    }
}

public class MyApp {
    public static void main(String[] args) {
        System.out.println("Welcome to BookMyStay App");
        System.out.println("Room Availability:\n");

        Room standard = new StandardRoom(5, 2000);
        Room deluxe = new DeluxeRoom(3, 3500);
        Room suite = new SuiteRoom(2, 5000);

        standard.displayRoomDetails();
        deluxe.displayRoomDetails();
        suite.displayRoomDetails();

        System.out.println("Application Terminated");
    }
}