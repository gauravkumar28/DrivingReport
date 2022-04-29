public class DriverLogEntryValidatorFactory {
    public IDrivingLogEntryValidator<String> getValidator(LogEntryType  logEntryType) {
        if (LogEntryType.Trip.equals(logEntryType)) {
            return new TripLogEntryValidator();
        }
        return new DriverLogEntryValidator();
    }
}
