package Repository;

import Domain.Room;
import Domain.RoomValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomRepository {

    private Map<Integer, Room> storage = new HashMap<>();
    private RoomValidator validator;

    /**
     * Instantiates a repository for rooms.
     * @param validator is the validator used.
     */

    public RoomRepository(RoomValidator validator) {
        this.validator = validator;
    }

    /**
     * Adds a room to storage.
     * @param room is the room to add.
     */

    public void add(Room room) {

        if(storage.containsKey(room.getId())) {
            throw new RuntimeException("A room with that ID already exists!");
        }

        validator.validateCheckIn(room);
        storage.put(room.getId(), room);
    }

    /**
     * Updates a room from the storage.
     * @param room is the room to update.
     */

    public void update(Room room) {

        if(!storage.containsKey(room.getId())) {
            throw new RuntimeException("There is no room with the given ID to update!");
        }

        validator.validateCheckOut(room);
        storage.put(room.getId(), room);
    }

    /**
     * @return a list of all check-ins.
     */

    public List<Room> getAll() {
        return new ArrayList<>(storage.values());
    }

}
