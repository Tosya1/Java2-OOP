import java.util.HashSet;
import java.util.Set;

public class Presenter {
    private Set<Model> users = new HashSet<>();
    private View view = new View();

    public void inputInfo() {
        String choise = view.getOption();
        String name = view.getName();
        String password = view.getPassword();
        if (Integer.parseInt(choise) == 1) {
            String email = view.getEmail();
            String birthDate = view.getBirthDate();
            Model user = new Model(name, password, email, birthDate);
            users.add(user);
            view.print("Регистрация прошла успешно");
        } else if (Integer.parseInt(choise) == 2) {
            for (Model user : users) {
                if (user.get(0).equals(name) && user.get(1).equals(password)) {
                    view.print("Авторизация прошла успешно");
                } else {
                    view.print("Пользователь не зарегистрирован. Пройдите регистрацию.");
                }
            }
        }
    }

    public void printUsers() {
        for (Model user : users) {
            view.print("Имя: " + user.get(0) + "; Пароль: " + user.get(1) + "; Email: " + user.get(2)
                    + "; Дата рождения: " + user.get(3));
        }
    }
}
