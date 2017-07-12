package com.example.glenyoung.blackjackandroid;

/**
 * Created by glenyoung on 11/07/2017.
 */

public class Participant {
    protected String name;
    protected Hand hand;

    public Participant(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public String getName() {
        return this.name;
    }

    public Hand getHand() {
        return this.hand;
    }

    public int handSize() {
        return this.hand.size();
    }
}
