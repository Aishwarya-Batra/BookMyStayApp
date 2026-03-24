import java.util.LinkedList;
import java.util.Queue;

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

    public void displayReservation() {
        System.out.println("Guest: " + guestName + ", Room Type: " + roomType);
    }
}

class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName());
    }

    public void displayRequests() {
        System.out.println("\nBooking Requests in Queue (FCFS Order):");
        for (Reservation r : requestQueue) {
            r.displayReservation();
        }
    }
}

public class App {
    public static void main(String[] args) {
        BookingRequestQueue queue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Aishwarya", "Standard Room");
        Reservation r2 = new Reservation("Rahul", "Deluxe Room");
        Reservation r3 = new Reservation("Sneha", "Suite Room");

        queue.addRequest(r1);
        queue.addRequest(r2);
        queue.addRequest(r3);

        queue.displayRequests();
    }
}