public class Person {
    static private final short MIN_STRENGTH = 5;
    static private final short MAX_STRENGTH = 100;
    static private final short STRENGTH_RANGE = MAX_STRENGTH - MIN_STRENGTH + 1;

    static private final short MIN_HEALTH = 60;
    static private final short MAX_HEALTH = 1200;
    static private final short MIN_HEALTH_DIVISOR_STRENGTH = 7;
    static private final short HEALTH_RANGE = 6;

    static private final short MIN_DEXTERITY = 1;
    static private final short MAX_DEXTERITY = 200;
    static private final short DEXTERITY_RANGE = MAX_DEXTERITY - MIN_DEXTERITY + 1;

    static private final short MIN_ENERGY = 1;
    static private final short MAX_ENERGY = 10_000;
    static private final short ENERGY_RANGE = MAX_ENERGY - MIN_ENERGY + 1;

    static private final short MIN_SPEED = 2;
    static private final short MAX_SPEED = 250;
    static private final short SPEED_RANGE = 201;
    static private final short SPEED_STRENGTH_DIVISOR = 2;

    static private final short MIN_REACTION = 1;
    static private final short MAX_REACTION = 200;
    static private final short REACTION_RANGE = MAX_REACTION - MIN_REACTION + 1;

    static private final short MENTAL_HEALTH_ENERGY_DIVISOR = 10;
    static private final short COUNT_CHARACTERISTIC_FOR_MENTAL_HEALTH = 7;

    static private final short DAMAGE_ENERGY_DIVISOR = 100;
    static private final short DAMAGE_SPEED_DIVISOR = 10;

    static private final short MAX_STRENGTH_FOR_LEVEL_4 = 15;
    static private final short MAX_STRENGTH_FOR_LEVEL_3 = 30;
    static private final short MAX_STRENGTH_FOR_LEVEL_2 = 45;
    static private final short MAX_STRENGTH_FOR_LEVEL_1 = 65;
    static private final short MAX_STRENGTH_FOR_LEVEL_0 = MAX_STRENGTH;

    static private final short MAX_HEALTH_FOR_LEVEL_4 = 180;
    static private final short MAX_HEALTH_FOR_LEVEL_3 = 360;
    static private final short MAX_HEALTH_FOR_LEVEL_2 = 540;
    static private final short MAX_HEALTH_FOR_LEVEL_1 = 780;
    static private final short MAX_HEALTH_FOR_LEVEL_0 = MAX_HEALTH;

    static private final short MAX_DEXTERITY_FOR_LEVEL_4 = 30;
    static private final short MAX_DEXTERITY_FOR_LEVEL_3 = 60;
    static private final short MAX_DEXTERITY_FOR_LEVEL_2 = 90;
    static private final short MAX_DEXTERITY_FOR_LEVEL_1 = 130;
    static private final short MAX_DEXTERITY_FOR_LEVEL_0 = MAX_DEXTERITY;

    static private final short MAX_ENERGY_FOR_LEVEL_4 = 1500;
    static private final short MAX_ENERGY_FOR_LEVEL_3 = 3000;
    static private final short MAX_ENERGY_FOR_LEVEL_2 = 4500;
    static private final short MAX_ENERGY_FOR_LEVEL_1 = 6500;
    static private final short MAX_ENERGY_FOR_LEVEL_0 = MAX_ENERGY;

    static private final short MAX_SPEED_FOR_LEVEL_4 = 38;
    static private final short MAX_SPEED_FOR_LEVEL_3 = 75;
    static private final short MAX_SPEED_FOR_LEVEL_2 = 113;
    static private final short MAX_SPEED_FOR_LEVEL_1 = 163;
    static private final short MAX_SPEED_FOR_LEVEL_0 = MAX_SPEED;

