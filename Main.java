public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Василий", "Иванов", 125845, "12.05.2002", true);
        Person p2 = new Person("Мария", "Иванова", 125854, "01.07.1977", false);
        Person p3 = new Person("Иван", "Иванов", 122554, "15.03.1975", true);
        Person p4 = new Person("Татьяна", "Сидорова", 122574, "11.04.1952", false);
        Person p5 = new Person("Анна", "Смирнова", 123845, "12.06.1997", false);
        Person p6 = new Person("Петр", "Петров", 112554, "10.02.1975", true);
        Person p7 = new Person("Алексей", "Смирнов", 158554, "05.01.1996", true);
        Person p8 = new Person("Елена", "Смирнова", 158984, "25.11.2017", false);
        p2.addChild(p1, p3);
        p2.addChild(p5, p6);
        p4.addChild(p2, null);
        p2.setIsMarried(p3);
        p5.setIsMarried(p7);
        p5.addChild(p8, p7);
        p1.printInfo();
        p8.writeInfo();
        GenTree gt1 = new GenTree(p2);
        GenTree gt2 = new GenTree(p5);
        gt1.printInfo();
        gt2.writeInfo();
    }
}
