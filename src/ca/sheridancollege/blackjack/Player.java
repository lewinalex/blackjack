/*
Title: Console Blackjack in Java (21)
Author: Olson, K
Date: July 18, 2024
Availability: https://kevinsguides.com/guides/code/java/javaprojs/consoleblackjack/
 */
package ca.sheridancollege.blackjack;

import java.util.Scanner;

//Extends from participant as that is an abstract class
public class Player extends Participant {

    private static final Scanner input = new Scanner(System.in);

    //Create a new Player
    public Player() {
        super.setName("Player");
    }

    //Controls the players choices during their turn
    public void makeDecision(Deck deck, Deck discard) {

        int decision = 0;
        boolean getNum = true;

        while (getNum) {
            System.out.println("Make a decision:\n1) Hit\n2) Stand");

            try {
                decision = input.nextInt();

                // Check if input is 1 or 2. If neither, print an error and ask again
                if (decision == 1 || decision == 2) {
                    getNum = false;
                } else {
                    System.out.println("Invalid input. Please enter 1 to Hit or 2 to Stand.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter 1 to Hit or 2 to Stand.");
                input.next();
            }
        }

        //If hit is chosen
        if (decision == 1) {
            this.hit(deck, discard);
            if (this.getHand().calculatedValue() > 20) {
                return;
            }
            else {
                this.makeDecision(deck, discard);
            }
        } else
        //If stand is chosen
        {
            System.out.println("You stand.");
        }
    }
}
