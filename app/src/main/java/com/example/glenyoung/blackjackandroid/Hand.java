package com.example.glenyoung.blackjackandroid;

import java.util.ArrayList;

/**
 * Created by glenyoung on 11/07/2017.
 */

public class Hand {
    private ArrayList<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public int size() {
        return this.cards.size();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }
}
