public class Person {

    private String name;
    private short health;
    private short mentalHealth;
    private short strength;
    private short dexterity;
    private short iq;
    private short speed;
    private short reaction;
    private short energy;
    private short damage;

    public Person(){

    }

    public Person(String name){
        this.name = name;
        strength = (short) (Math.random()*96 +5); //максимальная сила 100, минимальная 5, нужна для урона, также определяет здоровье и скорость
        health = (short) (strength*10+Math.random()*strength*6-strength*3); //расчитывается как сила*10 +- до 30% силы, максимальное здоровье 1300, минимальное 35
        dexterity = (short) (Math.random()*200+1); //максимальная гибкость 200, используется при вычислении шанса уворота
        energy = (short) (Math.random()*10000+1); //максимальная энергия 10_000, нужна для урона и защиты
        iq = (short) (Math.random()*251 + 50); //максимальный IQ 300, минимальный 50, нужен для шанса уворота
        speed = (short) (Math.random()*201 + strength/2); //максимальная скорость 250, минимальная 2, нужна для урона и шанса уворота
        reaction = (short) (Math.random()*200+1); //максимальная реакция 200, нужна для шанса уворота
        mentalHealth = (short) ((health + strength + (energy/10) + iq + speed + reaction)/6);
        damage = (short)(strength + energy/100 + speed/10); //максимальный урон 225, минимальный 2
    }

    public Person(String name, short strength, short health, short dexterity, short energy, short iq, short speed, short reaction){
        this.name = name;
        this.strength = strength; //максимальная сила 100, минимальная 5, нужна для урона, также определяет здоровье и скорость
        this.health = health; //расчитывается как сила*10 +- до 30% силы, максимальное здоровье 1300, минимальное 35
        this.dexterity = dexterity; //максимальная гибкость 200, используется при вычислении шанса уворота
        this.energy = energy; //максимальная энергия 10_000, нужна для урона и защиты
        this.iq = iq; //максимальный IQ 300, минимальный 50, нужен для шанса уворота
        this.speed = speed; //максимальная скорость 250, минимальная 2, нужна для урона и шанса уворота
        this.reaction = reaction; //максимальная реакция 200, нужна для шанса уворота
        mentalHealth = (short) ((health + strength + (energy/10) + iq + speed + reaction)/6);
        damage = (short)(strength + energy/100 + speed/10); //максимальный урон 225, минимальный 2
    }

    public static void battle(Person a, Person b){
        double chanceDodgeA = (double) ((a.getDexterity() + a.getReaction()) / 2 + a.getIq() - b.getIq() - b.getSpeed()) /450; //вычисление шанса уворота персонажа (значение до 448/450)
        double chanceDodgeB = (double) ((b.getDexterity() + b.getReaction()) / 2 + b.getIq() - a.getIq() - a.getSpeed()) /450;

        while (a.getHealth()>0 && b.getHealth()>0) { //пока все живы
            System.out.println(); //перевод на новую строку для красоты

            a.setDamage(); //установление наносимого урона (надо из-за меняющейся энергии)
            b.setDamage();

            boolean DodgeA = chanceDodgeA>Math.random(); //результат уклонения
            boolean DodgeB = chanceDodgeB>Math.random();

            if (a.getSpeed()>b.getSpeed() || (a.getSpeed() == b.getSpeed() && Math.random()>0.5)){ //определение первой атаки
                if (!DodgeB){ //проверка уворота
                    attack(a,b);//установка нового здоровья и энергии
                    if (b.getHealth() <= 0){ //Если b мёртв
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

            else{
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
    private static void attack(Person attacker, Person defender){ //установка урона
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
    public void printCharacter(){ //вывод всей информации
        System.out.printf("Name = %s, Health = %d, Mental Health = %d, Strength = %d, IQ = %d, Speed = %d, Reaction = %d, Energy = %d\n", name, health,
                mentalHealth, strength, iq, speed, reaction, energy);
    }




    public void printHealthAndEnergy(){ //вывод здоровья и энергии
        System.out.printf("Name = %s, Health = %d, Energy = %d\n",name, health, energy);
    }

    public String getName() {
        return name;
    }

    public short getHealth(){
        return health;
    }
    public short getMentalHealth(){
        return mentalHealth;
    }

    public short getStrength() {
        return strength;
    }

    public short getDexterity() {
        return dexterity;
    }

    public short getIq() {
        return iq;
    }

    public short getSpeed() {
        return speed;
    }

    public short getEnergy() {
        return energy;
    }

    public short getReaction() {
        return reaction;
    }

    public short getDamage() {
        return damage;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setHealth(short health){
        this.health = health;
    }

    public void setReaction(short reaction) {
        this.reaction = reaction;
    }

    public void setEnergy(short energy) {
        this.energy = energy;
    }

    public void setIq(short iq) {
        this.iq = iq;
    }

    public void setMentalHealth() {
        this.mentalHealth = (short) ((health + strength + (energy/10) + iq + speed + reaction)/6);
    }

    public void setSpeed(short speed) {
        this.speed = speed;
    }

    public void setStrength(short strength) {
        this.strength = strength;
    }

    public void setDexterity(short dexterity) {
        this.dexterity = dexterity;
    }

    public void setDamage() {
        damage = (short)(strength + energy/100 + speed/10);
    }


}
