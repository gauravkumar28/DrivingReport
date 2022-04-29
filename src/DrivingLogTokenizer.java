public class DrivingLogTokenizer implements IDrivingLogTokenizer<String, String> {
    private static String DEFAULT_DELIMITER = " ";

    @Override
    public String[] tokenize(String log) {
        return log.split(DEFAULT_DELIMITER);
    }

    @Override
    public String[] tokenize(String log, String delimiter) {
        return log.split(delimiter);
    }
}
