import org.junit.Before;
import org.junit.Test;

public class TripLogEntryValidatorTest {
    TripLogEntryValidator tripLogEntryValidator;
    @Before
    public void setup() {
        tripLogEntryValidator = new TripLogEntryValidator();
    }

    @Test
    public void testValidTripData() throws InvalidDataException {
        tripLogEntryValidator.validate(new String[] { "Trip", "Driver1", "10:10", "10:20", "10"});
    }

    @Test(expected = InvalidDataException.class)
    public void testTripWithInvalidStartTimeData() throws InvalidDataException {
        tripLogEntryValidator.validate(new String[] { "Trip", "Driver1", "10", "10:10", "10"});
    }

    @Test(expected = InvalidDataException.class)
    public void testTripWithInvalidEndTimeData() throws InvalidDataException {
        tripLogEntryValidator.validate(new String[] { "Trip", "Driver1", "10:10", ":10", "10"});
    }

    @Test(expected = InvalidDataException.class)
    public void testTripWithWrongStartAndEndTimeData() throws InvalidDataException {
        tripLogEntryValidator.validate(new String[] { "Trip", "Driver1", "10:10", "09:10", "10"});
    }

    @Test(expected = InvalidDataException.class)
    public void testTripWithInvalidDistance() throws InvalidDataException {
        tripLogEntryValidator.validate(new String[] { "Trip", "Driver1", "10:10", "10:10", "0"});
    }


}
