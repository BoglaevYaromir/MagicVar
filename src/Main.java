public class Main{
    static void main() {
        Person a = new Person("Анатолий"); //создание двух персонажей
        a.printCharacter(); //вывод характеристик
        Person b = new Person("Евдаким");
        b.printCharacter();

        battle(a,b); //запуск сражения
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

        if (a.getHealth()<=0){
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
}