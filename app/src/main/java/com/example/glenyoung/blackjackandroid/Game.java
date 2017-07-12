package com.example.glenyoung.blackjackandroid;

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

    public void dealAndDisplay(Participant participant) {
        dealer.deal(participant);
        int index = participant.handSize() - 1;
        Card card = participant.getHand().getCards().get(index);
        Rank rank = card.getRank();
        Suit suit = card.getSuit();
        viewer.showNewCard(participant, rank, suit);
    }


    public void playersPlay() {
        viewer.lineBreak();
        Scanner scanner = new Scanner(System.in);
        showCards(dealer);
        for (Player player : players) {
            showCards(player);
            if (!checkBlackjack(player)) {
                String answer = hitOrStay(player);
                while (answer.equals("Y") && handValue(player) < 21) {
                    dealAndDisplay(player);
                    if (checkBlackjack(player)) {
                        Card dealerFirstCard = dealer.getHand().getCards().get(0);
                        if (rankValue(dealerFirstCard) < 10) {
                            viewer.blackjack(player);
                        }
                    }
                    if (handValue(player) == 21) {
                        viewer.score(player, handValue(player));
                        break;
                    }
                    if (handValue(player) > 21) {
                        viewer.playerBust(player);
                        player.getHand().getCards().clear();
                        break;
                    }
                    else viewer.score(player, handValue(player));
                    answer = scanner.nextLine().toUpperCase();
                }
            }
            else viewer.declareBlackjack(player);
            viewer.lineBreak();
        }
    }


    public void showCards(Participant participant) {
        viewer.nameTitle(participant);
        for (Card card : participant.getHand().getCards()) {
            Rank rank = card.getRank();
            Suit suit = card.getSuit();
            viewer.showRankAndSuit(rank, suit);
        }
        viewer.score(participant, handValue(participant));
        viewer.lineBreak();

    }

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
//            viewer.allBust();
            return;
        }
        while (handValue(dealer) < 17) {
            dealAndDisplay(dealer);
            if (handValue(dealer) > 21) {
//                viewer.declareDealerBust();
            }
            else {
//                viewer.score(dealer, handValue(dealer));
            }
        }
    }

    public boolean isPlayerBust(Player player) {
        if (handValue(player) > 21) {
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

    public void playerVsDealerBlackjack() {
        for (Player player : players) {
            if (checkBlackjack(player)) {
                viewer.standOff(player);
            }
            else viewer.playerLoses(player);
        }
    }


    public void compareHands() {
        if (checkBlackjack(dealer)) {
            viewer.declareBlackjack(dealer);
            playerVsDealerBlackjack();
            return;
        }
        for (Player player : players) {
            if (checkBlackjack(player)) {
                viewer.blackjack(player);
            }
            if (handValue(dealer) < 22 && (handValue(player) < 22 && player.handSize() != 0)) {
                if (handValue(player) == handValue(dealer)) {
                    viewer.standOff(player);
                }
                else if (handValue(player) > handValue(dealer)) {
                    viewer.playerWins(player);
                }
                else if (handValue(player) < handValue(dealer)) {
                    viewer.playerLoses(player);
                }
            }
            else if (dealerBust() && (handValue(player) < 22 && player.handSize() != 0)) {
                viewer.playerWins(player);
            }
        }
    }

    public void run() {
//        populatePlayers();

        initialDeal();
        playersPlay();
        showCards(dealer);
        dealerFinish();
        compareHands();
    }
}
