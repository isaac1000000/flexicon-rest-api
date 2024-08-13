package com.flexicon.deck;

import com.flexicon.deck.anki.AnkiCard;
import com.flexicon.deck.anki.AnkiDeck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTest {

    @Test
    void givenExtension_whenGetExt_thenReturnUsableString() {
        String expectedExtension = ".txt";
        Deck<AnkiCard> ankiDeck = new AnkiDeck();
        ankiDeck.setExt(Extension.TXT);
        assertEquals(expectedExtension, ankiDeck.getExt());
    }
}
