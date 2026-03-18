import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main{
    static void main() {

        Person person1 = new Person("Анатолий"); //создание двух персонажей
        person1.printCharacter(); //вывод характеристик
        Person person2 = new Person("Евдаким");
        person2.printCharacter();

        BattleService.battleSimulation(person1,person2); //запуск сражения

        String welcomeForBeginningGame = "Приветствуем вас в нашем невероятном мире! Введите help, чтобы увидеть возможные команды";

        System.out.println(welcomeForBeginningGame);

        Scanner scanner = new Scanner(System.in);

        while (true){
            ConsoleInputReader.reader(scanner.next()); //запуск чтения команд
        }
    }
}