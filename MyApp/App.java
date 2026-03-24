import java.util.HashMap;

abstract class Room {
    String roomType;
    double price;

    Room(String roomType, double price) {
        this.roomType = roomType;
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    abstract void displayRoomDetails();
}

class StandardRoom extends Room {
    StandardRoom() {
        super("Standard Room", 2000);
    }

    void displayRoomDetails() {
        System.out.println(roomType + " - Price: " + price);
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom() {
        super("Deluxe Room", 3500);
    }

    void displayRoomDetails() {
        System.out.println(roomType + " - Price: " + price);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 5000);
    }

    void displayRoomDetails() {
        System.out.println(roomType + " - Price: " + price);
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoom(String type, int count) {
        inventory.put(type, count);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public HashMap<String, Integer> getInventory() {
        return inventory;
    }
}

class SearchService {
    public void displayAvailableRooms(RoomInventory inventory, Room[] rooms) {
        System.out.println("Available Rooms:");

        for (Room room : rooms) {
            int available = inventory.getAvailability(room.getRoomType());
            if (available > 0) {
                room.displayRoomDetails();
                System.out.println("Available Count: " + available);
                System.out.println("----------------------");
            }
        }
    }
}

public class App {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();

        inventory.addRoom("Standard Room", 5);
        inventory.addRoom("Deluxe Room", 0);
        inventory.addRoom("Suite Room", 2);

        Room[] rooms = {
                new StandardRoom(),
                new DeluxeRoom(),
                new SuiteRoom()
        };

        SearchService searchService = new SearchService();
        searchService.displayAvailableRooms(inventory, rooms);
    }
}