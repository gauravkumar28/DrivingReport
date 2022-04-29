import java.util.List;

public interface IDrivingService<U, V> {
    List<V> getReport(U u);
}
