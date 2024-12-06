/*
Title: Console Blackjack in Java (21)
Author: Olson, K
Date: July 18, 2024
Availability: https://kevinsguides.com/guides/code/java/javaprojs/consoleblackjack/
 */
package ca.sheridancollege.blackjack;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> hand = new ArrayList<>();

    public void takeCardFromDeck(Deck deck) {
        hand.add(deck.takeCard());
    }

    @Override
    public String toString() {
        String output = "";
        for (Card card : hand) {
            output += card;
        }
        return output;
    }

    public int calculatedValue() {
        int totalValue = 0;
        int aceCount = 0;

        // Sum card values and count Aces
        for (Card card : hand) {
            if (card.getValue() == Card.Value.ACE) { // Assuming Ace has value 11 by default
                aceCount++;
                totalValue += 11; // Initially count Ace as 11
            } else {
                totalValue += card.getValue().getCardValue(); // Add card value
            }
        }

        // Adjust for Aces if totalValue exceeds 21
        while (totalValue > 21 && aceCount > 0) {
            totalValue -= 10; // Change an Ace from 11 to 1
            aceCount--;
        }

        return totalValue;
    }

//    public int calculatedValue() {
//
//        //variable to count number of aces, and current total value
//        int value = 0;
//        int aceCount = 0;
//
//        //For each card in this hand
//        for (Card card : hand) {
//            //Add the card value to the hand
//            value += card.getValue().getCardValue();
//            //Count how many aces have been added
//            if (card.getValue() == Card.Value.ACE) {
//                aceCount++;
//            }
//        }
//        //if we have a scenario where we have multiple aces, as may be the case of drawing 10, followed by two or more aces, (10+11+1 > 21)
//        //go back and set each ace to 1 until get back under 21, if possible
//        if (value > 21 && aceCount > 0) {
//            while (aceCount > 0 && value > 21) {
//                value -= 10;
//                aceCount--;
//            }
//        }
//        return value;
//
//    }
    public Card getCard(int idx) {
        return hand.get(idx);
    }

    public void discardHandToDeck(Deck discardDeck) {

        //copy cards from hand to discardDeck
        discardDeck.addCards(hand);

        //clear the hand
        hand.clear();

    }
}
