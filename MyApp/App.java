import java.io.*;
import java.util.*;

class ConfirmedReservation implements Serializable {
    private String reservationId;
    private String guestName;
    private String roomType;

    public ConfirmedReservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println(reservationId + " - " + guestName + " - " + roomType);
    }
}

class RoomInventory implements Serializable {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoom(String type, int count) {
        inventory.put(type, count);
    }

    public void displayInventory() {
        System.out.println("Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}

class BookingHistory implements Serializable {
    private List<ConfirmedReservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    public void addReservation(ConfirmedReservation r) {
        history.add(r);
    }

    public void displayHistory() {
        System.out.println("Booking History:");
        for (ConfirmedReservation r : history) {
            r.display();
        }
    }
}

class PersistenceService {
    public void saveSystemState(RoomInventory inventory, BookingHistory history) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("system_state.dat"));
            out.writeObject(inventory);
            out.writeObject(history);
            out.close();
            System.out.println("System state saved.");
        } catch (IOException e) {
            System.out.println("Error saving system state.");
        }
    }

    public Object[] loadSystemState() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("system_state.dat"));
            RoomInventory inventory = (RoomInventory) in.readObject();
            BookingHistory history = (BookingHistory) in.readObject();
            in.close();
            System.out.println("System state loaded.");
            return new Object[]{inventory, history};
        } catch (Exception e) {
            System.out.println("No previous system state found.");
            return null;
        }
    }
}

public class App {
    public static void main(String[] args) {
        PersistenceService persistence = new PersistenceService();

        Object[] state = persistence.loadSystemState();

        RoomInventory inventory;
        BookingHistory history;

        if (state != null) {
            inventory = (RoomInventory) state[0];
            history = (BookingHistory) state[1];
        } else {
            inventory = new RoomInventory();
            history = new BookingHistory();

            inventory.addRoom("Standard Room", 5);
            inventory.addRoom("Deluxe Room", 3);

            history.addReservation(new ConfirmedReservation("R101", "Aishwarya", "Standard Room"));
        }

        inventory.displayInventory();
        history.displayHistory();

        persistence.saveSystemState(inventory, history);
    }
}