    static private final short MAX_REACTION_FOR_LEVEL_4 = 30;
    static private final short MAX_REACTION_FOR_LEVEL_3 = 60;
    static private final short MAX_REACTION_FOR_LEVEL_2 = 90;
    static private final short MAX_REACTION_FOR_LEVEL_1 = 130;
    static private final short MAX_REACTION_FOR_LEVEL_0 = MAX_REACTION;


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
        health = (short) (strength*(MIN_HEALTH_DIVISOR_STRENGTH + Math.random()*HEALTH_RANGE));
        dexterity = (short) (MIN_DEXTERITY + Math.random()*DEXTERITY_RANGE); //максимальная гибкость 200, используется при вычислении шанса уворота
        energy = (short) (MIN_ENERGY + Math.random()*ENERGY_RANGE); //максимальная энергия 10_000, нужна для урона и защиты
        iq = generateIQ(); //максимальный IQ 300, минимальный 50, нужен для шанса уворота
        speed = (short) ((short)(strength/SPEED_STRENGTH_DIVISOR) + Math.random()*SPEED_RANGE); //максимальная скорость 250, минимальная 2, нужна для урона и шанса уворота
        reaction = (short) (MIN_REACTION + Math.random()*REACTION_RANGE); //максимальная реакция 200, нужна для шанса уворота
        setMentalHealth();
        setDamage(); //максимальный урон 225, минимальный 2
    }

    public Person(String name, byte level){
        this.name = name;
        setStrength(setCharacterForLevel(MIN_STRENGTH, MAX_STRENGTH_FOR_LEVEL_4,MAX_STRENGTH_FOR_LEVEL_3,MAX_STRENGTH_FOR_LEVEL_2,MAX_STRENGTH_FOR_LEVEL_1,MAX_STRENGTH_FOR_LEVEL_0,level));
        setHealth(setCharacterForLevel(MIN_HEALTH, MAX_HEALTH_FOR_LEVEL_4,MAX_HEALTH_FOR_LEVEL_3,MAX_HEALTH_FOR_LEVEL_2,MAX_HEALTH_FOR_LEVEL_1,MAX_HEALTH_FOR_LEVEL_0,level));
        setDexterity(setCharacterForLevel(MIN_DEXTERITY, MAX_DEXTERITY_FOR_LEVEL_4,MAX_DEXTERITY_FOR_LEVEL_3,MAX_DEXTERITY_FOR_LEVEL_2,MAX_DEXTERITY_FOR_LEVEL_1,MAX_DEXTERITY_FOR_LEVEL_0,level));
        setEnergy(setCharacterForLevel(MIN_ENERGY, MAX_ENERGY_FOR_LEVEL_4,MAX_ENERGY_FOR_LEVEL_3,MAX_ENERGY_FOR_LEVEL_2,MAX_ENERGY_FOR_LEVEL_1,MAX_ENERGY_FOR_LEVEL_0,level));
        setIq(generateIQ());
        setSpeed(setCharacterForLevel(MIN_SPEED, MAX_SPEED_FOR_LEVEL_4,MAX_SPEED_FOR_LEVEL_3,MAX_SPEED_FOR_LEVEL_2,MAX_SPEED_FOR_LEVEL_1,MAX_SPEED_FOR_LEVEL_0,level));
        setReaction(setCharacterForLevel(MIN_REACTION, MAX_REACTION_FOR_LEVEL_4,MAX_REACTION_FOR_LEVEL_3,MAX_REACTION_FOR_LEVEL_2,MAX_REACTION_FOR_LEVEL_1,MAX_REACTION_FOR_LEVEL_0,level));
        setMentalHealth();
        setDamage();
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
        setMentalHealth();
        setDamage();
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
        this.mentalHealth = (short)((health + strength + (energy/MENTAL_HEALTH_ENERGY_DIVISOR) + iq + speed + reaction + dexterity)/COUNT_CHARACTERISTIC_FOR_MENTAL_HEALTH);
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
        damage = (short)(strength + energy/DAMAGE_ENERGY_DIVISOR + speed/DAMAGE_SPEED_DIVISOR);
    }

    private short setCharacterForLevel(short MIN_CHARACTER,short MAX_CHARACTER_FOR_LEVEL_4, short MAX_CHARACTER_FOR_LEVEL_3, short MAX_CHARACTER_FOR_LEVEL_2, short MAX_CHARACTER_FOR_LEVEL_1, short MAX_CHARACTER_FOR_LEVEL_0, byte level){
        if (level == 4){
            final short RANGE_CHARACTER_LEVEL_4 = (short)(MAX_CHARACTER_FOR_LEVEL_4 - MIN_CHARACTER + 1);
            return (short)(MIN_CHARACTER + Math.random()*RANGE_CHARACTER_LEVEL_4);
        }
        else if (level == 3){
            final short RANGE_CHARACTER_LEVEL_3 =(short)(MAX_CHARACTER_FOR_LEVEL_3 - MAX_CHARACTER_FOR_LEVEL_4);
            return (short)(MAX_CHARACTER_FOR_LEVEL_4 + 1 + Math.random()*RANGE_CHARACTER_LEVEL_3);
        }
        else if (level == 2){
            final short RANGE_CHARACTER_LEVEL_2 =(short)(MAX_CHARACTER_FOR_LEVEL_2 - MAX_CHARACTER_FOR_LEVEL_3);
            return (short)(MAX_CHARACTER_FOR_LEVEL_3 + 1 + Math.random()*RANGE_CHARACTER_LEVEL_2);
        }
        else if (level == 1){
            final short RANGE_CHARACTER_LEVEL_1 =(short)(MAX_CHARACTER_FOR_LEVEL_1 - MAX_CHARACTER_FOR_LEVEL_2);
            return (short)(MAX_CHARACTER_FOR_LEVEL_2 + 1 + Math.random()*RANGE_CHARACTER_LEVEL_1);
        }
        else {
            final short RANGE_CHARACTER_LEVEL_0 =(short)(MAX_CHARACTER_FOR_LEVEL_0 - MAX_CHARACTER_FOR_LEVEL_1);
            return (short)(MAX_CHARACTER_FOR_LEVEL_1 + 1 + Math.random()*RANGE_CHARACTER_LEVEL_0);
        }
    }

    private short generateIQ(){
        final double RANDOM_NUMBER_FROM_PERCENT = Math.random()*100;

        final double PERCENT_VERY_LOW_IQ = 1.5;
        boolean isVeryLowIq = RANDOM_NUMBER_FROM_PERCENT < PERCENT_VERY_LOW_IQ;
        final short MIN_IQ = 50;
        final short MAX_VERY_LOW_IQ = 70;
        if (isVeryLowIq){
            final short RANGE_VERY_LOW_IQ = MAX_VERY_LOW_IQ - MIN_IQ + 1;
            return (short)(MIN_IQ + Math.random()*RANGE_VERY_LOW_IQ);
        }

        final double PERCENT_LOW_IQ = PERCENT_VERY_LOW_IQ + 7;
        boolean isLowIq = RANDOM_NUMBER_FROM_PERCENT < PERCENT_LOW_IQ;
        final short MAX_LOW_IQ = 80;
        if (isLowIq){
            final short RANGE_LOW_IQ = MAX_LOW_IQ - MAX_VERY_LOW_IQ;
            return (short)(MAX_VERY_LOW_IQ + 1 + Math.random()*RANGE_LOW_IQ);
        }

        final double PERCENT_LOWER_MIDDLE_IQ = PERCENT_LOW_IQ + 16;
        boolean isLowerMiddleIq = RANDOM_NUMBER_FROM_PERCENT < PERCENT_LOWER_MIDDLE_IQ;
        final short MAX_LOWER_MIDDLE_IQ = 90;
        if (isLowerMiddleIq){
            final short RANGE_LOWER_MIDDLE_IQ = MAX_LOWER_MIDDLE_IQ - MAX_LOW_IQ;
            return (short)(MAX_LOW_IQ + 1 + Math.random()*RANGE_LOWER_MIDDLE_IQ);
        }

        final double PERCENT_MIDDLE_IQ = PERCENT_LOWER_MIDDLE_IQ + 50;
        boolean isMiddleIq = RANDOM_NUMBER_FROM_PERCENT < PERCENT_MIDDLE_IQ;
        final short MAX_MIDDLE_IQ = 110;
        if (isMiddleIq){
            final short RANGE_MIDDLE_IQ = MAX_MIDDLE_IQ - MAX_LOWER_MIDDLE_IQ;
            return (short)(MAX_LOWER_MIDDLE_IQ + 1 + Math.random()*RANGE_MIDDLE_IQ);
        }

        final double PERCENT_HIGHER_MIDDLE_IQ = PERCENT_MIDDLE_IQ + 16;
        boolean isHigherMiddleIq = RANDOM_NUMBER_FROM_PERCENT < PERCENT_HIGHER_MIDDLE_IQ;
        final short MAX_HIGHER_MIDDLE_IQ = 120;
        if (isHigherMiddleIq){
            final short RANGE_HIGHER_MIDDLE_IQ = MAX_HIGHER_MIDDLE_IQ - MAX_MIDDLE_IQ;
            return (short)(MAX_MIDDLE_IQ + 1 + Math.random()*RANGE_HIGHER_MIDDLE_IQ);
        }

        final double PERCENT_HIGH_IQ = PERCENT_HIGHER_MIDDLE_IQ + 7;
        boolean isHighIq = RANDOM_NUMBER_FROM_PERCENT < PERCENT_HIGH_IQ;
        final short MAX_HIGH_IQ = 130;
        if (isHighIq){
            final short RANGE_HIGH_IQ = MAX_HIGH_IQ - MAX_HIGHER_MIDDLE_IQ;
            return (short)(MAX_HIGHER_MIDDLE_IQ + 1 + Math.random()*RANGE_HIGH_IQ);
        }

        final short MAX_IQ = 300;
        final short RANGE_VERY_HIGH_IQ = MAX_IQ - MAX_HIGH_IQ;
        return (short)(MAX_HIGH_IQ + 1 + Math.random()*RANGE_VERY_HIGH_IQ);

    }
}
