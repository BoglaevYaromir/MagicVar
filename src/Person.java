public class Person {
    static private final short MIN_STRENGTH = 5;
    static private final short MAX_STRENGTH = 100;
    static private final short STRENGTH_RANGE = MAX_STRENGTH - MIN_STRENGTH + 1;

    static private final short MIN_HEALTH = 7;
    static private final short HEALTH_RANGE = 6;

    static private final short MIN_DEXTERITY = 1;
    static private final short MAX_DEXTERITY = 200;
    static private final short DEXTERITY_RANGE = MAX_DEXTERITY - MIN_DEXTERITY + 1;

    static private final short MIN_ENERGY = 1;
    static private final short MAX_ENERGY = 10_000;
    static private final short ENERGY_RANGE = MAX_ENERGY - MIN_ENERGY + 1;

    static private final short SPEED_RANGE = 201;
    static private final short SPEED_STRENGTH_DIVISOR = 2;

    static private final short MIN_REACTION = 1;
    static private final short MAX_REACTION = 200;
    static private final short REACTION_RANGE = MAX_REACTION - MIN_REACTION + 1;

    static private final short MENTAL_HEALTH_ENERGY_DIVISOR = 10;
    static private final short COUNT_CHARACTERISTIC_FOR_MENTAL_HEALTH = 7;

    static private final short DAMAGE_ENERGY_DIVISOR = 100;
    static private final short DAMAGE_SPEED_DIVISOR = 10;

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
        strength = (short) (MIN_STRENGTH + Math.random()*STRENGTH_RANGE);
        health = (short) (strength*(MIN_HEALTH + Math.random()*HEALTH_RANGE));
        dexterity = (short) (MIN_DEXTERITY + Math.random()*DEXTERITY_RANGE); //максимальная гибкость 200, используется при вычислении шанса уворота
        energy = (short) (MIN_ENERGY + Math.random()*ENERGY_RANGE); //максимальная энергия 10_000, нужна для урона и защиты
        iq = generateIQ(); //максимальный IQ 300, минимальный 50, нужен для шанса уворота
        speed = (short) ((short)(strength/SPEED_STRENGTH_DIVISOR) + Math.random()*SPEED_RANGE); //максимальная скорость 250, минимальная 2, нужна для урона и шанса уворота
        reaction = (short) (MIN_REACTION + Math.random()*REACTION_RANGE); //максимальная реакция 200, нужна для шанса уворота
        mentalHealth = (short) ((health + strength + (energy/MENTAL_HEALTH_ENERGY_DIVISOR) + iq + speed + reaction + dexterity)/COUNT_CHARACTERISTIC_FOR_MENTAL_HEALTH);
        damage = (short)(strength + energy/DAMAGE_ENERGY_DIVISOR + speed/DAMAGE_SPEED_DIVISOR); //максимальный урон 225, минимальный 2
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
        mentalHealth = (short) ((health + strength + (energy/MENTAL_HEALTH_ENERGY_DIVISOR) + iq + speed + reaction + dexterity)/COUNT_CHARACTERISTIC_FOR_MENTAL_HEALTH);
        damage = (short)(strength + energy/DAMAGE_ENERGY_DIVISOR + speed/DAMAGE_SPEED_DIVISOR); //максимальный урон 225, минимальный 2
    }



    public void printCharacter(){ //вывод всей информации
        System.out.printf("Name = %s, Health = %d, Mental Health = %d, Strength = %d, Dexterity = %d, IQ = %d, Speed = %d, Reaction = %d, Energy = %d\n", name, health,
                mentalHealth, strength, dexterity, iq, speed, reaction, energy);
    }




    public void printHealthAndEnergy(){ //вывод здоровья и энергии
        System.out.printf("Name = %s, Health = %d, Energy = %d\n",name, health, energy);
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public short getHealth(){
        return health;
    }
    public void setHealth(short health){
        this.health = health;
    }

    public short getMentalHealth(){
        return mentalHealth;
    }
    public void setMentalHealth() {
        this.mentalHealth = (short) ((health + strength + (energy/10) + iq + speed + reaction)/6);
    }

    public short getStrength() {
        return strength;
    }
    public void setStrength(short strength) {
        this.strength = strength;
    }

    public short getDexterity() {
        return dexterity;
    }
    public void setDexterity(short dexterity) {
        this.dexterity = dexterity;
    }

    public short getIq() {
        return iq;
    }
    public void setIq(short iq) {
        this.iq = iq;
    }

    public short getSpeed() {
        return speed;
    }
    public void setSpeed(short speed) {
        this.speed = speed;
    }

    public short getEnergy() {
        return energy;
    }
    public void setEnergy(short energy) {
        this.energy = energy;
    }

    public short getReaction() {
        return reaction;
    }
    public void setReaction(short reaction) {
        this.reaction = reaction;
    }

    public short getDamage() {
        return damage;
    }
    public void setDamage() {
        damage = (short)(strength + energy/100 + speed/10);
    }

    private short generateIQ(){
        final double RANDOM_NUMBER_FROM_PERCENT = Math.random()*100;

        final double PERCENT_VERY_LOW_IQ = 1.5;
        boolean isVeryLowIq = RANDOM_NUMBER_FROM_PERCENT < PERCENT_VERY_LOW_IQ;
        final short MIN_IQ = 50;
        final short VERY_LOW_IQ = 70;
        if (isVeryLowIq){
            final short RANGE_VERY_LOW_IQ = VERY_LOW_IQ - MIN_IQ;
            return (short)(MIN_IQ + Math.random()*RANGE_VERY_LOW_IQ);
        }

        final double PERCENT_LOW_IQ = PERCENT_VERY_LOW_IQ + 7;
        boolean isLowIq = RANDOM_NUMBER_FROM_PERCENT < PERCENT_LOW_IQ;
        final short LOW_IQ = 80;
        if (isLowIq){
            final short RANGE_LOW_IQ = LOW_IQ - VERY_LOW_IQ;
            return (short)(VERY_LOW_IQ + Math.random()*RANGE_LOW_IQ);
        }

        final double PERCENT_LOWER_MIDDLE_IQ = PERCENT_LOW_IQ + 16;
        boolean isLowerMiddleIq = RANDOM_NUMBER_FROM_PERCENT < PERCENT_LOWER_MIDDLE_IQ;
        final short LOWER_MIDDLE_IQ = 90;
        if (isLowerMiddleIq){
            final short RANGE_LOWER_MIDDLE_IQ = LOWER_MIDDLE_IQ - LOW_IQ;
            return (short)(LOW_IQ + Math.random()*RANGE_LOWER_MIDDLE_IQ);
        }

        final double PERCENT_MIDDLE_IQ = PERCENT_LOWER_MIDDLE_IQ + 50;
        boolean isMiddleIq = RANDOM_NUMBER_FROM_PERCENT < PERCENT_MIDDLE_IQ;
        final short MIDDLE_IQ = 110;
        if (isMiddleIq){
            final short RANGE_MIDDLE_IQ = MIDDLE_IQ - LOWER_MIDDLE_IQ;
            return (short)(LOWER_MIDDLE_IQ + Math.random()*RANGE_MIDDLE_IQ);
        }

        final double PERCENT_HIGHER_MIDDLE_IQ = PERCENT_MIDDLE_IQ + 16;
        boolean isHigherMiddleIq = RANDOM_NUMBER_FROM_PERCENT < PERCENT_HIGHER_MIDDLE_IQ;
        final short HIGHER_MIDDLE_IQ = 120;
        if (isHigherMiddleIq){
            final short RANGE_HIGHER_MIDDLE_IQ = HIGHER_MIDDLE_IQ - MIDDLE_IQ;
            return (short)(MIDDLE_IQ + Math.random()*RANGE_HIGHER_MIDDLE_IQ);
        }

        final double PERCENT_HIGH_IQ = PERCENT_HIGHER_MIDDLE_IQ + 7;
        boolean isHighIq = RANDOM_NUMBER_FROM_PERCENT < PERCENT_HIGH_IQ;
        final short HIGH_IQ = 130;
        if (isHighIq){
            final short RANGE_HIGH_IQ = HIGH_IQ - HIGHER_MIDDLE_IQ;
            return (short)(HIGHER_MIDDLE_IQ + Math.random()*RANGE_HIGH_IQ);
        }

        final short MAX_IQ = 300;
        final short RANGE_VERY_HIGH_IO = MAX_IQ - HIGH_IQ;
        return (short)(HIGH_IQ + Math.random()*RANGE_VERY_HIGH_IO);

    }
}
