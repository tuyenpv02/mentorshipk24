import java.util.List;
import java.util.function.Predicate;

public class Linq<T> {

    private List<T> elements;
    public Linq(List<T> newList) {
        this.elements = newList;
    }

    public Long count(Predicate<T> predicate){
        return elements.stream().filter(predicate).count();
    }
    public List<T> toList() {
        return elements;
    }
}
