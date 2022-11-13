import java.util.Arrays;
import java.util.Comparator;

// 1. Создать метод, который принимает массив int и сортирует его по последней 
// цифре. Используйте метод Arrays.sort. для его работы создайте свой компаратор. 
// Имеется в виду последняя цифра в записи числа, например в числе 123, последння 
// цифра 3. Надо сделать сортировку, которая учитывает только эту последнюю цифру 
// в числе.
// 2. Создайте класс, который представляет из себя коллекцию, добавьте 2 метода add и
// get для работы с коллекцией. Реализуйте возможность использования цикла for-each
// для работы с данной коллекцией. Для этого реализуйте интерфейс Iterable и 
// создайте итератор.

public class practice3 {
    public static void main(String[] args) {
        // 1
        Integer[] list = new Integer[] { 125, 362, 586, 751, 14, 67, 4589 };
        sort(list);
        System.out.println(Arrays.toString(list));

        // 2
        MyCollection mc = new MyCollection();
        mc.add("str");
        mc.add(10);
        mc.add(15);
        System.out.println(mc.get(1));
        for (Object el : mc) {
            System.out.println(el);
        }
    }

    static void sort(Integer[] list) {
        Arrays.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 % 10 - o2 % 10;
            }
        });
    }
}
