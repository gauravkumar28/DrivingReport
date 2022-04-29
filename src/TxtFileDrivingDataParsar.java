import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TxtFileDrivingDataParsar implements IDrivingDataParsar<DrivingLogEntryCollections, File> {

    private IDrivingLogTokenizer<String, String> drivingLogTokenizer;
    private DriverLogEntryValidatorFactory driverLogEntryValidatorFactory;

    public TxtFileDrivingDataParsar(IDrivingLogTokenizer<String, String> drivingLogTokenizer,
                                    DriverLogEntryValidatorFactory driverLogEntryValidatorFactory) {
        this.drivingLogTokenizer = drivingLogTokenizer;
        this.driverLogEntryValidatorFactory = driverLogEntryValidatorFactory;
    }

    public TxtFileDrivingDataParsar() {
        this.drivingLogTokenizer = new DrivingLogTokenizer();
        this.driverLogEntryValidatorFactory = new DriverLogEntryValidatorFactory();
    }

    @Override
    public DrivingLogEntryCollections parse(File file) throws FileNotFoundException, InvalidDataException {
        DrivingLogEntryCollections drivingLogEntryCollections = DrivingLogEntryCollections.builder()
                .driverList(new ArrayList<>())
                .tripList(new ArrayList<>())
                .build();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String log = sc.nextLine();
            processLog(log, drivingLogEntryCollections);
        }
        return drivingLogEntryCollections;
    }

    private void processLog(String logEntry, DrivingLogEntryCollections drivingLogEntryCollections) throws InvalidDataException {
        String[] logEntryToekns = drivingLogTokenizer.tokenize(logEntry);
        LogEntryType logEntryType = LogEntryType.valueOf(logEntryToekns[0]);
        switch (logEntryType) {
            case Driver:
                processDriverLog(logEntryToekns, drivingLogEntryCollections);
                break;
            case Trip:
                processTripLog(logEntryToekns, drivingLogEntryCollections);
                break;
        }
    }

    private void processDriverLog(String[] logEntryToekns, DrivingLogEntryCollections drivingLogEntryCollections) throws InvalidDataException {
        IDrivingLogEntryValidator validator = driverLogEntryValidatorFactory.getValidator(LogEntryType.Driver);
        validator.validate(logEntryToekns);
        drivingLogEntryCollections.addDriver(
                Driver.builder()
                        .name(logEntryToekns[1])
                        .build()
        );
    }

    private void processTripLog(String[] logEntryToekns, DrivingLogEntryCollections drivingLogEntryCollections) throws InvalidDataException {
        IDrivingLogEntryValidator validator = driverLogEntryValidatorFactory.getValidator(LogEntryType.Trip);
        validator.validate(logEntryToekns);
        drivingLogEntryCollections.addTrip(
                Trip.builder()
                        .name(logEntryToekns[1])
                        .startTime(logEntryToekns[2])
                        .endTime(logEntryToekns[3])
                        .distance(Double.valueOf(logEntryToekns[4]))
                        .build());
    }
}
