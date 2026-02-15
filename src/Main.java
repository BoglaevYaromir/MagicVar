import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main{
    static void main() {

        Person a = new Person("Анатолий"); //создание двух персонажей
        a.printCharacter(); //вывод характеристик
        Person b = new Person("Евдаким");
        b.printCharacter();

        Person.battle(a,b); //запуск сражения

        System.out.println("Приветствуем вас в нашем невероятном мире! Введите help, чтобы увидеть возможные команды");

        Scanner scanner = new Scanner(System.in);

        while (true){
            ConsoleInputReader.reader(scanner.next());
        }
    }
}