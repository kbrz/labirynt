package com.kbrz.projektoop;


class StartingRoom extends Room
{
    public StartingRoom()
    {
        super(null, "");
        monster = null;
        while (this.doorsNumber() < 2)
        {
            int chance;
            for(int i=0; i<doorsTable.length; i++)
            {
                if(doorsTable[i] == null)
                {
                    chance = Chance.generator.nextInt(2);
                    if(chance == 1)
                        doorsTable[i] = new Doors(this);
                }
            }
        }
    }
}