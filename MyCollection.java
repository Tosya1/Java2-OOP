import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyCollection implements Iterable<Object>{
    private List<Object> c;

    public MyCollection() {
        this.c = new ArrayList<>();
    }

    public void add (Object o) {
        c.add(o);
    }

    @Override
    public String toString() {
        return "" + c;
    }

    public Object get (int i) {
        return c.get(i);
    }

    public List<Object> getC() {
        return c;
    }

    @Override
    public Iterator<Object> iterator() {
        return c.iterator();
    }
}
