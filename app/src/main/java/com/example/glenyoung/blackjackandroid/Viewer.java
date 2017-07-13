package com.example.glenyoung.blackjackandroid;

/**
 * Created by glenyoung on 11/07/2017.
 */

public class Viewer {

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

    public String declareBlackjack(Participant player) {
        return (player.getName() + " has BlackJack!");
    }

    public String blackjackWin(Player player) {
        return player.getName() + " wins with BlackJack!";
    }

    public String standOff(Player player) {
        return player.getName() + " has a stand off!";
    }

    public String playerWins(Player player) {
        return player.getName() + " wins!";
    }

    public String playerLoses(Player player) {
        return player.getName() + " loses.";
    }

    public String playerBust(Player player) {
        return player.getName() + " is bust!";
    }

    public String declareDealerBust() {
        return "Dealer has bust!";
    }

    public String allBust() {
        return "All players have bust!";
    }

}
