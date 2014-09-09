package com.kbrz.projektoop;

import java.util.ArrayList;

public class Room
{
    protected boolean seen;
    protected Doors [] doorsTable = {null, null, null, null}; // N - S - E - W
    protected Monster monster = null;
    protected ArrayList<Item> treasureChest;

    public Room(Room roomFrom, String direction)
    {
        seen = false;

        if(direction.equals("north"))
            doorsTable[1] = roomFrom.northDoors();
        else if(direction.equals("south"))
            doorsTable[0] = roomFrom.southDoors();
        else if(direction.equals("east"))
            doorsTable[3] = roomFrom.eastDoors();
        else if(direction.equals("west"))
            doorsTable[2] = roomFrom.westDoors();

        int chance;
        for(int i=0; i<doorsTable.length; i++)
        {
            chance = Chance.generator.nextInt(2);
            if(chance == 1 && doorsTable[i] == null)
            {
                doorsTable[i] = new Doors(this);
            }
        }

        treasureChest = new ArrayList<Item>();
        if(Doors.doorsWithoutKey.size() > 0)
        {
            chance = Chance.generator.nextInt(Doors.doorsWithoutKey.size());
            treasureChest.add(new Item("Key", Doors.doorsWithoutKey.remove(chance).toString()));
        }

        chance = Chance.generator.nextInt(3);
        for(int i=0; i<chance; i++)
            treasureChest.add(Item.randomItem());

        chance = Chance.generator.nextInt(2);
        if(chance == 1)
            monster = Monster.randomMonster();

    }

    public Doors northDoors(){ return doorsTable[0]; }
    public Doors southDoors() { return doorsTable[1]; }
    public Doors eastDoors() { return doorsTable[2]; }
    public Doors westDoors() { return doorsTable[3]; }

    public int doorsNumber()
    {
        int number = 0;
        for(Doors doors: doorsTable)
            if(doors != null)
                number++;
        return number;
    }

    public String info()
    {
        String messageToShow = "";
        messageToShow += "W komnacie w ktorej sie znajdujesz widzisz " + this.doorsNumber() + " drzwi.\n";
        if(this.northDoors() != null)
            messageToShow += "Polnocne drzwi oznaczone sa numerem " + this.northDoors().id() + ".\n";
        if(this.eastDoors() != null)
            messageToShow += "Wschodnie drzwi oznaczone sa numerem " + this.eastDoors().id() + ".\n";
        if(this.southDoors() != null)
            messageToShow += "Poludniowe drzwi oznaczone sa numerem " + this.southDoors().id() + ".\n";
        if(this.westDoors() != null)
            messageToShow += "Zachodnie drzwi oznaczone sa numerem " + this.westDoors().id() + ".\n";
        if(treasureChest.size() > 0)
            messageToShow += "W rogu komnaty stoi skrzynia.\n";
        if(monster != null)
            messageToShow += "Po komnacie krzata sie " + monster.name() + ".";

        seen = true;
        return messageToShow;
    }
}