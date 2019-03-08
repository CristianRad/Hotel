import Domain.RoomValidator;
import Repository.RoomRepository;
import Service.RoomService;
import UI.Console;

public class Main {

    public static void main(String[] args) {

        RoomValidator validator = new RoomValidator();
        RoomRepository repository = new RoomRepository(validator);
        RoomService service = new RoomService(repository);
        Console console = new Console(service);
        console.run();
    }

}
