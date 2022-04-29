import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class DrivingReportApplication {
    public static void main(String... args) throws FileNotFoundException, InvalidDataException {
        ValidateArguments(args);
        //System.out.println("Arguments Provided { filePath=" + args[0] + " }");
        String inputFilePath = args[0];
        IDrivingDataParsar<DrivingLogEntryCollections, File> drivingDataParsar = new TxtFileDrivingDataParsar();
        DrivingLogEntryCollections drivingLogEntryCollections = drivingDataParsar.parse(new File(inputFilePath));
        IDrivingService<DrivingLogEntryCollections, DrivingSummary> drivingService = new DrivingService();
        List<DrivingSummary> drivingSummaryList = drivingService.getReport(drivingLogEntryCollections);
        IDisplayService<DrivingSummary> drivingSummaryIDisplayService = new DrivingReportDisplayService();
        drivingSummaryIDisplayService.display(drivingSummaryList);
    }

    private static void ValidateArguments(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
    }

}
