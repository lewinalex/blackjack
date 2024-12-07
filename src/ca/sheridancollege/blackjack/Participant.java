/*
Title: Console Blackjack in Java (21)
Author: Olson, K
Date: July 18, 2024
Availability: https://kevinsguides.com/guides/code/java/javaprojs/consoleblackjack/
 */
package ca.sheridancollege.blackjack;

//Use participant class as abstract class as dealer and player use similar commands
public abstract class Participant {

    private String participantID;

    //A constructor that allows you to set the participant's unique ID
    public Participant(String name) {
        participantID = name;
    }

    public String getParticipantID() {
        return participantID;
    }

    public void setParticipantID(String givenID) {
        participantID = givenID;
    }

    private Hand hand;
    private String name;

    //Create new participant
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

    //Check for blackjack in initial hand
    public boolean hasBlackjack() {
        return this.getHand().calculatedValue() == 21;
    }

    //Print cards in hand and hand value
    public void printHand() {
        System.out.println(this.name + "'s hand looks like this:");
        System.out.println(this.hand + "Valued at: " + this.hand.calculatedValue() + "\n");
    }

    //Add card to hand with hit
    public void hit(Deck deck, Deck discard) {
        this.hand.takeCardFromDeck(deck);
        System.out.println(this.name + " gets a card");
        this.printHand();

    }
}
