package UI;

import Domain.Room;
import Domain.RoomAverageRating;
import Service.RoomService;

import java.util.Map;
import java.util.Scanner;

public class Console {

    private RoomService service;
    private Scanner scanner;

    public Console(RoomService service) {
        this.service = service;
        scanner = new Scanner(System.in);
    }

    private void showMenu() {

        System.out.println("1. Check-in");
        System.out.println("2. Check-out");
        System.out.println("3. Raport rating camere");
        System.out.println("a. Afisare toate rezervarile");
        System.out.println("x. Iesire");
    }

    public void run() {

        while (true) {
            showMenu();
            String option = scanner.nextLine();
            if (option.equals("1")) {
                handleCheckIn();
            } else if (option.equals("2")) {
                handleCheckOut();
            } else if (option.equals("3")) {
                handleRoomReport();
            } else if (option.equals("a")) {
                handleShowAll();
            } else if (option.equals("x")) {
                break;
            }
        }
    }

    private void handleRoomReport() {

        for (RoomAverageRating ratingAverage : service.getRoomRatingAverages())
            System.out.println(ratingAverage);
    }

    private void handleShowAll() {

        for (Room checkIn : service.getAll())
            System.out.println(checkIn);
    }

    private void handleCheckIn() {

        try {
            System.out.print("Dati ID-ul: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Dati numarul camerei: ");
            int number = Integer.parseInt(scanner.nextLine());
            System.out.print("Dati numarul de persoane: ");
            int capacity = Integer.parseInt(scanner.nextLine());
            System.out.print("Dati numarul de zile: ");
            int days = Integer.parseInt(scanner.nextLine());

            service.checkIn(id, number, capacity, days);
        } catch (RuntimeException runtimeException) {
            System.out.println("Avem erori: " + runtimeException.getMessage());
        }
    }

    private void handleCheckOut() {

        try {
            System.out.print("Dati numarul camerei: ");
            int number = Integer.parseInt(scanner.nextLine());
            System.out.print("Dati un feedback: ");
            String report = scanner.nextLine();
            System.out.print("Dati un rating: ");
            int rating = Integer.parseInt(scanner.nextLine());

            service.checkOut(number, report, rating);
        } catch (RuntimeException runtimeException) {
            System.out.println("Avem erori: " + runtimeException.getMessage());
        }
    }

}

