package com.flexicon.deck.anki;

import com.flexicon.deck.Card;

/**
 * Represents a single Anki card, usually a word and its definition.
 *
 * @author isaac1000000
 */
public class AnkiCard implements Card {

    /**
     * The information on the front of the card, usually the word alone.
     */
    private String front;

    /**
     * The information on the back of the card, usually the word alone.
     */
    private String back;

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public AnkiCard() { }
    public AnkiCard(String front, String back) {
        this.front = front;
        this.back = back;
    }
}
