import com.cobeliii.booking.BookingService;
import com.cobeliii.car.CarService;
import com.cobeliii.user.UserService;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        CarService carService = new CarService();
        UserService userService = new UserService();
        BookingService bookingService = new BookingService(userService, carService);

        menu();
        int choice = scanner.nextInt();
        while (choice != 7) {
            menu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    bookingService.addBooking();
                    break;
                case 2:
                    bookingService.viewAllUserBookedCars();
                    break;
                case 3:
                    bookingService.printBookings();
                    break;
                case 4:
                    carService.viewAvailableCars();
                    break;
                case 5:
                    System.out.println("Available Electric cars: ");
                    carService.viewAvailableElectricCars();
                    break;
                case 6:
                    userService.printUsers();
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

    }

    public static void menu(){
        System.out.println("1️⃣ - Book Car");
        System.out.println("2️⃣ - View All User Booked Cars");
        System.out.println("3️⃣ - View All Bookings");
        System.out.println("4️⃣ - View Available Cars");
        System.out.println("5️⃣ - View Available Electric Cars");
        System.out.println("6️⃣ - View All Users");
        System.out.println("7️⃣ - Exit");
    }

}



