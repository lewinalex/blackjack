/*
Title: Console Blackjack in Java (21)
Author: Olson, K
Date: July 18, 2024
Availability: https://kevinsguides.com/guides/code/java/javaprojs/consoleblackjack/
 */
package ca.sheridancollege.blackjack;

import java.util.Scanner;

//Main class that handles the game logic
public class Game {

    Scanner scanner = new Scanner(System.in);
    private final Deck deck;
    private final Deck discarded;
    private final Rules rules;
    private final Dealer dealer;
    private final Player player;
    private final Score score;

    public static void main(String[] args) {
        System.out.println("Welcome to Blackjack!");
        Game game = new Game();
        game.startGame();

    }

    public Game() {

        //Create a new deck with 52 cards
        deck = new Deck(true);
        //Create a new empty deck for discarded cards
        discarded = new Deck();

        //Create the rules, dealer, player, and score
        rules = new Rules();
        dealer = new Dealer();
        player = new Player();
        score = new Score();
    }

    public void startGame() {
        //Player enters name
        System.out.println("Please enter your name: ");
        String playerName = scanner.nextLine();
        player.setName(playerName);
        boolean continuePlaying = true;

        //Initialize main menu
        continuePlaying = mainMenu(continuePlaying);
        while (continuePlaying) {
            startRound();

            score.displayScore(); // Display score

            String response = "";
            boolean validResponse = false;

            //Ask user if they would like to continue playing or not
            while (!validResponse) {
                System.out.println("\nWould you like to play another round? (Y/N)");
                response = scanner.nextLine();

                // Check for valid input (Y or N)
                if (response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("N")) {
                    validResponse = true;
                    if (response.equalsIgnoreCase("N")) {
                        continuePlaying = false;
                        //Game ends
                        System.out.println("Thanks for playing " + playerName + "!");
                    }
                } else {
                    System.out.println("Invalid input. Please enter 'Y' for Yes or 'N' for No.");
                }
            }
        }
        //Display the final score
        score.displayScore();
        scanner.close();
    }

    //Main menu displayed at game start
    public boolean mainMenu(boolean continuePlaying) {
        boolean continueGame = true;

        while (continueGame) {
            System.out.println("Please select an option:");
            System.out.println("1) View Rules");
            System.out.println("2) Start Round");
            System.out.println("3) Quit Game");

            String choice = scanner.nextLine();

            switch (choice) {
                
                //Show the rules
                case "1":
                    viewRules(); 
                    break;
                    
                // Start a new round
                case "2":
                    startRound(); 
                    continueGame = false;
                    continuePlaying = true;
                    break;
                    
                //Quit the game
                case "3":
                    System.out.println("Thanks for playing! Goodbye.");
                    continueGame = false;
                    continuePlaying = false;
                    break;
                    
                //Must enter valid input
                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
                    break;
            }
        }
        return continuePlaying;
    }

    //Retrieve and display the rules from the Rules class
    public void viewRules() {
        System.out.println("\n--- Blackjack Rules ---");
        rules.displayRules();
        System.out.println("-------------------------\n");
    }

    //This handles the logic for each round
    private void startRound() {

        //Ensure deck is full with 52 cards
        dealer.getHand().discardHandToDeck(discarded);
        player.getHand().discardHandToDeck(discarded);
        deck.reloadDeckFromDiscard(discarded);

        //Give the player and dealer two cards
        dealer.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);

        //Print their hands
        dealer.printFirstHand();
        player.printHand();

        //Check if dealer has blackjack
        if (dealer.hasBlackjack()) {
            dealer.printHand();
            if (player.hasBlackjack()) {
                System.out.println("You both have 21 - Tie!");
                score.addTie();
            } else {
                System.out.println("Dealer has BlackJack. You lose.");
                score.addLoss();
            }
            return;  //End the round
        }

        //Check if player has Blackjack
        if (player.hasBlackjack()) {
            dealer.printHand();
            System.out.println("You have Blackjack! You win!");
            score.addWin();
            score.addBlackjack();
            return;  //End the round
        }

        //Player chooses hit or stand
        player.makeDecision(deck, discarded);
        
        //Checks players hand value for bust
        if (player.getHand().calculatedValue() > 21) {
            System.out.println("You have gone over 21. You lose.");
            score.addLoss();
            score.incrementRounds();
            return; //End the round
        }

        //Show dealers hand
        dealer.printHand();
        while (dealer.getHand().calculatedValue() < 17) {
            dealer.hit(deck, discarded);
        }

        //Decide a result
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
