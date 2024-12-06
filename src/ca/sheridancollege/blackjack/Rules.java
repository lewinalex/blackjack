/*
Title: Console Blackjack in Java (21)
Author: Olson, K
Date: July 18, 2024
Availability: https://kevinsguides.com/guides/code/java/javaprojs/consoleblackjack/
 */
package ca.sheridancollege.blackjack;

public class Rules {

    public void displayRules() {
        System.out.println("1. The goal is to get as close to 21 points without exceeding it.");
        System.out.println("2. The player and dealer are each dealt two cards.");
        System.out.println("3. The player can choose to 'Hit' (take another card) or 'Stand' (keep their current hand).");
        System.out.println("4. Face cards (Jack, Queen, King) are worth 10 points.");
        System.out.println("5. Aces can be worth 1 or 11 points.");
        System.out.println("6. The dealer must hit until they have at least 17 points.");
        System.out.println("7. If the player exceeds 21 points, they bust and lose.");
        System.out.println("8. If the dealer exceeds 21 points, the player wins.");
        System.out.println("9. If both the player and dealer have the same value, it’s a tie.");
    }
}
