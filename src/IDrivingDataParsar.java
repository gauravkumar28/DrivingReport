import java.io.FileNotFoundException;
import java.util.List;

public interface IDrivingDataParsar<U, V> {
    U parse(V v) throws FileNotFoundException, InvalidDataException;
}
