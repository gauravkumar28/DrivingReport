import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Driver extends DrivingLogEntry{
    public String name;
}
