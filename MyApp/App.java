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

    public synchronized boolean allocateRoom(String roomType) {
        int available = inventory.getOrDefault(roomType, 0);
        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        }
        return false;
    }

    public void addRoom(String type, int count) {
        inventory.put(type, count);
    }

    public void displayInventory() {
        System.out.println("Final Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public synchronized void addRequest(Reservation r) {
        queue.add(r);
    }

    public synchronized Reservation getRequest() {
        return queue.poll();
    }
}

class BookingProcessor extends Thread {
    private BookingRequestQueue queue;
    private RoomInventory inventory;

    public BookingProcessor(BookingRequestQueue queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {
        while (true) {
            Reservation r;
            synchronized (queue) {
                r = queue.getRequest();
            }

            if (r == null) {
                break;
            }

            boolean allocated = inventory.allocateRoom(r.getRoomType());

            if (allocated) {
                System.out.println(r.getGuestName() + " booking confirmed for " + r.getRoomType());
            } else {
                System.out.println(r.getGuestName() + " booking failed for " + r.getRoomType());
            }
        }
    }
}

public class App {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Standard Room", 2);

        BookingRequestQueue queue = new BookingRequestQueue();
        queue.addRequest(new Reservation("Aishwarya", "Standard Room"));
        queue.addRequest(new Reservation("Rahul", "Standard Room"));
        queue.addRequest(new Reservation("Sneha", "Standard Room"));
        queue.addRequest(new Reservation("Arjun", "Standard Room"));

        BookingProcessor t1 = new BookingProcessor(queue, inventory);
        BookingProcessor t2 = new BookingProcessor(queue, inventory);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        inventory.displayInventory();
    }
}