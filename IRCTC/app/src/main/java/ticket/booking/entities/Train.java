package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Train {
    private String trainId;
    private int trainNo;
    private List<List<Integer>> seats;
    private List<String> stations;
    private Map<String, String> stationTimes;

    public Train(String trainId, int trainNo, List<List<Integer>> seats, List<String> stations, Map<String, String> stationTimes) {
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stations = stations;
        this.stationTimes = stationTimes;
    }

    public Train() {
    }

    @JsonIgnore
    public String getTrainInfo() {
        return String.format("Train ID: %s Train No: %s", trainId, trainNo);
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public int getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(int trainNo) {
        this.trainNo = trainNo;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public Map<String, String> getStationTimes() {
        return stationTimes;
    }

    public void setStationTimes(Map<String, String> stationTimes) {
        this.stationTimes = stationTimes;
    }
}
