package com.example.glenyoung.blackjackandroid;

import java.util.HashMap;

/**
 * Created by glenyoung on 11/07/2017.
 */

public class Card {
    private Rank rank;
    private Suit suit;
    private HashMap<Enum, Integer> rankValues;


    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        this.rankValues = new HashMap<Enum, Integer>();

    }

    public HashMap<Enum, Integer> getRankValues() {
        return this.rankValues;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return this.rank;
    }
}
