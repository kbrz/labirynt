package com.kbrz.projektoop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Player extends Creature
{
    private int level;
    private int exp;
    private ArrayList<Item> inventory;

    public ArrayList<Item> inventory() { return inventory; }

    public Player(String newName)
    {
        name = newName;
        maxHealth = health = 200;
        maxMana = mana = 50;
        attack = 15;
        defence = 5;
        speed = 20;

        inventory = new ArrayList<Item>();
        inventory.add(new Item("Health Potion", "Small Health Potion"));
        inventory.add(new Item("Health Potion", "Small Health Potion"));
        inventory.add(new Item("Health Potion", "Small Health Potion"));
        inventory.add(new Item("Mana Potion", "Small Mana Potion"));
        inventory.add(new Item("Mana Potion", "Small Mana Potion"));
        inventory.add(new Item("Mana Potion", "Small Mana Potion"));
    }

    public int exp() { return exp; }
    public void addExp(int howMuch) { exp += howMuch; }
    public int level() { return level; }

    @Override
    public boolean useItem(Item usedItem)
    {
        if(!inventory.contains(usedItem))
            return false;

        usedItem.makeEffect(this);
        inventory.remove(usedItem);
        return true;
    }

    public boolean useItem(String usedItem)
    {
        Iterator<Item> it = inventory.iterator();
        while (it.hasNext())
        {
            Item item = it.next();
            if(item.name().equals(usedItem))
            {
                item.makeEffect(this);
                it.remove();
                return true;
            }
        }
        return false;
    }

    public void collectItem(Item collected)
    {
        inventory.add(collected);
    }

    public void levelUp()
    {
        exp = 0;
        level++;
        maxHealth += 20; health += 20;
        maxMana += 5; mana += 5;
        attack += 1;
        defence += 1;
        speed += 2;
    }

    @Override
    public String info()
    {
        String infoS = "";
        infoS += "Imie: " + name + "\n";
        infoS += "Poziom: " + level + "\n";
        infoS += "HP: " + health + "/" + maxHealth + ", ";
        infoS += "MP: " + mana + "/" + maxMana + "\n";
        infoS += "Atak: " + attack + ", Obrona: " + defence + "\n";
        infoS += "Ekwipunek: ";
        if(equipedWeapon != null)
        {
            infoS += equipedWeapon.name();
            if(equipedShield != null)
                infoS += ", " + equipedShield.name();
        }
        else if(equipedShield != null)
            infoS += equipedShield.name();
        else
            infoS += "brak";
        infoS += "\n";
        return infoS;
    }

    public ArrayList<String> battleInventoryList()
    {
        ArrayList<String> list = new ArrayList<String>();
        Iterator<Item> it = inventory.iterator();
        while (it.hasNext())
        {
            Item item = it.next();
            if(!item.type().equals("Key") && !item.type().equals("Weapon") && !item.type().equals("Shield"))
                if(!list.contains(item.name()))
                    list.add(item.name());
        }
        return list;
    }

    public ArrayList<String> inventoryList()
    {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Iterator<Item> it = inventory.iterator();
        while (it.hasNext())
        {
            Item item = it.next();
            if(map.containsKey(item.name()))
                map.put(item.name(), map.get(item.name())+1);
            else
                map.put(item.name(), 1);
        }

        ArrayList<String> list = new ArrayList<String>();

        Iterator itm = map.entrySet().iterator();
        while (itm.hasNext())
        {
            HashMap.Entry pairs = (HashMap.Entry)itm.next();
            list.add(pairs.getKey() + " x" + pairs.getValue());
            //it.remove();
        }

        return list;
    }

    public ArrayList<String> usableInventoryList()
    {
        ArrayList<String> list = new ArrayList<String>();
        Iterator<Item> it = inventory.iterator();
        while (it.hasNext())
        {
            Item item = it.next();
            if(!item.type().equals("Key"))
                if(!list.contains(item.name()))
                    list.add(item.name());
        }
        return list;
    }
}