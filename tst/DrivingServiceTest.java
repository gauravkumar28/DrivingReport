import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DrivingServiceTest {
    private DrivingService drivingService;
    private DrivingLogEntryCollections drivingLogEntryCollections;

    @Before
    public void setup() {
        drivingService = new DrivingService();
    }

    @Test
    public void testNoTripForAnyDriver() {
        drivingLogEntryCollections = DrivingLogEntryCollections.builder()
                .tripList(Arrays.asList())
                .driverList(Arrays.asList(
                        Driver.builder().name("Driver1").build()
                ))
                .build();
        List<DrivingSummary> drivingSummaryList = drivingService.getReport(drivingLogEntryCollections);
        Assert.assertEquals(1, drivingSummaryList.size());
        Assert.assertEquals(0.0, drivingSummaryList.get(0).distance, 0);
        Assert.assertEquals(0.0, drivingSummaryList.get(0).avgSpeed, 0);
    }

    @Test
    public void testDrivingReportWithOutlierTrips() {
        drivingLogEntryCollections = DrivingLogEntryCollections.builder()
                .tripList(Arrays.asList(
                        Trip.builder().name("Driver1").startTime("09:20").endTime("13:30").distance(20.0).build(),
                        Trip.builder().name("Driver2").startTime("17:20").endTime("18:40").distance(20.0).build(),
                        Trip.builder().name("Driver2").startTime("09:20").endTime("09:50").distance(20.0).build(),
                        Trip.builder().name("Driver3").startTime("09:20").endTime("09:22").distance(50.0).build()
                ))
                .driverList(Arrays.asList(
                        Driver.builder().name("Driver1").build(),
                        Driver.builder().name("Driver2").build(),
                        Driver.builder().name("Driver3").build()
                ))
                .build();
        List<DrivingSummary> drivingSummaryList = drivingService.getReport(drivingLogEntryCollections);
        Assert.assertEquals(3, drivingSummaryList.size());
        //Driver1
        Assert.assertEquals(0.0, drivingSummaryList.get(0).distance, 0);
        Assert.assertEquals(0.0, drivingSummaryList.get(0).avgSpeed, 0);
        //Driver2
        Assert.assertEquals(40.0, drivingSummaryList.get(1).distance, 0);
        Assert.assertEquals(21.8, drivingSummaryList.get(1).avgSpeed, 5);
        //Driver3
        Assert.assertEquals(0.0, drivingSummaryList.get(2).distance, 0);
        Assert.assertEquals(0.0, drivingSummaryList.get(2).avgSpeed, 0);
    }

    @Test
    public void testDrivingReports() {
        drivingLogEntryCollections = DrivingLogEntryCollections.builder()
                .tripList(Arrays.asList(
                        Trip.builder().name("Driver1").startTime("09:20").endTime("10:30").distance(20.0).build(),
                        Trip.builder().name("Driver2").startTime("17:20").endTime("18:40").distance(20.0).build(),
                        Trip.builder().name("Driver2").startTime("09:20").endTime("09:50").distance(20.0).build(),
                        Trip.builder().name("Driver3").startTime("09:20").endTime("09:22").distance(2.0).build()
                ))
                .driverList(Arrays.asList(
                        Driver.builder().name("Driver1").build(),
                        Driver.builder().name("Driver2").build(),
                        Driver.builder().name("Driver3").build()
                ))
                .build();
        List<DrivingSummary> drivingSummaryList = drivingService.getReport(drivingLogEntryCollections);
        Assert.assertEquals(3, drivingSummaryList.size());
        //Driver1
        Assert.assertEquals(20.0, drivingSummaryList.get(0).distance, 0);
        Assert.assertEquals(17.14, drivingSummaryList.get(0).avgSpeed, 5);
        //Driver2
        Assert.assertEquals(40.0, drivingSummaryList.get(1).distance, 0);
        Assert.assertEquals(21.8, drivingSummaryList.get(1).avgSpeed, 5);
        //Driver3
        Assert.assertEquals(2.0, drivingSummaryList.get(2).distance, 0);
        Assert.assertEquals(60.0, drivingSummaryList.get(2).avgSpeed, 0);
    }

}
