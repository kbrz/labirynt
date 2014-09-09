package com.kbrz.projektoop;

import java.util.ArrayList;

public abstract class Fight
{
    public static boolean fight(Player player, Monster monster)
    {
        Dialog.showMessage("Walka! " + player.name() + " vs. " + monster.name());

        int turn = 0;
        int monsterDamage = 0;
        int playerDamage = 0;
        int decision = 0;

        while (player.health() > 0 && monster.health() > 0)
        {
            turn++;
            Dialog.showMessage
                (
                    "Tura " + turn + "\n" +
                    player.name() + "[" + player.health() + "/" + player.maxHealth() + "]        " +
                    monster.name() + "[" + monster.health() + "/" + monster.maxHealth() + "]"
                );

            if(monster.speed() > player.speed() && turn == 1)
            {
                monsterDamage = monster.performAttack(player);
                if(monsterDamage < 0)
                    monsterDamage = 1;
                player.restoreHealth(-monsterDamage);

                Dialog.showMessage(monster.name() + " zaatakowal i zadal " + monsterDamage + " pkt. obrazen.");
            }

            ArrayList<String> options = new ArrayList<String>(); options.add("atakuj"); options.add("inwentarz");
            decision = Dialog.ask("Co chcesz zrobic?", options).getValue();

            if(decision == 0)
            {
                playerDamage = player.performAttack(monster);
                if(playerDamage < 0)
                    playerDamage = 1;
                monster.restoreHealth(-playerDamage);

                Dialog.showMessage(player.name() + " zaatakowal i zadal " + playerDamage + " pkt. obrazen.");
            }
            else
            {
                options = player.battleInventoryList();
                decision = Dialog.ask("Ktorego przedmiotu uzyc?", options).getValue();

                player.useItem(options.get(decision));

                Dialog.showMessage
                    (
                        player.name() + " uzyl przemiotu " + options.get(decision)
                    );
            }

            monsterDamage = monster.performAttack(player);
            if(monsterDamage < 0)
                monsterDamage = 1;
            player.restoreHealth(-monsterDamage);

            Dialog.showMessage(monster.name() + " zaatakowal i zadal " + monsterDamage + " pkt. obrazen.");
        }
        if(monster.health() <= 0)
        {
            Dialog.showMessage("Wygrales! Zdobyles " + monster.expGiven() + " pkt. doswiadczenia!");
            player.addExp(monster.expGiven());
            if(player.exp() > 20 + player.level()*player.level())
            {
                player.levelUp();
                Dialog.showMessage("Zdobyles " + player.level() + " poziom!");
            }
            return true;
        }
        else
        {
            Dialog.showMessage("Niestety, polegles.");
            return false;
        }
    }
}