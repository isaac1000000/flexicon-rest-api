package com.flexicon.deck.anki;

import com.flexicon.deck.Card;
import com.flexicon.deck.Extension;
import com.flexicon.rel.RelDTO;

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

    /**
     * Creates and returns a new instance of {@link AnkiCard}
     *
     * @param relDTO the {@link RelDTO} object containing a target word and definition
     * @return a new {@link AnkiCard} instance with information from <code>relDTO</code>
     */
    public static AnkiCard of(RelDTO relDTO) {
        AnkiCard newCard = new AnkiCard();
        newCard.setFront(relDTO.getTarget());
        newCard.setBack(relDTO.getTargetDefinition());
        return newCard;
    }

    public String exportToString(Extension extension) {
        switch (extension) {
            case TXT:
                return this.front + "; " + this.back;
            case APKG:
                // Not yet implemented
                return "";
            default:
                return "";
        }
    }
}
