package Domain;

public class Room {

    private int id;
    private int number;
    private int capacity;
    private int days;
    private boolean checkOut;
    private String feedback;
    private int rating;

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", capacity=" + capacity +
                ", days=" + days +
                ", checkOut=" + checkOut +
                ", feedback='" + feedback + '\'' +
                ", rating=" + rating +
                '}';
    }

    public Room(int id, int number, int capacity, int days) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
        this.days = days;
        checkOut = false;
    }

    public Room(int id, int number, int capacity, int days, boolean checkOut, String feedback, int rating) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
        this.days = days;
        this.checkOut = checkOut;
        this.feedback = feedback;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public boolean isCheckOut() { return checkOut; }

    public void setCheckOut(boolean checkOut) { this.checkOut = checkOut; }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
