import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleInputReader {

    public static void reader(String command){
        Pattern patternHelp = Pattern.compile("^[Hh]elp\s*");
        Matcher matcherHelp = patternHelp.matcher(command);

        Pattern patternRandom = Pattern.compile("^([Rr]andom|1)\s*");
        Matcher matcherRandom = patternRandom.matcher(command);

        Pattern patternTraining = Pattern.compile("^([Tt]raining|2)\s*");
        Matcher matcherTraining = patternTraining.matcher(command);

        Pattern patternFight = Pattern.compile("^([Ff]ight|3)\s*");
        Matcher matcherFight = patternFight.matcher(command);

        if (matcherHelp.matches()){
            System.out.println("Для сражения двух случайных персонажей введите \"random\"(1)\n" +
                    "Для сражения одного случайного и кастомного персонажа введите \"training\"(2)\n" +
                    "Для сражения двух кастомных персонажей введите \"fight\"(3)\n");
        }
        else if (matcherRandom.matches()){
            Person a = new Person("Анатолий"); //создание двух персонажей
            a.printCharacter(); //вывод характеристик
            Person b = new Person("Евдаким");
            b.printCharacter();

            Person.battle(a,b); //запуск сражения
        } else if (matcherTraining.matches()) {
            Person a = new Person();
            inputDate(a);
            Person b = new Person("Кирилл");

            a.printCharacter();
            b.printCharacter();

            Person.battle(a,b);
        } else if (matcherFight.matches()) {
            Person a = new Person();
            inputDate(a);
            Person b = new Person();
            inputDate(b);

            a.printCharacter();
            b.printCharacter();

            Person.battle(a,b);

        }

        else{
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
