package Domain;

public class RoomValidator {

    /**
     * Validates a room.
     * @param room the room to validate.
     * @throws RuntimeException if there are validation errors.
     */

    public void validateCheckIn(Room room) {

        if(room.getDays() <= 0) {
            throw new RuntimeException("The number of days must be greater than 0!");
        }

    }

    public void validateCheckOut(Room room) {

        String feedback = room.getFeedback();
        if(feedback.length() == 0 || Character.compare(feedback.charAt(0), '\n') == 0) {
            throw new RuntimeException("The feedback must not be empty!");
        }
        if(room.isCheckOut() && (room.getRating() < 1 || room.getRating() > 5)) {
            throw new RuntimeException("Rating must be an integer from 1 to 5!");
        }
    }

}
