import java.util.*;

class ConfirmedReservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private String roomId;
    private boolean isCancelled;

    public ConfirmedReservation(String reservationId, String guestName, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
        this.isCancelled = false;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void cancel() {
        isCancelled = true;
    }

    public void display() {
        System.out.println("Reservation ID: " + reservationId +
                ", Guest: " + guestName +
                ", Room Type: " + roomType +
                ", Room ID: " + roomId +
                ", Cancelled: " + isCancelled);
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

    public void incrementRoom(String type) {
        inventory.put(type, inventory.get(type) + 1);
    }

    public void displayInventory() {
        System.out.println("Inventory Status:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}

class BookingHistory {
    private List<ConfirmedReservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    public void addReservation(ConfirmedReservation r) {
        history.add(r);
    }

    public ConfirmedReservation findReservation(String reservationId) {
        for (ConfirmedReservation r : history) {
            if (r.getReservationId().equals(reservationId)) {
                return r;
            }
        }
        return null;
    }

    public void displayHistory() {
        for (ConfirmedReservation r : history) {
            r.display();
        }
    }
}

class CancellationService {
    private Stack<String> rollbackRooms;

    public CancellationService() {
        rollbackRooms = new Stack<>();
    }

    public void cancelReservation(String reservationId, BookingHistory history, RoomInventory inventory) {
        ConfirmedReservation reservation = history.findReservation(reservationId);

        if (reservation == null) {
            System.out.println("Reservation not found.");
            return;
        }

        if (reservation.isCancelled()) {
            System.out.println("Reservation already cancelled.");
            return;
        }

        rollbackRooms.push(reservation.getRoomId());
        inventory.incrementRoom(reservation.getRoomType());
        reservation.cancel();

        System.out.println("Reservation " + reservationId + " cancelled successfully.");
        System.out.println("Room " + rollbackRooms.peek() + " rolled back to inventory.");
    }
}

public class App {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Standard Room", 2);

        BookingHistory history = new BookingHistory();
        history.addReservation(new ConfirmedReservation("R101", "Aishwarya", "Standard Room", "ST201"));
        history.addReservation(new ConfirmedReservation("R102", "Rahul", "Standard Room", "ST202"));

        CancellationService cancelService = new CancellationService();

        cancelService.cancelReservation("R101", history, inventory);

        history.displayHistory();
        inventory.displayInventory();
    }
}