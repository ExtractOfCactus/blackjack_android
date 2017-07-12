package com.example.glenyoung.blackjackandroid;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by glenyoung on 11/07/2017.
 */

public class Game {
    private ArrayList<Player> players;
    private Player player1;
    private Player player2;
    private Player player3;
    private Dealer dealer;
    private HashMap<Enum, Integer> rankValues;
    private Viewer viewer;

    public Game(Viewer viewer) {
        this.viewer = viewer;
        rankValues = new HashMap<>();
        this.players = new ArrayList<>();
        player1 = new Player("Jim");
        player2 = new Player("Roy");
        player3 = new Player("Alphonso");
        addPlayer(player1);
        addPlayer(player2);
        addPlayer(player3);
        this.dealer = new Dealer("Dealer");
        this.setUpRankValues();
    }

    public void setUpRankValues() {
        rankValues.put(Rank.ACE, 11);
        rankValues.put(Rank.TWO, 2);
        rankValues.put(Rank.THREE, 3);
        rankValues.put(Rank.FOUR, 4);
        rankValues.put(Rank.FIVE, 5);
        rankValues.put(Rank.SIX, 6);
        rankValues.put(Rank.SEVEN, 7);
        rankValues.put(Rank.EIGHT, 8);
        rankValues.put(Rank.NINE, 9);
        rankValues.put(Rank.TEN, 10);
        rankValues.put(Rank.JACK, 10);
        rankValues.put(Rank.QUEEN, 10);
        rankValues.put(Rank.KING, 10);
    }

    public Integer rankValue(Card card) {
        return rankValues.get(card.getRank());
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

//    public void populatePlayers() {
//        int groupSize = 0;
//        Scanner scanner = new Scanner(System.in);
//        viewer.addPlayerOrPlay();
//        String input = scanner.nextLine();
//        while (!input.toLowerCase().equals("play")) {
//            if (input.equals("")) {
//                return;
//            }
//            String name = input.substring(0,1).toUpperCase() + input.substring(1);
//            Player player = new Player(name);
//            addPlayer(player);
//            groupSize += 1;
//            if (groupSize >= 3) {
//                viewer.tableFull(player);
//                break;
//            }
//            viewer.confirmPlayerAdded(player);
//            input = scanner.nextLine();
//        }
//    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public void dealRound() {
        for (Player player : players) {
            this.dealer.deal(player);
        }
    }

    public void initialDeal() {
        dealRound();
        this.dealer.deal(dealer);
        dealRound();
    }

    public int handValue(Participant participant) {
        int total = 0;
        int aceCounter = 0;
        for (Card card : participant.getHand().getCards()) {
            total += rankValue(card);
            if (card.getRank() == Rank.ACE) {
                aceCounter += 1;
            }
            if (total > 21 && aceCounter > 0) {
                total -= 10;
                aceCounter -= 1;
            }
        }
        return total;
    }


    public boolean checkBlackjack(Participant participant) {
        if (participant.handSize() == 2 && handValue(participant) == 21){
            return true;
        }
        return false;
    }

    public String hitOrStay(Player player) {
        Scanner scanner = new Scanner(System.in);
        viewer.offerCard(player);
        String answer = scanner.nextLine().toUpperCase();
        return answer;
    }


//    public void playersPlay() {
//        viewer.lineBreak();
//        Scanner scanner = new Scanner(System.in);
//        showCards(dealer);
//        for (Player player : players) {
//            showCards(player);
//            if (!checkBlackjack(player)) {
//                String answer = hitOrStay(player);
//                while (answer.equals("Y") && handValue(player) < 21) {
//                    dealer.deal(player);
//                    if (checkBlackjack(player)) {
//                        Card dealerFirstCard = dealer.getHand().getCards().get(0);
//                        if (rankValue(dealerFirstCard) < 10) {
//                            viewer.blackjackWin(player);
//                        }
//                    }
//                    if (handValue(player) == 21) {
//                        viewer.score(player, handValue(player));
//                        break;
//                    }
//                    if (handValue(player) > 21) {
//                        viewer.playerBust(player);
//                        player.getHand().getCards().clear();
//                        break;
//                    }
//                    else viewer.score(player, handValue(player));
//                    answer = scanner.nextLine().toUpperCase();
//                }
//            }
////            else viewer.declareBlackjack(player);
//            player.getHand().getCards().clear();
//            viewer.lineBreak();
//        }
//    }


//    public void showCards(Participant participant) {
//        viewer.nameTitle(participant);
//        for (Card card : participant.getHand().getCards()) {
//            viewer.showCard(card);
//        }
//        viewer.score(participant, handValue(participant));
//        viewer.lineBreak();
//
//    }

    public boolean noPlayersRemaining() {
        int cardCounter = 0;
        for (Player player : players) {
            cardCounter += player.handSize();
            if (cardCounter == 0) {
                return true;
            }
        }
        return false;
    }


    public void dealerFinish() {
        if (noPlayersRemaining()) {
            viewer.allBust();
            return;
        }

        while (handValue(dealer) < 17) {
            dealer.deal(dealer);
            if (handValue(dealer) > 21) {
//                viewer.declareDealerBust();
            }
            else {
//                viewer.score(dealer, handValue(dealer));
            }
        }
    }

    public boolean isPlayerBust(Player player) {
        return (handValue(player) > 21);
    }

    public boolean allBust() {
        int bustCounter = 0;
        for (Player player : players) {
            if (isPlayerBust(player)) {
                bustCounter += 1;
            }
        }
        if (bustCounter == 3) {
            return true;
        }
        return false;
    }


    public boolean dealerBust() {
        if (handValue(dealer) > 21) {
            return true;
        }
        return false;
    }

    public String playerVsDealerBlackjack(Player player) {
        if (checkBlackjack(player)) {
            return viewer.standOff(player);
        }
        return viewer.playerLoses(player);
    }

    public String dealerResult () {
        if (handValue(dealer) == 21 && dealer.handSize() == 2) {
            return viewer.declareBlackjack(dealer);
        }
        else if (handValue(dealer) > 21) {
            return viewer.declareDealerBust();
        }
        else {
            return viewer.score(dealer, handValue(dealer));
        }


    }


    public String compareHands(Player player) {
        if (checkBlackjack(dealer)) {
            return playerVsDealerBlackjack(player);
//            return;
        }
            if (checkBlackjack(player)) {
               return viewer.blackjackWin(player);
            }
            if (handValue(dealer) < 22 && (handValue(player) < 22 && player.handSize() != 0)) {
                if (handValue(player) == handValue(dealer)) {
                    return viewer.standOff(player);
                }
                else if (handValue(player) > handValue(dealer)) {
                    return  viewer.playerWins(player);
                }
                else if (handValue(player) < handValue(dealer)) {
                    return viewer.playerLoses(player);
                }
            }
            else if (dealerBust() && handValue(player) < 22) {
                 return viewer.playerWins(player);
            }
            else if (isPlayerBust(player)) {
                return viewer.playerBust(player);
            }

        return "You've fucked up, Glen.";
    }

//    public void run() {
////        populatePlayers();
//
//        initialDeal();
//        playersPlay();
//        showCards(dealer);
//        dealerFinish();
////        compareHands();
//    }
}
