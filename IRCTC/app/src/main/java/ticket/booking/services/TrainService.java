package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TrainService {
    private List<Train> trainList;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String TRAINS_PATH = "app/src/main/java/ticket/booking/localDb/trains.json";

    public TrainService() throws IOException {
        File trains = new File(TRAINS_PATH);
        trainList = objectMapper.readValue(trains, new TypeReference<List<Train>>() {
        });
    }

    public boolean validTrain(Train train, String source, String destination) {
        List<String> stationOrder = train.getStations();

        int sourceIndex = stationOrder.indexOf(source.toLowerCase());
        int destinationIndex = stationOrder.indexOf(destination.toLowerCase());

        return (sourceIndex != -1 && destinationIndex != -1) && (sourceIndex < destinationIndex);
    }

    public List<Train> searchTains(String source, String destination) {
        return (
                trainList.stream().filter(train -> validTrain(train, source, destination)).collect(Collectors.toList())
        );
    }

    public void addTrain(Train newTrain) throws IOException {
        Optional<Train> trainExist = trainList.stream().filter(train -> train.getTrainId().equalsIgnoreCase(newTrain.getTrainId())).findFirst();

        if (trainExist.isPresent()) {
            trainList.set(trainList.indexOf(trainExist.get()), newTrain);
        } else {
            trainList.add(newTrain);
            File trains = new File(TRAINS_PATH);
            objectMapper.writeValue(trains, trainList);
        }
    }
}
