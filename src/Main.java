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

        service.checkIn(1,200,3,5);
        service.checkIn(2,224,4,3);
        service.checkIn(3,251,3,6);
        service.checkIn(4,247,3,5);
        service.checkOut(200,"Good",3);
        service.checkOut(251,"Nice",4);
        service.checkIn(5,200,3,4);
        service.checkOut(247,"Bad",2);
        service.checkIn(6,247,3,7);
        service.checkOut(224,"Nice",4);
        service.checkOut(200,"Very Good",5);
        service.checkOut(247,"Very Bad",1);

        console.run();
    }

}
