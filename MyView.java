import java.util.Scanner;

public class MyView implements View {
    private Scanner scanner;

    public MyView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void print(String str) {
        System.out.println(str);
    }

    @Override
    public String getInfo() {
        String ans = scanner.nextLine();
        return ans;
    }

    @Override
    public String getOption() {
        print("Выберите опцию: ");
        print("1. Регистрация. ");
        print("2. Авторизация. ");
        String choise = getInfo();
        if (choise.isEmpty() || Integer.parseInt(choise) != 1 && Integer.parseInt(choise) != 2) {
            print("Выбор не сделан. Повторите попытку.");
            choise = getOption();
        }
        return choise;
    }

    @Override
    public String getName() {
        print("Введите Ваше имя: ");
        String name = getInfo();
        if (name.length() < 2) {
            print("Имя введено некоррeктно. Повторите попытку.");
            name = getName();
        }
        return name;
    }

    @Override
    public String getPassword() {
        print("Введите пароль (длина пароля не менее 6 символов): ");
        String password = getInfo();
        if (!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,12}$")) {
            print("Пароль не соответствует заданным условиям.");
            print("Пароль должен содержать не менее 1 строчной и заглавной латинской буквы, длина пароля в диапазоне 6-12 символов.");
            password = getPassword();
        }
        return password;
    }

    @Override
    public String getEmail() {
        print("Введите email: ");
        String email = getInfo();
        if (!email.matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")) {
            print("Некорректный email. Повторите попытку.");
            email = getEmail();
        }
        return email;
    }

    @Override
    public String getBirthDate() {
        print("Введите дату рождения в формате \"дд.мм.гггг\": ");
        String date = getInfo();
        if (!date.matches("(0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[012])[.](19|20)\\d\\d")) {
            print("Дата рождения введена некорректно. Повторите попытку.");
            date = getBirthDate();
        }
        return date;
    }

}