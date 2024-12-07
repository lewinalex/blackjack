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

    //Takes card from deck and adds to hand
    public void takeCardFromDeck(Deck deck) {
        hand.add(deck.takeCard());
    }

    //String to output cards in hand
    @Override
    public String toString() {
        String output = "";
        for (Card card : hand) {
            output += card;
        }
        return output;
    }

    //Calculates value of cards in hand
    public int calculatedValue() {
        int totalValue = 0;
        int aceCount = 0;

        // Sum card values and count Aces
        for (Card card : hand) {
            if (card.getValue() == Card.Value.ACE) {
                aceCount++;
                totalValue += 11; // Initially count Ace as 11
            } else {
                totalValue += card.getValue().getCardValue();
            }
        }

        // Adjust aces to be value of 1 if hand value exceeds 21
        while (totalValue > 21 && aceCount > 0) {
            totalValue -= 10;
            aceCount--;
        }

        return totalValue;
    }

    public Card getCard(int extraCard) {
        return hand.get(extraCard);
    }

    //Discard hand
    public void discardHandToDeck(Deck discardDeck) {
        discardDeck.addCards(hand);
        hand.clear();
    }
}
