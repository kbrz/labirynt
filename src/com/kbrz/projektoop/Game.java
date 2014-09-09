package com.kbrz.projektoop;

import java.util.ArrayList;

public class Game
{
    public Player player;
    public StartingRoom startingRoom;
    public Room currentRoom;
    private static boolean created = false;
    private static Game game;

    private Game()
    {
        player = new Player(Dialog.getLine("Podaj swoje imie"));
        startingRoom = new StartingRoom();
        currentRoom = startingRoom;
    }

    public static Game instance()
    {
        if(!created)
        {
            game = new Game();
            created = true;
        }
        return game;
    }

    public void gameLoop()
    {
        Dialog.showMessage("ZNALAZLES SIE W LABIRYNCIE!\nPORUSZAJ SIE PO KOMNATACH UZYWAJAC DOSTEPNYCH KOMEND.\n");
        boolean gameOver = false;
        Pair<String, Integer> decision;
        ArrayList<String> options = new ArrayList<String>();

        while (!gameOver)
        {
            options.clear();
            options.add("rozejrzyj sie");
            options.add("statystyki postaci");
            options.add("pokaz inwentarz");
            if(currentRoom.seen)
            {
                options.add("przejdz przez drzwi");
                if(currentRoom.monster != null)
                    options.add("walcz z " + currentRoom.monster.name());
                options.add("otworz skrzynie");
            }
            options.add("zakoncz gre");

            decision = Dialog.ask("Co chcesz zrobic?", options);

            if(decision.getKey().equals("rozejrzyj sie"))
                Dialog.showMessage(currentRoom.info());
            else if(decision.getKey().equals("statystyki postaci"))
                Dialog.showMessage(player.info());
            else if(decision.getKey().equals("pokaz inwentarz"))
            {
                Dialog.showMessage(player.inventoryList());
                options = player.usableInventoryList();
                options.add("zadnego");
                decision = Dialog.ask("Ktorego przedmiotu chcesz uzyc?", options);

                if(!decision.getKey().equals("zadnego"))
                    player.useItem(decision.getKey());
            }
            else if(decision.getKey().equals("przejdz przez drzwi"))
            {
                options.clear();
                if (currentRoom.northDoors() != null)
                    options.add("polnocne");
                if (currentRoom.eastDoors() != null)
                    options.add("wschodnie");
                if (currentRoom.southDoors() != null)
                    options.add("poludniowe");
                if (currentRoom.westDoors() != null)
                    options.add("zachodnie");

                decision = Dialog.ask("Przez ktore drzwi chcesz przejsc?", options);

                if (decision.getKey().equals("polnocne"))
                {
                    if (currentRoom.northDoors().open(player))
                    {
                        Dialog.showMessage("Przechodzisz przez polnocne drzwi.");
                        currentRoom = currentRoom.northDoors().takeDoors(currentRoom);
                    }
                    else
                        Dialog.showMessage("Drzwi sa zamkniete na klucz.");
                }
                else if (decision.getKey().equals("wschodnie"))
                {
                    if (currentRoom.eastDoors().open(player))
                    {
                        Dialog.showMessage("Przechodzisz przez wschodnie drzwi.");
                        currentRoom = currentRoom.eastDoors().takeDoors(currentRoom);
                    }
                    else
                        Dialog.showMessage("Drzwi sa zamkniete na klucz.");
                }
                else if (decision.getKey().equals("poludniowe"))
                {
                    if (currentRoom.southDoors().open(player))
                    {
                        Dialog.showMessage("Przechodzisz przez poludniowe drzwi.");
                        currentRoom = currentRoom.southDoors().takeDoors(currentRoom);
                    }
                    else
                        Dialog.showMessage("Drzwi sa zamkniete na klucz.");
                }
                else if (decision.getKey().equals("zachodnie"))
                {
                    if (currentRoom.westDoors().open(player))
                    {
                        Dialog.showMessage("Przechodzisz przez zachodnie drzwi.");
                        currentRoom = currentRoom.westDoors().takeDoors(currentRoom);
                    }
                    else
                        Dialog.showMessage("Drzwi sa zamkniete na klucz.");
                }
            }
            else if(currentRoom.monster != null)
            {
                if (decision.getKey().equals("walcz z " + currentRoom.monster.name()))
                    if (!Fight.fight(player, currentRoom.monster))
                        gameOver = true;
            }
            else if(decision.getKey().equals("otworz skrzynie"))
            {
                options.clear();
                for(Item item: currentRoom.treasureChest)
                    options.add(item.name());
                options.add("zaden");


                if(currentRoom.treasureChest.size() > 0)
                {
                    Dialog.showMessage("W skrzyni znajduja sie przedmioty.");
                    decision = Dialog.ask("Ktory przedmiot chcesz wyciagnac?", options);

                    if(!decision.getKey().equals("zaden"))
                    {
                        player.collectItem(currentRoom.treasureChest.get(decision.getValue()));
                        currentRoom.treasureChest.remove(currentRoom.treasureChest.get(decision.getValue()));
                    }
                }
                else
                    Dialog.showMessage("Skrzynia jest pusta.");
            }
            else if(decision.getKey().equals("zakoncz gre"))
                gameOver = true;
            else
                Dialog.showMessage("Mozesz wybrac tylko jedna z wyswietlanych mozliwosci.");
        }
    }

    public static void main(String [] args)
    {
        Game gra = Game.instance();
        gra.gameLoop();
        Dialog.showMessage("Koniec gry.");
    }
}