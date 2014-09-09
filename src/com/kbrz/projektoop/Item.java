package com.kbrz.projektoop;


public class Item
{
    protected String itemName;
    protected String itemType;
    protected int attackPoints = 0;
    protected int defencePoints = 0;
    protected int restoreAmount = 0;
    protected int keyId = 0;

    public String name() { return itemName; }
    public String type() { return itemType; }

    public Item(String newType, String newName)
    {
        itemType = newType;
        itemName = newName;

        if(itemType.equals("Health Potion"))
        {
            if(itemName.equals("Small Health Potion"))
                restoreAmount = 200;
            else if(itemName.equals("Great Health Potion"))
                restoreAmount = 1000;
        }
        else if(itemType.equals("Mana Potion"))
        {
            if(itemName.equals("Small Mana Potion"))
                restoreAmount = 50;
            else if(itemName.equals("Great Mana Potion"))
                restoreAmount = 250;
        }
        else if(itemType.equals("Weapon"))
        {
            if(itemName.equals("Sword"))
                attackPoints = 10;
            else if(itemName.equals("Axe"))
                attackPoints = 15;
            else  if(itemName.equals("Great Sword"))
                attackPoints = 20;
            else if(itemName.equals("Great Axe"))
                attackPoints = 25;
        }
        else if(itemType.equals("Shield"))
        {
            if(itemName.equals("Wooden Shield"))
                defencePoints = 10;
            else if(itemName.equals("Steel Shield"))
                defencePoints = 15;
            else if(itemName.equals("Kevlar Shield"))
                defencePoints = 20;
            else if(itemName.equals("Mithril Shield"))
                defencePoints = 25;
        }
        else if(itemType.equals("Key"))
        {
            keyId = Integer.parseInt(itemName);
            itemName = "Key " + itemName;
        }
    }

    public void makeEffect(Creature target)
    {
        if(itemType.equals("Weapon"))
            target.changeWeapon(itemName);
        else if(itemType.equals("Shield"))
            target.changeShield(itemName);
        else if(itemType.equals("Health Potion"))
            target.restoreHealth(restoreAmount);
        else if(itemType.equals("Mana Potion"))
            target.restoreMana(restoreAmount);
        else if(itemType.equals("Key"))
            return; //TODO
    }

    public int points()
    {
        if(itemType.equals("Weapon"))
            return  attackPoints;
        else if(itemType.equals("Shield"))
            return defencePoints;
        else if(itemType.equals("Health Potion") || itemType.equals("Mana Potion"))
            return restoreAmount;
        else if(itemType.equals("Key"))
            return keyId;
        return -1;
    }

    public static Item randomItem()
    {
        String type;
        String name;
        int chance = Chance.generator.nextInt(100) + 1;
        if(chance < 81)
        {
            chance = Chance.generator.nextInt(100) + 1;
            if(chance < 66)
            {
                type = "Health Potion";
                chance = Chance.generator.nextInt(100) + 1;
                if(chance < 81)
                    name = "Small Health Potion";
                else
                    name = "Great Health Potion";
            }
            else
            {
                type = "Mana Potion";
                chance = Chance.generator.nextInt(100) + 1;
                if(chance < 81)
                    name = "Small Mana Potion";
                else
                    name = "Great Mana Potion";
            }
        }
        else if(chance < 91)
        {
            type = "Weapon";
            chance = Chance.generator.nextInt(100) + 1;
            if(chance < 61)
                name = "Sword";
            else if(chance < 86)
                name = "Axe";
            else if(chance < 96)
                name = "Great Sword";
            else
                name = "Great Axe";
        }
        else
        {
            type = "Shield";
            chance = Chance.generator.nextInt(100) + 1;
            if(chance < 61)
                name = "Wooden Shield";
            else if(chance < 86)
                name = "Steel Shield";
            else if(chance < 96)
                name = "Kevlar Shield";
            else
                name = "Mithril Shield";
        }
        return new Item(type, name);
    }
}