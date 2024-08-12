package com.flexicon.deck.anki;

import com.flexicon.deck.Deck;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name="decks")
@DiscriminatorValue("anki")
public class AnkiDeck extends Deck<AnkiCard> {

    @Override
    public void export() {

    }
}
