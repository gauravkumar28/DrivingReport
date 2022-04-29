import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class DrivingSummary implements Comparable<DrivingSummary> {
    public String name;
    public double distance;
    public double avgSpeed;

    @Override
    public int compareTo(DrivingSummary otherDrivingSummary) {
        return Double.compare(otherDrivingSummary.distance, this.distance);
    }
}
