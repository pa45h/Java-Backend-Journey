package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class UserBookingService {

    private User user;
    private static final String USERS_PATH = "app/src/main/java/ticket/booking/localDb/users.json";
    private List<User> userList;
    private ObjectMapper objectMapper = new ObjectMapper();

    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUser();
        if (logIn(user)) {
            System.out.println("User Log In Successfull!");
        } else {
            System.out.println("Error in Log In!");
        }
    }

    public UserBookingService() throws IOException {
        loadUser();
    }

    public void loadUser() throws IOException {
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }

    public Boolean signUp(User user) {
        try {
            userList.add(user);
            File usersFile = new File(USERS_PATH);
            objectMapper.writeValue(usersFile, userList);
            return Boolean.TRUE;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Boolean.FALSE;
        }
    }

    public Boolean logIn(User user) {
        try {
            Optional<User> foundUser = userList.stream().filter(u -> (u.getName().equals(user.getName()) && UserServiceUtil.checkPassword(u.getPassword(), user.getHashedPassword()))).findFirst();
            if (foundUser.isPresent()) {
                this.user = foundUser.get();
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Boolean.FALSE;
        }
    }

    public void fetchBookings() {
        user.printTickets();
    }

    public Boolean cancelBooking(String ticketId) {
        try {
            Boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(ticketId));
            if (removed) {
                System.out.println("Removed ticket - " + ticketId);
            } else {
                System.out.println("Could not found the ticket - " + ticketId);
            }
            return removed;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Boolean.FALSE;
        }
    }

    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTains(source, destination);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<List<Integer>> fetchSeats(Train train) {
        if (train == null || train.getSeats() == null) {
            throw new IllegalStateException("Train seats not loaded");
        }
        return train.getSeats();
    }

    public boolean bookTrainSeat(Train train, int row, int col, String source, String dest) {
        try {
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && col >= 0 && col < seats.get(row).size()) {
                if (seats.get(row).get(col) == 0) {
                    seats.get(row).set(col, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);

                    Ticket ticket = new Ticket(
                            UUID.randomUUID().toString(),
                            user.getUserId(),
                            source, dest,
                            new Date().toString(),
                            train
                    );
                    user.getTicketsBooked().add(ticket);
                    objectMapper.writeValue(new File(USERS_PATH), userList);

                    return Boolean.TRUE;
                } else {
                    return Boolean.FALSE;
                }
            } else {
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Boolean.FALSE;
        }
    }
}
