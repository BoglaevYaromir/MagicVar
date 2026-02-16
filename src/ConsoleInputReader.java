import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleInputReader {
    private static final Pattern PATTERN_HELP = Pattern.compile("^[Hh]elp\s*");
    private static final Pattern PATTERN_RANDOM = Pattern.compile("^([Rr]andom|1)\s*");
    private static final Pattern PATTERN_TRAINING = Pattern.compile("^([Tt]raining|2)\s*");
    private static final Pattern PATTERN_FIGHT = Pattern.compile("^([Ff]ight|3)\s*");

    public static void reader(String command){
        if (PATTERN_HELP.matcher(command).matches()) {
            System.out.println("Для сражения двух случайных персонажей введите \"random\"(1)\n" +
                    "Для сражения одного случайного и кастомного персонажа введите \"training\"(2)\n" +
                    "Для сражения двух кастомных персонажей введите \"fight\"(3)\n");
        } else if (PATTERN_RANDOM.matcher(command).matches()) {
            Person a = new Person("Анатолий"); //создание двух персонажей
            a.printCharacter(); //вывод характеристик
            Person b = new Person("Евдаким");
            b.printCharacter();
            BattleService.battle(a,b);//запуск сражения
        } else if (PATTERN_TRAINING.matcher(command).matches()) {
            Person a = new Person();
            inputDate(a);
            Person b = new Person("Кирилл");

            a.printCharacter();
            b.printCharacter();

            BattleService.battle(a,b);

        } else if (PATTERN_FIGHT.matcher(command).matches()) {
            Person a = new Person();
            inputDate(a);
            Person b = new Person();
            inputDate(b);

            a.printCharacter();
            b.printCharacter();

            BattleService.battle(a,b);

        } else{
            System.out.println("Не удалось распознать команду");
        }


    }

    private static void inputDate(Person person){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя персонажа");
        String name = scanner.next();
        person.setName(name);

        System.out.println("Введите силу");
        short strength = scanner.nextShort();
        person.setStrength(strength);

        System.out.println("Введите здоровье");
        short health = scanner.nextShort();
        person.setHealth(health);

        System.out.println("Введите ловкость");
        short dexterity  = scanner.nextShort();
        person.setDexterity(dexterity);

        System.out.println("Введите энергию");
        short energy = scanner.nextShort();
        person.setEnergy(energy);

        System.out.println("Введите IQ");
        short iq = scanner.nextShort();
        person.setIq(iq);

        System.out.println("Введите скорость");
        short speed = scanner.nextShort();
        person.setSpeed(speed);

        System.out.println("Введите реакцию");
        short reaction = scanner.nextShort();
        person.setReaction(reaction);

        person.setDamage();
        person.setMentalHealth();
    }

}
