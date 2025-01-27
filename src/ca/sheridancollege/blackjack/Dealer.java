/*
Title: Console Blackjack in Java (21)
Author: Olson, K
Date: July 18, 2024
Availability: https://kevinsguides.com/guides/code/java/javaprojs/consoleblackjack/
 */
package ca.sheridancollege.blackjack;

public class Dealer extends Participant {

    
    public Dealer() {

        //Name the dealer "Dealer"
        super.setName("Dealer");

    }

    //Display first hand with only one card showing face up
    public void printFirstHand() {
        System.out.println("The dealer's hand looks like this:");
        System.out.print(super.getHand().getCard(0));
        System.out.println("The second card is face down.\n");
    }
}
