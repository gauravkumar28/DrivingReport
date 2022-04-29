public interface IDrivingLogTokenizer<U, V> {
    U[] tokenize(U log);

    U[] tokenize(U log, V seperator);
}
