import java.util.*;

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
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

    public void decrementRoom(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }

    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " Available: " + inventory.get(type));
        }
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation r) {
        queue.add(r);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

class BookingService {
    private RoomInventory inventory;
    private Set<String> assignedRoomIds;
    private int roomIdCounter = 100;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        assignedRoomIds = new HashSet<>();
    }

    public void processBooking(Reservation reservation) {
        String roomType = reservation.getRoomType();

        if (inventory.getAvailability(roomType) > 0) {
            String roomId = generateRoomId(roomType);
            assignedRoomIds.add(roomId);
            inventory.decrementRoom(roomType);

            System.out.println("Reservation Confirmed for " + reservation.getGuestName());
            System.out.println("Room Type: " + roomType);
            System.out.println("Assigned Room ID: " + roomId);
            System.out.println("---------------------------");
        } else {
            System.out.println("No rooms available for " + roomType + " for " + reservation.getGuestName());
        }
    }

    private String generateRoomId(String roomType) {
        roomIdCounter++;
        return roomType.substring(0, 2).toUpperCase() + roomIdCounter;
    }
}

public class App {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Standard Room", 2);
        inventory.addRoom("Deluxe Room", 1);
        inventory.addRoom("Suite Room", 1);

        BookingRequestQueue queue = new BookingRequestQueue();
        queue.addRequest(new Reservation("Aishwarya", "Standard Room"));
        queue.addRequest(new Reservation("Rahul", "Standard Room"));
        queue.addRequest(new Reservation("Sneha", "Standard Room"));
        queue.addRequest(new Reservation("Arjun", "Suite Room"));

        BookingService bookingService = new BookingService(inventory);

        while (!queue.isEmpty()) {
            Reservation r = queue.getNextRequest();
            bookingService.processBooking(r);
        }

        inventory.displayInventory();
    }
}