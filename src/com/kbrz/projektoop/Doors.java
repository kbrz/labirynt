package com.kbrz.projektoop;

import java.util.ArrayList;
import java.util.Iterator;

public class Doors
{
    private boolean isOpened;
    private int id;
    private Room [] connection = {null, null};

    private static int created = 0;
    public static ArrayList<Integer> doorsWithoutKey = new ArrayList<Integer>();

    public Doors(Room firstRoom)
    {
        id = created;
        created++;

        connection[0] = firstRoom;
        int chance = Chance.generator.nextInt(2);

        if(chance == 1)
            isOpened = true;
        else
        {
            isOpened = false;
            doorsWithoutKey.add(id);
        }
    }

    public int id() { return id; }

    public boolean open(Player player)
    {
        if(isOpened)
            return true;
        Iterator<Item> it = player.inventory().iterator();
        while(it.hasNext())
        {
            Item current = it.next();
            if(current.type().equals("Key") && current.name().equals("Key " + id))
            {
                isOpened = true;
                return true;
            }
        }
        return false;
    }

    public Room takeDoors(Room room)
    {
        String dir = "";
        if(connection[0] == room)
        {
            if(connection[1] == null)
            {
                if(this == room.northDoors())
                    dir = "north";
                else if(this == room.southDoors())
                    dir = "south";
                else if(this == room.eastDoors())
                    dir = "east";
                else if(this == room.westDoors())
                    dir = "west";
                Room connected = new Room(room, dir);
                connection[1] = connected;
                return connected;
            }
            return connection[1];
        }
        else
            return connection[0];
    }
}