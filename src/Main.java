import java.util.Scanner;

public class Main{
    static void main() {

        Person a = new Person("Анатолий"); //создание двух персонажей
        a.printCharacter(); //вывод характеристик
        Person b = new Person("Евдаким");
        b.printCharacter();

        battle(a,b); //запуск сражения

        System.out.println("Приветствуем вас в нашем невероятном мире! Введите help, чтобы увидеть возможные команды");

        Scanner scanner = new Scanner(System.in);

        while (true){
            reader(scanner.next());
        }


    }
    public static void battle(Person a, Person b){
        double chanceDodgeA = (double) ((a.getDexterity() + a.getReaction()) / 2 + a.getIq() - b.getIq() - b.getSpeed()) /450; //вычисление шанса уворота персонажа (значение до 448/450)
        double chanceDodgeB = (double) ((b.getDexterity() + b.getReaction()) / 2 + b.getIq() - a.getIq() - a.getSpeed()) /450;

        while (a.getHealth()>0 & b.getHealth()>0) { //пока все живы
            System.out.println(); //перевод на новую строку для красоты

            a.setDamage(); //установление наносимого урона (надо из-за меняющейся энергии)
            b.setDamage();

            boolean DodgeA = chanceDodgeA>Math.random(); //результат уклонения
            boolean DodgeB = chanceDodgeB>Math.random();

            if (a.getSpeed()>b.getSpeed() || (a.getSpeed() == b.getSpeed() && Math.random()>0.5)){ //определение первой атаки
                if (!DodgeB){ //проверка уворота
                    attack(a,b);//установка нового здоровья и энергии
                    if (b.getHealth() <= 0){ //Если b жив
                        continue; //заканчиваем цикл
                    }
                }

                else{
                    System.out.printf("%s уклонился от атаки %s\n", b.getName(), a.getName()); //вывод уворота
                }

                if (!DodgeA){ //аналогично
                    attack(b,a);
                }

                else{
                    System.out.printf("%s уклонился от атаки %s\n", a.getName(), b.getName()); //вывод уворота
                }
            }

            else if (a.getSpeed()<b.getSpeed()){
                if (!DodgeA){
                    attack(b,a);
                    if (a.getHealth() <= 0){
                        continue;
                    }
                }

                else{
                    System.out.printf("%s уклонился от атаки %s\n", a.getName(), b.getName()); //вывод уворота
                }

                if (!DodgeB){
                    attack(a,b);
                }

                else{
                    System.out.printf("%s уклонился от атаки %s\n", b.getName(), a.getName()); //вывод уворота
                }
            }
        }

        if (a.getHealth()<=0){ //вывод победителя
            System.out.printf("%s победил\n", b.getName());
        }

        else{
            System.out.printf("%s победил\n", a.getName());
        }

    }

    public static void attack(Person attacker, Person defender){ //установка урона
        if (defender.getEnergy()==0){ //если энергия = 0
            System.out.printf("%s нанёс %d урона персонажу %s\n", attacker.getName(), attacker.getDamage(),defender.getName()); //вывод нанесённого урона
            defender.setHealth((short)(defender.getHealth()-(attacker.getDamage()))); //весь урон идёт на здоровье
        }

        else if (defender.getEnergy()-attacker.getDamage()/2 <= 0){ //если половина урона больше энергии
            System.out.printf("%s нанёс %d урона персонажу %s\n", attacker.getName(), attacker.getDamage()-defender.getEnergy(),defender.getName());
            defender.setHealth((short)(defender.getHealth()-(attacker.getDamage()-defender.getEnergy()))); // тратим урон на энергию, оставшийся на здоровье
            defender.setEnergy((short) 0);
        }

        else{
            System.out.printf("%s нанёс %d урона персонажу %s\n", attacker.getName(), attacker.getDamage() / 2,defender.getName());
            defender.setHealth((short)(defender.getHealth()-(attacker.getDamage())/2)); //половина урона на здоровье, половина на энергию
            defender.setEnergy((short)(defender.getEnergy()-(attacker.getDamage()/2)));
        }
        defender.printHealthAndEnergy(); //вывод здоровья и энергии защищающегося персонажа

    }

    public static void reader(String command){
        switch (command){
            case "help":
                System.out.println("Для сражения двух случайных персонажей введите \"random\"(1)\n" +
                        "Для сражения одного случайного и кастомного персонажа введите \"training\"(2)\n" +
                        "Для сражения двух кастомных персонажей введите \"fight\"(3)\n");

                break;
            case "1":
                Person a = new Person("Анатолий"); //создание двух персонажей
                a.printCharacter(); //вывод характеристик
                Person b = new Person("Евдаким");
                b.printCharacter();

                battle(a,b); //запуск сражения

                break;
            case "2":
                Person aa = new Person();
                inputDate(aa);

        }

    }

    public static void inputDate(Person person){
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