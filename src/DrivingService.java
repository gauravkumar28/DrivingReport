import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DrivingService implements IDrivingService<DrivingLogEntryCollections, DrivingSummary> {
    @Override
    public List<DrivingSummary> getReport(DrivingLogEntryCollections drivingLogEntryCollections) {
        filterUnwantedData(drivingLogEntryCollections);
        List<DrivingSummary> drivingSummaryList = new ArrayList<>();
        for (Driver driver : drivingLogEntryCollections.driverList) {
            DrivingSummary drivingSummary = getDrivingSummary(driver, drivingLogEntryCollections);
            drivingSummaryList.add(drivingSummary);
        }
        return drivingSummaryList;
    }

    private void filterUnwantedData(DrivingLogEntryCollections drivingLogEntryCollections) {
        drivingLogEntryCollections.tripList = drivingLogEntryCollections
                .tripList
                .stream()
                .filter(x -> !x.hasSpeedOutOfRange())
                .collect(Collectors.toList());
    }

    private DrivingSummary getDrivingSummary(Driver driver, DrivingLogEntryCollections drivingLogEntryCollections) {
        double distance = drivingLogEntryCollections
                .tripList
                .stream()
                .filter(trip -> trip.name.equals(driver.name))
                .mapToDouble(trip -> trip.distance)
                .sum();
        long totalTimeInMinutes = drivingLogEntryCollections
                .tripList
                .stream()
                .filter(trip -> trip.name.equals(driver.name))
                .mapToLong(trip -> trip.getDuration().toMinutes())
                .sum();
        double avgSpeedMPH = (totalTimeInMinutes == 0) ? 0 : ((distance * 60) / totalTimeInMinutes);
        return DrivingSummary.builder()
                .name(driver.name)
                .avgSpeed(avgSpeedMPH)
                .distance(distance)
                .build();
    }
}
