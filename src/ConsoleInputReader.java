import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleInputReader {
    private static final Pattern PATTERN_HELP = Pattern.compile("^[Hh]elp\s*"); //паттерн регулярных выражений
    private static final Pattern PATTERN_RANDOM = Pattern.compile("^([Rr]andom|1)\s*");
    private static final Pattern PATTERN_TRAINING = Pattern.compile("^([Tt]raining|2)\s*");
    private static final Pattern PATTERN_FIGHT = Pattern.compile("^([Ff]ight|3)\s*");

    public static void reader(String command){
        if (PATTERN_HELP.matcher(command).matches()) { //если паттерн совпадает с введённой командой
            System.out.println("Для сражения двух случайных персонажей введите \"random\"(1)\n" +
                    "Для сражения одного случайного и кастомного персонажа введите \"training\"(2)\n" +
                    "Для сражения двух кастомных персонажей введите \"fight\"(3)\n");
        } else if (PATTERN_RANDOM.matcher(command).matches()) {
            Person person1 = new Person("Анатолий"); //создание двух персонажей
            person1.printCharacter(); //вывод характеристик
            Person person2 = new Person("Евдаким");
            person2.printCharacter();
            BattleService.battleSimulation(person1,person2);//запуск сражения
        } else if (PATTERN_TRAINING.matcher(command).matches()) {
            Person person1 = new Person();
            inputData(person1);//ввод характеристик
            Person person2 = new Person("Кирилл");

            person1.printCharacter();
            person2.printCharacter();

            BattleService.battleSimulation(person1,person2);

        } else if (PATTERN_FIGHT.matcher(command).matches()) {
            Person person1 = new Person();
            inputData(person1);
            Person person2 = new Person();
            inputData(person2);

            person1.printCharacter();
            person2.printCharacter();

            BattleService.battleSimulation(person1,person2);

        } else{
            System.out.println("Не удалось распознать команду");
        }


    }

    private static void inputData(Person person){//ввод характеристик
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

        person.setDamage();//установка урона
        person.setMentalHealth();//установка ментального здоровья
    }

}
