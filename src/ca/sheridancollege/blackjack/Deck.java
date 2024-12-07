/*
Title: Console Blackjack in Java (21)
Author: Olson, K
Date: July 18, 2024
Availability: https://kevinsguides.com/guides/code/java/javaprojs/consoleblackjack/
 */
package ca.sheridancollege.blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    //The group of cards, stored in an ArrayList
    private final ArrayList<Card> deck = new ArrayList<>();

    public Deck() {
        ArrayList<Card> discarded = deck;
    }

    public ArrayList<Card> getCards() {
        return deck;
    }

    //Deck constructor with to make a full deck
    public Deck(boolean makeDeck) {
        if (makeDeck) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Value value : Card.Value.values()) {
                }
            }
        }
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    // Shuffles the deck
    public void shuffle() {
        Collections.shuffle(deck);
    }

    //Removes a card from the deck
    public Card takeCard() {
        Card cardToTake = new Card(deck.get(0));
        deck.remove(0);
        return cardToTake;
    }

    public void emptyDeck() {
        deck.clear();
    }

    //Adds cards back to deck
    public void addCards(ArrayList<Card> cards) {
        deck.addAll(cards);
    }

    //Takes cards from used pile and puts them back into the deck
    public void reloadDeckFromDiscard(Deck discard) {
        this.addCards(discard.getCards());
        this.shuffle();
        discard.emptyDeck();
    }

}
