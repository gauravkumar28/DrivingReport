public interface IDrivingLogEntryValidator<V> {
    void validate(V[] v) throws InvalidDataException;
}
