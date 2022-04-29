public class DriverLogEntryValidator implements IDrivingLogEntryValidator<String> {
    public void validate(String[] driverInfoList) throws InvalidDataException {
        if (driverInfoList.length != 2) {
            throw new InvalidDataException("Invalid Trip Log Entry");
        }
    }
}
