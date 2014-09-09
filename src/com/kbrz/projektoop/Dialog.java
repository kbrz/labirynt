package com.kbrz.projektoop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public abstract class Dialog
{
    public static Scanner reader = new Scanner(System.in);

    public static void showMessage(String message)
    {
        System.out.println(message);
    }

    public static void showMessage(ArrayList list)
    {
        Iterator it = list.iterator();
        while (it.hasNext())
            System.out.println(it.next());
    }

    public static Pair<String, Integer> ask(String message, ArrayList<String> options)
    {
        System.out.println(message);

        for(String option: options)
            System.out.print("[" + option + "] ");

        int decision = -1;
        String input = "";

        while (decision == -1)
        {
            System.out.print("\nDecyzja: ");

            input = reader.nextLine();
            input.replaceAll("\n", "");

            for(int i=0; i<options.size(); i++)
            {
                if(options.get(i).equals(input))
                {
                    decision = i;
                    break;
                }
            }

            if(decision == -1)
                System.out.print("\nMuisz wybrac jedna z dostepnych opcji.");
        }

        return new Pair<String, Integer>(input, decision);
    }

    public static String getLine(String message)
    {
        System.out.print(message + ": ");
        String input = reader.nextLine();
        input.replaceAll("\n", "");
        return input;
    }
}