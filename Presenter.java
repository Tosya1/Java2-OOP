import java.util.HashSet;
import java.util.Set;

public class Presenter {
    private static Set<Model> users = new HashSet<>();
    private View view;

    public Presenter(View view) {
        this.view = view;
    }

    public void inputInfo(Model model) {
        String choise = view.getOption();
        model.setName(view.getName());
        model.setPassword(view.getPassword());
        if (Integer.parseInt(choise) == 1) {
            model.setEmail(view.getEmail());
            model.setBirthDate(view.getBirthDate());
            users.add(model);
            view.print("Регистрация прошла успешно.");
        } else if (Integer.parseInt(choise) == 2) {
            for (Model user : users) {
                if (user.getName().equals(model.getName()) && user.getPassword().equals(model.getPassword())) {
                    view.print("Авторизация прошла успешно.");
                } else {
                    view.print("Пользователь не зарегистрирован. Пройдите регистрацию.");
                }
            }
        }
    }

    public void printUsers() {
        for (Model user : users) {
            view.print("Имя: " + user.getName() + "; Пароль: " + user.getPassword() + "; Email: " + user.getEmail()
                    + "; Дата рождения: " + user.getBirthDate());
        }
    }

}
