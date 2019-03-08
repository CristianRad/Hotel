package Service;

import Domain.Room;
import Repository.RoomRepository;

import java.util.*;

public class RoomService {

    private RoomRepository repository;

    /**
     * Instantiates a service for rooms.
     * @param repository is the repository used.
     */

    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    /**
     * Check in.
     * @param id is the ID of the reservation.
     * @param number is the room number.
     * @param capacity is the number of people per room.
     * @param days is the time of reservation.
     */

    public void checkIn(int id, int number, int capacity, int days) {

        Room room = new Room(id, number, capacity, days);
        List<Room> checkIns = repository.getAll();
        for (Room r : checkIns) {
            if(r.getNumber() == number && !r.isCheckOut()) {
                throw new RuntimeException("The room is already taken!");
            }
        }
        repository.add(room);

    }

    /**
     * Check out
     * @param number is the room number.
     * @param feedback is the feedback of the customer.
     * @param rating is the customer's rating of the room.
     */

    public void checkOut(int number, String feedback, int rating) {

        Room reservedRoom = null;
        List<Room> checkIns = repository.getAll();
        for (Room r : checkIns) {
            if(r.getNumber() == number && !r.isCheckOut()) {
                reservedRoom = r;
            }
        }

        if(reservedRoom != null) {
            reservedRoom.setFeedback(feedback);
            reservedRoom.setRating(rating);
            reservedRoom.setCheckOut(true);
            repository.update(reservedRoom);
        } else {
            throw new RuntimeException("There is no room with the given number!");
        }

    }

    /**
     * @return a list of all check-ins.
     */

    public List<Room> getAll() {
        return repository.getAll();
    }

    /**
     * @return a list of all rooms.
     */

    public Set<Integer> getAllRooms() {

        Set<Integer> rooms = new HashSet<>();
        List<Room> checkIns = getAll();
        for (Room room : checkIns) {
            rooms.add(room.getNumber());
        }
        return rooms;
    }

    /**
     * @return a list of ratings for a given room.
     * @param number is the room number.
     */

    public List<Integer> getRatingsByRoom(int number) {

        List<Integer> ratings = new ArrayList<>();
        for (Room room : getAll()) {
            if(room.getNumber() == number) {
                ratings.add(room.getRating());
            }
        }
        return ratings;
    }

    /**
     * @return the average rating for each room.
     * @param ratings is a list with all the ratings for a room.
     */

    public Double getAverage(List<Integer> ratings) {

        int sum = 0;
        for (Integer rating : ratings) {
            sum += rating;
        }
        return (sum * 1.0) / ratings.size();
    }

    /**
     * @return a report of the average ratings per room.
     */

    public Map<Double, Integer> getReports() {

        Map<Double, Integer> reports = new TreeMap<>();
        for (Integer number : getAllRooms()) {
            List<Integer> ratings = getRatingsByRoom(number);
            Double average = getAverage(ratings);
            reports.put(average, number);
        }

        Map<Double, Integer> descReports = new TreeMap<>(new Comparator<Double>() {
            @Override
            public int compare(Double rating1, Double rating2) {
                return rating2.compareTo(rating1);
            }
        });
        descReports.putAll(reports);
        return descReports;
    }
}
