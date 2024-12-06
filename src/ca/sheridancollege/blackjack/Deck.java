/*
Title: Console Blackjack in Java (21)
Author: Olson, K
Date: July 18, 2024
Availability: https://kevinsguides.com/guides/code/java/javaprojs/consoleblackjack/
 */
package ca.sheridancollege.blackjack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author dancye
 */
public class Deck {

    //The group of cards, stored in an ArrayList
    private final ArrayList<Card> deck = new ArrayList<>();

    public Deck() {
        ArrayList<Card> newDeck = deck;
    }
    
    public ArrayList<Card> getCards() {
        return deck;
    }

    // Constructor for Deck with an optional flag to create a full deck
    public Deck(boolean makeDeck) {
        // Check if we need to create a full deck
        if (makeDeck) {
            // Go through all the suits
            for (Card.Suit suit : Card.Suit.values()) {
                // Go through all the ranks
                for (Card.Value value : Card.Value.values()) {
                    // Add a new card containing each iteration's suit and rank
                    deck.add(new Card(suit, value)); // Use StandardCard instead of Card
                }
            }
        }
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    // Getter method to retrieve the deck
    public ArrayList<Card> getDeck() {
        return deck;
    }

    // Shuffle method
    public void shuffle() {
        Collections.shuffle(deck);
    }

    // Optional: You could implement a method to display the deck
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Card card : deck) {
            output.append(card).append("\n");
        }
        return output.toString();
    }

    public Card takeCard() {

        //Take a copy of the first card from the deck
        Card cardToTake = new Card(deck.get(0));
        //Remove the card from the deck
        deck.remove(0);
        //Give the card back
        return cardToTake;

    }

    public boolean hasCards() {
        if (!deck.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public void emptyDeck() {
        deck.clear();
    }

    /**
     *
     * @param cards an arraylist of cards to be added to this deck
     */
    public void addCards(ArrayList<Card> cards) {
        deck.addAll(cards);
    }

    /**
     * Take all the cards from a discarded deck and place them in this deck, shuffled. Clear the old deck
     *
     * @param discard - the deck we're getting the cards from
     */
    public void reloadDeckFromDiscard(Deck discard) {
        this.addCards(discard.getCards());
        this.shuffle();
        discard.emptyDeck();
    }
   
}//end class
