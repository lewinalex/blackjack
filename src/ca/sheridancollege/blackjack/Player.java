/*
Title: Console Blackjack in Java (21)
Author: Olson, K
Date: July 18, 2024
Availability: https://kevinsguides.com/guides/code/java/javaprojs/consoleblackjack/
 */
package ca.sheridancollege.blackjack;

/**
 * A class that models each Participant in the game. Participants have an identifier, which should be unique.
 *
 * @author dancye, 2018
 */
import java.util.Scanner;

public class Player extends Participant {

    private static final Scanner input = new Scanner(System.in); // Declare input scanner

    //Create a new Player
    public Player() {

        super.setName("Player");

    }

    public void makeDecision(Deck deck, Deck discard) {

        int decision = 0;
        boolean getNum = true;

        while (getNum) {
            System.out.println("Make a decision:\n1) Hit\n2) Stand");

            try {
                decision = input.nextInt();

                // Check if input is 1 or 2, if not, print an error and ask again
                if (decision == 1 || decision == 2) {
                    getNum = false;  // Valid input, exit the loop
                } else {
                    System.out.println("Invalid input. Please enter 1 to Hit or 2 to Stand.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter 1 to Hit or 2 to Stand.");
                input.next();  // Clear the invalid input
            }
            //we don't close the scanner, because we will need it later.
        }

        //if they decide to hit
        if (decision == 1) {
            //hit the deck using the deck and discard deck
            this.hit(deck, discard);
            //return (exit the method) if they have blackjack or busted
            if (this.getHand().calculatedValue() > 20) {
                return;
            } //if they didnt bust or get 21, allow them to decide to hit or stand again by going back to this same method
            else {
                this.makeDecision(deck, discard);
            }

            //if they type any number other than 1, we'll assume they're standing
        } else {
            System.out.println("You stand.");
        }
    }
}
