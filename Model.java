import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {
    private List<String> user;

    public Model(String name, String password, String email, String birthdate) {
        this.user = new ArrayList<>(Arrays.asList(name, password, email, birthdate));
    }

    public void add(String name, String password) {
        this.user.add(name);
        this.user.add(password);
    }

    public String get(int i) {
        return user.get(i);
    }
}
