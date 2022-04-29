import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TripLogEntryValidator implements IDrivingLogEntryValidator<String> {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

    @Override
    public void validate(String[] tripInfoList) throws InvalidDataException {
        if (tripInfoList.length != 5) {
            throw new InvalidDataException("Invalid Trip Log Entry");
        }

        try {
            Date d1 = dateFormat.parse(tripInfoList[2]);
            Date d2 = dateFormat.parse(tripInfoList[3]);
            if (!d2.after(d1)) {
                throw new InvalidDataException("Invalid Trip Log Entry");
            }
        } catch (ParseException pe) {
            throw new InvalidDataException("Invalid Trip Log Entry");
        }

        if (Double.valueOf(tripInfoList[4]) <= 0.0) {
            throw new InvalidDataException("Invalid Trip Log Entry");
        }

    }
}
