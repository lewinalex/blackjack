/*
Title: Console Blackjack in Java (21)
Author: Olson, K
Date: July 18, 2024
Availability: https://kevinsguides.com/guides/code/java/javaprojs/consoleblackjack/
 */
package ca.sheridancollege.blackjack;

public class Card {
    // Card suits
    public enum Suit {
        HEARTS,
        DIAMONDS,
        SPADES,
        CLUBS;
    }

    //Cards with values
    public enum Value {
        ACE("Ace", 11),
        TWO("Two", 2),
        THREE("Three", 3),
        FOUR("Four", 4),
        FIVE("Five", 5),
        SIX("Six", 6),
        SEVEN("Seven", 7),
        EIGHT("Eight", 8),
        NINE("Nine", 9),
        TEN("Ten", 10),
        JACK("Jack", 10),
        QUEEN("Queen", 10),
        KING("King", 10);

        private final String cardName;
        private final int cardValue;

        Value(String cardName, int cardValue) {
            this.cardName = cardName;
            this.cardValue = cardValue;
        }

        public int getCardValue() {
            return cardValue;
        }

        @Override
        public String toString() {
            return cardName;
        }
    }

    private final Suit suit;
    private final Value value;

    //Card constructor
    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    //Card Constructor
    public Card(Card card) {
        this.suit = card.getSuit();
        this.value = card.getValue();
    }

    //String formatted for cards and their values
    @Override
    public String toString() {
        return String.format("[%-5s of %-8s] (%2d)%n", value, suit, value.getCardValue());
    }

}
