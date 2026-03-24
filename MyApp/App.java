import java.util.HashMap;

class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " Available Rooms: " + inventory.get(roomType));
        }
    }
}

public class App {
    public static void main(String[] args) {
        System.out.println("BookMyStay Inventory System");

        RoomInventory inventory = new RoomInventory();

        inventory.addRoomType("Standard Room", 5);
        inventory.addRoomType("Deluxe Room", 3);
        inventory.addRoomType("Suite Room", 2);

        inventory.displayInventory();

        System.out.println("\nUpdating Deluxe Room availability...\n");

        inventory.updateAvailability("Deluxe Room", 4);

        inventory.displayInventory();
    }
}