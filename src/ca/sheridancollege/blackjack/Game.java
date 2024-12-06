/*
Title: Console Blackjack in Java (21)
Author: Olson, K
Date: July 18, 2024
Availability: https://kevinsguides.com/guides/code/java/javaprojs/consoleblackjack/
 */
package ca.sheridancollege.blackjack;

import java.util.Scanner;

public class Game {

    Scanner scanner = new Scanner(System.in);
    private final Deck deck;
    private final Deck discarded;
    private final Rules rules;
    private final Dealer dealer;
    private final Player player;
    private final Score score;

    public static void main(String[] args) {
        //Say hi to the user
        System.out.println("Welcome to Blackjack!");
        //Declare variables needed for Game class
        Game game = new Game();
        game.startGame(); // Start the game loop

    }

    public Game() {

        //Create a new deck with 52 cards
        deck = new Deck(true);
        //Create a new empty deck
        discarded = new Deck();

        //Create the People
        rules = new Rules();
        dealer = new Dealer();
        player = new Player();
        score = new Score(); // Initialize the Score object
    }

    public void startGame() {
        System.out.println("Please enter your name: ");
        String playerName = scanner.nextLine();
        player.setName(playerName);
        boolean continuePlaying = true;

        continuePlaying = mainMenu(continuePlaying);
        while (continuePlaying) {
            startRound();

            score.displayScore(); // Display the final score

            String response = "";  // Initialize response variable
            boolean validResponse = false;  // Flag for valid input

            while (!validResponse) {
                System.out.println("\nWould you like to play another round? (Y/N)");
                response = scanner.nextLine();

                // Check for valid input (Y or N)
                if (response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("N")) {
                    validResponse = true;  // Exit loop if input is valid
                    if (response.equalsIgnoreCase("N")) {
                        continuePlaying = false;  // Exit the game loop
                        System.out.println("Thanks for playing " + playerName + "!");

                    }
                } else {
                    System.out.println("Invalid input. Please enter 'Y' for Yes or 'N' for No.");
                }
            }
        }
        score.displayScore(); // Display the final score
        scanner.close();
    }

    public boolean mainMenu(boolean continuePlaying) {
        boolean continueGame = true;

        while (continueGame) {
            System.out.println("Welcome to Blackjack!");
            System.out.println("Please select an option:");
            System.out.println("1) View Rules");
            System.out.println("2) Start Round");
            System.out.println("3) Quit Game");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewRules(); // Show the rules
                    break;

                case "2":
                    startRound(); // Start a new round
                    continueGame = false;
                    continuePlaying = true; // Return true to continue the game after the round
                    break;
                case "3":
                    System.out.println("Thanks for playing! Goodbye.");
                    continueGame = false;
                    continuePlaying = false; // Return false to quit the game
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
                    break;
            }
        }
        return continuePlaying;
    }

    // Retrieve and display the rules from the Rules class
    public void viewRules() {
        System.out.println("\n--- Blackjack Rules ---");
        rules.displayRules();  // Call the Rules class method to display rules
        System.out.println("-------------------------\n");
    }

    //This  method will handle the logic for each round
    private void startRound() {

        dealer.getHand().discardHandToDeck(discarded);
        player.getHand().discardHandToDeck(discarded);
        deck.reloadDeckFromDiscard(discarded);

        //Give the dealer two cards
        dealer.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);

        //Give the player two cards
        player.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);

        //Print their hands
        dealer.printFirstHand();
        player.printHand();

        if (dealer.hasBlackjack()) {
            dealer.printHand();
            if (player.hasBlackjack()) {
                System.out.println("You both have 21 - Tie!");
                score.addTie();
            } else {
                System.out.println("Dealer has BlackJack. You lose.");
                score.addLoss();
            }
            return;  // End the round
        }

        // Check if player has Blackjack
        if (player.hasBlackjack()) {
            dealer.printHand();
            System.out.println("You have Blackjack! You win!");
            score.addWin();
            score.addBlackjack();
            return;  // End the round
        }

        player.makeDecision(deck, discarded);
        if (player.getHand().calculatedValue() > 21) {
            System.out.println("You have gone over 21. You lose.");
            //count the losses
            score.addLoss();
            score.incrementRounds();  // Increment rounds played
            //start the round over
            return;
        }

        dealer.printHand();
        while (dealer.getHand().calculatedValue() < 17) {
            dealer.hit(deck, discarded);
        }

        if (dealer.getHand().calculatedValue() > 21) {
            System.out.println("Dealer busts. You win!");
            score.addWin();
        } else if (dealer.getHand().calculatedValue() > player.getHand().calculatedValue()) {
            System.out.println("You lose.");
            score.addLoss();
        } else if (player.getHand().calculatedValue() > dealer.getHand().calculatedValue()) {
            System.out.println("You win.");
            score.addWin();
        } else {
            System.out.println("Tie.");
            score.addTie();
        }
    }

}
