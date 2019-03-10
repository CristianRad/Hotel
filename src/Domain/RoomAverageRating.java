package Domain;

public class RoomAverageRating {

    private int rating;
    private double averageRating;

    @Override
    public String toString() {
        return "RoomAverageRating{" +
                "rating=" + rating +
                ", averageRating=" + averageRating +
                '}';
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public RoomAverageRating(int rating, double averageRating) {
        this.rating = rating;
        this.averageRating = averageRating;
    }
}
