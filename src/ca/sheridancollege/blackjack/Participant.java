/*
Title: Console Blackjack in Java (21)
Author: Olson, K
Date: July 18, 2024
Availability: https://kevinsguides.com/guides/code/java/javaprojs/consoleblackjack/
 */
package ca.sheridancollege.blackjack;

/**
 *
 * @author alewi
 */
public abstract class Participant {

    private String participantID; //the unique ID for this participant

    /**
     * A constructor that allows you to set the participant's unique ID
     *
     * @param name the unique ID to assign to this participant.
     */
    public Participant(String name) {
        participantID = name;
    }

    /**
     * @return the participantID
     */
    public String getParticipantID() {
        return participantID;
    }

    /**
     * Ensure that the participantID is unique
     *
     * @param givenID the participantID to set
     */
    public void setParticipantID(String givenID) {
        participantID = givenID;
    }

    /**
     * The method to be instantiated when you subclass the Participant class with your specific type of Participant and
     * filled in with logic to play your game.
     */
    private Hand hand;
    private String name;

    /**
     * Create a new Person
     */
    public Participant() {
        this.hand = new Hand();
        this.name = "";
    }

    public Hand getHand() {
        return this.hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public boolean hasBlackjack() {
        return this.getHand().calculatedValue() == 21;
    }

    public void printHand() {
        System.out.println(this.name + "'s hand looks like this:");
        System.out.println(this.hand + "Valued at: " + this.hand.calculatedValue() + "\n");
    }

    public void hit(Deck deck, Deck discard) {

        //If there's no cards left in the deck
        if (!deck.hasCards()) {
            deck.reloadDeckFromDiscard(discard);
        }
        this.hand.takeCardFromDeck(deck);
        System.out.println(this.name + " gets a card");
        this.printHand();

    }
}
