public class InvalidDataException extends Exception {
    public InvalidDataException(String invalid_trip_log_entry) {
        super(invalid_trip_log_entry);
    }
}
