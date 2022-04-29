import java.util.Collections;
import java.util.List;

public class DrivingReportDisplayService implements IDisplayService<DrivingSummary> {
    @Override
    public void display(List<DrivingSummary> drivingSummaries) {
        // sort by reverse order of distance travelled
        Collections.sort(drivingSummaries);
        for (DrivingSummary drivingSummary : drivingSummaries) {
            System.out.println(drivingSummary.name +
                    ": " +
                    Math.round(drivingSummary.distance) +
                    " miles" +
                    (Math.round(drivingSummary.distance) > 0 ? " @ " +
                            Math.round(drivingSummary.avgSpeed) +
                            " mph" : ""
                    )
            );
        }
    }
}
