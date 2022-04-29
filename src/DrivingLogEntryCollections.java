import lombok.Builder;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Builder
@ToString
public class DrivingLogEntryCollections {
    public List<Driver> driverList;
    public List<Trip> tripList;

    public void addTrip(Trip trip) {
        if (tripList == null) {
            tripList = new ArrayList<>();
        }
        tripList.add(trip);
    }

    public void addDriver(Driver driver) {
        if (driverList == null) {
            driverList = new ArrayList<>();
        }
        driverList.add(driver);
    }
}
