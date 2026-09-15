
package ticket.booking;

import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.services.UserBookingService;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;
import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n******************** WELCOME TO IRCTC ********************\n\n");
        int option = 0;
        UserBookingService userBookingService;
        Train trainSelectedForBooking = null;
        String source = "";
        String dest = "";
        try {
            userBookingService = new UserBookingService();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        while (option != 7) {
            System.out.println("Choose option\n");
            System.out.println("\t1. Sign up");
            System.out.println("\t2. Login");
            System.out.println("\t3. Fetch Bookings");
            System.out.println("\t4. Search Trains");
            System.out.println("\t5. Book a Seat");
            System.out.println("\t7. Exit the App");
            System.out.print("\nEnter Option No.: ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter the username to signup");
                    String nameToSignUp = scanner.next();
                    System.out.println("Enter the password to signup");
                    String passwordToSignUp = scanner.next();
                    User userToSignup = new User(nameToSignUp, passwordToSignUp, UserServiceUtil.hashPassword(passwordToSignUp), new ArrayList<>(), UUID.randomUUID().toString());
                    if (userBookingService.signUp(userToSignup)) {
                        System.out.println("User Sign up Successfull!");
                    } else {
                        System.out.println("Error in Sign Up!");
                    }
                    break;
                case 2:
                    System.out.println("Enter the username to Login");
                    String nameToLogin = scanner.next();
                    System.out.println("Enter the password to Login");
                    String passwordToLogin = scanner.next();
                    User userToLogin = new User(nameToLogin, passwordToLogin, UserServiceUtil.hashPassword(passwordToLogin), new ArrayList<>(), UUID.randomUUID().toString());
                    try {
                        userBookingService = new UserBookingService(userToLogin);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Fetching your bookings");
                    userBookingService.fetchBookings();
                    break;
                case 4:
                    System.out.println("Type your source station");
                    source = scanner.next();
                    System.out.println("Type your destination station");
                    dest = scanner.next();
                    List<Train> trains = userBookingService.getTrains(source, dest);
                    int index = 1;
                    for (Train t : trains) {
                        System.out.println(index + " Train id : " + t.getTrainId());
                        for (Map.Entry<String, String> entry : t.getStationTimes().entrySet()) {
                            System.out.println("station " + entry.getKey() + " time: " + entry.getValue());
                        }
                    }
                    System.out.println("Select a train by typing 1,2,3...");
                    int choice = scanner.nextInt();
                    if (choice < 1 || choice > trains.size()) {
                        System.out.println("Invalid train selection");
                        break;
                    }
                    trainSelectedForBooking = trains.get(choice - 1);
                    break;
                case 5:
                    System.out.println("Select a seat out of these seats");
                    try {
                        List<List<Integer>> seats = userBookingService.fetchSeats(trainSelectedForBooking);
                        for (List<Integer> row : seats) {
                            for (Integer val : row) {
                                System.out.print(val + " ");
                            }
                            System.out.println();
                        }
                        System.out.println("Select the seat by typing the row and column");
                        System.out.println("Enter the row");
                        int row = scanner.nextInt();
                        System.out.println("Enter the column");
                        int col = scanner.nextInt();
                        System.out.println("Booking your seat....");
                        Boolean booked = userBookingService.bookTrainSeat(trainSelectedForBooking, row, col, source, dest);
                        if (booked.equals(Boolean.TRUE)) {
                            System.out.println("Booked! Enjoy your journey");
                        } else {
                            System.out.println("Can't book this seat");
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
