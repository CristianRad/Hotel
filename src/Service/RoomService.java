package Service;

import Domain.Room;
import Domain.RoomAverageRating;
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

    public List<RoomAverageRating> getRoomRatingAverages() {

        List<RoomAverageRating> results = new ArrayList<>();
        Map<Integer, List<Integer>> ratingsForRooms = new HashMap<>();

        for (Room r : repository.getAll())
            if (r.isCheckOut()) {
                int room = r.getNumber();
                int rating = r.getRating();

                if(!ratingsForRooms.containsKey(room))
                    ratingsForRooms.put(room, new ArrayList<>());
                ratingsForRooms.get(room).add(rating);
            }

        for (int room : ratingsForRooms.keySet()) {
            List<Integer> ratings = ratingsForRooms.get(room);
            int sum = 0;
            for (int r : ratings)
                sum += r;
            double average = (double) sum / ratings.size();
            results.add(new RoomAverageRating(room, average));
        }

        results.sort((r1, r2) -> {
            if (r1.getAverageRating() > r2.getAverageRating())
                return -1;
            else if (r1.getAverageRating() == r2.getAverageRating())
                return 0;
            else
                return 1;
        });
        return results;

    }

    /**
     * @return a list of all check-ins.
     */

    public List<Room> getAll() {
        return repository.getAll();
    }

}
