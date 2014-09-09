package com.kbrz.projektoop;


public class Monster extends Creature
{
    private int expGiven;

    public Monster(String monsterName)
    {
        name = monsterName;
        int statMod = 0;

        if(monsterName == "Earthworm")
            statMod = 0;
        else if(monsterName == "Goblin")
            statMod = 1;
        else if(monsterName == "Troll")
            statMod = 2;
        else if(monsterName == "Vampire")
            statMod = 3;
        else if(monsterName == "Dragon")
            statMod = 6;

        maxHealth = health = 100 + statMod*50;
        maxMana = mana = 0;
        attack = 15 + statMod*5;
        defence = 2 + statMod*5;
        speed = 20 + statMod*3;
        expGiven = 2 + statMod*3;
    }

    public int expGiven() { return expGiven; }

    public static Monster randomMonster()
    {
        int chance = Chance.generator.nextInt(100) + 1;
        String n;
        if(chance < 66)
            n = "Earthworm";
        else if(chance < 86)
            n = "Goblin";
        else if(chance < 96)
            n = "Vampire";
        else
            n = "Dragon";
        return new Monster(n);
    }
}