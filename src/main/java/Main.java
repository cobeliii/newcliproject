import com.cobeliii.booking.BookingDataAccessService;
import com.cobeliii.booking.BookingService;
import com.cobeliii.car.CarDao;
import com.cobeliii.car.CarDataAccessService;
import com.cobeliii.car.CarService;
import com.cobeliii.user.User;
import com.cobeliii.user.UserDao;
import com.cobeliii.user.UserDataAccessService;
import com.cobeliii.user.UserService;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println();
        System.out.println("Welcome to Car Booking System!");
        CarDao carDao = new CarDataAccessService();
        CarService carService = new CarService(carDao);
        UserDao userDao = new UserDataAccessService();
        UserService userService = new UserService(userDao);
        BookingDataAccessService bookingDao = new BookingDataAccessService();

        BookingService bookingService = new BookingService(userService, carService, bookingDao);
        while (true) {
            menu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    scanner.nextLine();
                    carService.viewAvailableCars();
                    System.out.println("Enter car id: ");
                    UUID carId = UUID.fromString(scanner.nextLine());
                    userService.printUsers();
                    System.out.println("Enter user id:");
                    UUID userId = UUID.fromString(scanner.nextLine());
                    bookingService.addBooking(carId, userId);
                    break;
                case 2:
                    scanner.nextLine();
                    userService.printUsers();
                    System.out.println("Enter user name: ");
                    String userName = scanner.nextLine();
                    User user = userService.findUserByName(userName);
                    bookingService.viewAllUserBookedCars(user);
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
                    return;
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



