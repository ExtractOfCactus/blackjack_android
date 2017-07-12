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

    public String turn(Participant participant) {
        return participant.getName() + ", it is your turn.";
    }

    public String showCards(Participant participant) {
        String result = "";
        for (Card card : participant.getHand().getCards()) {
            Rank rank = card.getRank();
            Suit suit = card.getSuit();
             result += rank + " of " + suit + "\n";
        }
        return result;
    }

    public String score(Participant player, int handValue) {
        return player.getName() + " has " + handValue;
    }

    public void offerCard(Player player) {
        System.out.println(player.getName() + ": Would you like to take a card? (Y/N)");
    }

    public String declareBlackjack(Participant player) {
        return (player.getName() + " has BlackJack!");
    }

    public String blackjackWin(Player player) {
        return player.getName() + " wins with BlackJack!";
    }

    public String standOff(Player player) {
        return player.getName() + " has a stand off by matching the dealer";
    }

    public String playerWins(Player player) {
        return player.getName() + " wins!";
    }

    public String playerLoses(Player player) {
        return player.getName() + " loses.";
    }

    public String playerBust(Player player) {
        return player.getName() + " is bust and loses!";
    }

    public String showNewCard(Participant participant, Rank rank, Suit suit) {
       return participant.getName() + " draws the " + rank + " of " + suit;
    }

    public String declareDealerBust() {
        return "Dealer has bust!";
    }

    public String allBust() {
        return "All players have bust!";
    }

    public void lineBreak() {
        System.out.println(" ");
    }
}
