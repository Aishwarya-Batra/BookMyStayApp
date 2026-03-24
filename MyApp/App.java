import java.util.*;

class ConfirmedReservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public ConfirmedReservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayReservation() {
        System.out.println("Reservation ID: " + reservationId +
                ", Guest: " + guestName +
                ", Room Type: " + roomType);
    }
}

class BookingHistory {
    private List<ConfirmedReservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    public void addReservation(ConfirmedReservation reservation) {
        history.add(reservation);
    }

    public List<ConfirmedReservation> getHistory() {
        return history;
    }
}

class BookingReportService {
    public void displayAllBookings(BookingHistory history) {
        System.out.println("Booking History:");
        for (ConfirmedReservation r : history.getHistory()) {
            r.displayReservation();
        }
    }

    public void generateRoomTypeReport(BookingHistory history) {
        HashMap<String, Integer> report = new HashMap<>();

        for (ConfirmedReservation r : history.getHistory()) {
            String type = r.getRoomType();
            report.put(type, report.getOrDefault(type, 0) + 1);
        }

        System.out.println("\nRoom Type Booking Report:");
        for (String type : report.keySet()) {
            System.out.println(type + ": " + report.get(type) + " bookings");
        }
    }
}

public class App {
    public static void main(String[] args) {
        BookingHistory history = new BookingHistory();

        history.addReservation(new ConfirmedReservation("R101", "Aishwarya", "Standard Room"));
        history.addReservation(new ConfirmedReservation("R102", "Rahul", "Deluxe Room"));
        history.addReservation(new ConfirmedReservation("R103", "Sneha", "Standard Room"));

        BookingReportService reportService = new BookingReportService();

        reportService.displayAllBookings(history);
        reportService.generateRoomTypeReport(history);
    }
}