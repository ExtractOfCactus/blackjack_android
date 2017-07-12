package com.example.glenyoung.blackjackandroid;

/**
 * Created by glenyoung on 11/07/2017.
 */

public class Viewer {
    public void addPlayerOrPlay() {
        System.out.println("Enter a new player name to join the game then type 'play' to begin: ");
    }

    public void confirmPlayerAdded(Player player) {
        System.out.println(player.getName() + " has joined the game. Add another player or type 'play' to begin: ");
    }

    public void tableFull(Player player) {
        System.out.println(player.getName() +" has joined the game. The table is now full, so lets begin: ");
    }

    public String nameTitle(Participant participant) {
        return participant.getName() + ":";
    }

    public String showCard(Card card) {
        Rank rank = card.getRank();
        Suit suit = card.getSuit();
        return rank + " of " + suit;
    }

    public void score(Participant player, int handValue) {
        System.out.println(player.getName() + " has " + handValue);
    }

    public void offerCard(Player player) {
        System.out.println(player.getName() + ": Would you like to take a card? (Y/N)");
    }

    public void declareBlackjack(Participant player) {
        System.out.println(player.getName() + " has BlackJack!");
    }

    public String blackjack(Player player) {
        return player.getName() + " wins with BlackJack!";
    }

    public void standOff(Player player) {
        System.out.println(player.getName() + " has a stand off by matching the dealer");
    }

    public void playerWins(Player player) {
        System.out.println(player.getName() + " wins!");
    }

    public void playerLoses(Player player) {
        System.out.println(player.getName() + " loses.");
    }

    public void playerBust(Player player) {
        System.out.println(player.getName() + " is bust and loses!");
    }

    public void showNewCard(Participant participant, Rank rank, Suit suit) {
        System.out.println(participant.getName() + " draws the " + rank + " of " + suit);
    }

    public void declareDealerBust() {
        System.out.println("Dealer has bust!");
    }

    public void allBust() {
        System.out.println("All players have bust!");
    }

    public void lineBreak() {
        System.out.println(" ");
    }
}
