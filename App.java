public class App {
    public static void main(String[] args) {
        Presenter pr = new Presenter(new MyView());
        pr.inputInfo(new Person());
        pr.inputInfo(new Person());
        pr.printUsers();
    }
}

