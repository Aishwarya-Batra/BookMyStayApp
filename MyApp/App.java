import java.util.HashMap;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
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

    public boolean roomExists(String type) {
        return inventory.containsKey(type);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

class InvalidBookingValidator {
    public void validate(String guestName, String roomType, RoomInventory inventory) throws InvalidBookingException {
        if (guestName == null || guestName.isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!inventory.roomExists(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        if (inventory.getAvailability(roomType) <= 0) {
            throw new InvalidBookingException("Selected room type is not available.");
        }
    }
}

public class App {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Standard Room", 2);
        inventory.addRoom("Deluxe Room", 0);

        InvalidBookingValidator validator = new InvalidBookingValidator();

        String guestName = "Aishwarya";
        String roomType = "Deluxe Room";

        try {
            validator.validate(guestName, roomType, inventory);
            System.out.println("Booking input is valid. Proceed with booking.");
        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }

        System.out.println("System continues running...");
    }
}