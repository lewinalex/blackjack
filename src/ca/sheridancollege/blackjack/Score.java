/*
    Title: Console Blackjack in Java (21)
    Author: Olson, K
    Date: July 18, 2024
    Availability: https://kevinsguides.com/guides/code/java/javaprojs/consoleblackjack/
 */
package ca.sheridancollege.blackjack;

public class Score {

    private int totalRounds;
    private int wins;
    private int ties;
    private int losses;
    private int winStreak;
    private int blackjacks;

    // Constructor
    public Score() {
        totalRounds = 0;
        wins = 0;
        ties = 0;
        losses = 0;
        winStreak = 0;
        blackjacks = 0;
    }

    // Method to increase total rounds
    public void incrementRounds() {
        totalRounds++;
    }

    // Method to handle player Blackjack
    public void addBlackjack() {
        blackjacks++;
    }

    // Method to increase win count and win streak
    public void addWin() {
        wins++;
        winStreak++;
    }

    // Method to handle a tie
    public void addTie() {
        ties++;
        winStreak = 0;  // Reset win streak after a tie
    }

    // Method to increase loss count and reset win streak
    public void addLoss() {
        losses++;
        winStreak = 0;  // Reset win streak after a loss
    }

    // Method to calculate win/loss ratio
    public double calculateWinLossRatio() {
        double ratio = (losses == 0) ? wins : (double) wins / losses;
        return Math.round(ratio * 100.0) / 100.0;  // Round to 2 decimal places
    }

    // Display score details
    public void displayScore() {
        System.out.println("\n--- Score Summary ---");
        System.out.println("Total Rounds Played: " + totalRounds);
        System.out.println("Total Wins: " + wins);
        System.out.println("Total Ties: " + ties);
        System.out.println("Total Losses: " + losses);
        System.out.println("Current Win Streak: " + winStreak);
        System.out.println("Total Blackjacks: " + blackjacks);
        System.out.println("Win/Loss Ratio: " + calculateWinLossRatio());
        System.out.println("-----------------------");
    }
}
