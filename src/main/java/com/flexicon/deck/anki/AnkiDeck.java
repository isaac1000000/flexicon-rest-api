package com.flexicon.deck.anki;

import com.flexicon.deck.Deck;
import com.flexicon.deck.DeckType;
import com.flexicon.deck.Extension;
import com.flexicon.rel.RelDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * An Anki deck - an extension of {@link Deck} with a card list made of {@link AnkiCard} instances.
 *
 * @author isaac1000000
 * @see Deck
 * @see AnkiCard
 */
@Entity
@Table(name="decks")
@DiscriminatorValue("anki")
public class AnkiDeck extends Deck<AnkiCard> {

    private static final Extension DEFAULT_EXT = Extension.TXT;

    @Transient
    private FileWriter writer;

    @Override
    public void export() {
        this.size = this.getCardList().size();

        System.out.println(this.getId());

        try {
            File deckFile = new File("/flexicon/" + DECK_DIRECTORY, this.getFilepath());
            System.out.println(deckFile.getAbsolutePath());
            if (deckFile.createNewFile()) {
                System.out.println("New file created at path: " + this.getFilepath());
            } else {
                System.out.println("File already existed and was overwritten");
            }

            this.writer = new FileWriter(deckFile);

            writeFileHeader();

            for (AnkiCard card : this.getCardList()) {
                this.writer.write(card.exportToString(this.getExtRaw()) + "\n");
            }

            packageFile();

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeFileHeader() {
        switch (this.getExtRaw()) {
            case TXT:
                // No header necessary
                break;
            case APKG:
                // Will eventually write data necessary for .apkg file
                break;
        }
    }

    private void packageFile() {
        switch (this.getExtRaw()) {
            case TXT:
                // No need to package
                break;
            case APKG:
                // Will eventually package the file into .apkg
                break;
        }
    }

    /**
     * Adds the target word of the {@link RelDTO} as an {@link AnkiCard} instance to <code>cardList</code>
     *
     * @param relDTO the source for the new card
     */
    @Override
    public void addTargetFromRel(RelDTO relDTO) {
        this.addCard(AnkiCard.of(relDTO));
    }

    public AnkiDeck() {
        super(DeckType.ANKI, DEFAULT_EXT);
    }

    public AnkiDeck(Extension ext) {
        super(DeckType.ANKI, ext);
    }

    public AnkiDeck(String name, Extension ext) {
        super(DeckType.ANKI, name, ext);
    }
}
