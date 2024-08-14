package com.flexicon.deck;

import com.flexicon.deck.anki.AnkiDeck;
import com.flexicon.rel.RelDTO;
import com.flexicon.rel.RelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * A service layer application for the {@link Deck} class.
 *
 * @author isaac1000000
 * @see DeckRepository
 * @see DeckController
 * @see Deck
 */
@Service
public class DeckService {

    @Autowired
    private RelService relService;

    @Autowired
    private DeckRepository deckRepository;

    /**
     * Populates the given instance of a subclass of {@link Deck}.
     *
     * @param deck the {@link Deck} instance to be overwritten
     * @see RelService#findRelsByBaseIdStrengthAndFrequency(Long, Double, Double, Double, Double, int)
     */
    public void fillDeck(Deck deck, Long baseId, Double minStrength, Double maxStrength,
                                              Double minFrequency, Double maxFrequency, int quantity) {
        List<RelDTO> relList;
        relList = relService.findRelsByBaseIdStrengthAndFrequency(baseId, minStrength, maxStrength, minFrequency, maxFrequency, quantity);

        for (RelDTO rel : relList) {
            deck.addTargetFromRel(rel);
        }

        deckRepository.save(deck);

        deck.export();
    }

    /**
     * Creates a deck, then populates it according to the specified value ranges.
     *
     * @param type the type of deck to create
     * @param name the user-provided name for the deck
     * @param ext the extension of the deck file (there may be multiple per deck type)
     * @param minStrength the minimum acceptable strength value for the relation query
     * @param maxStrength the maximum acceptable strength value for the rel query
     * @param minFrequency the minimum acceptable frequency value for the rel query
     * @param maxFrequency the maximum acceptable frequency value for the rel query
     * @param quantity initial size of the deck
     * @see #fillDeck(Deck, Long, Double, Double, Double, Double, int)
     */
    public Deck generateDeck(String type, String name, String ext, Long baseId, Double minStrength, Double maxStrength,
                             Double minFrequency, Double maxFrequency, int quantity) {
        Deck deck;

        switch (DeckType.valueOf(type.toUpperCase())) {
            case DeckType.ANKI:
                deck = new AnkiDeck(name, Extension.valueOf(ext.toUpperCase()));
                break;
            default:
                throw new IllegalStateException("Unexpected value for type: " + DeckType.valueOf(type));
        }

        deck.setMinStrength(minStrength);
        deck.setMaxStrength(maxStrength);
        deck.setMinFrequency(minFrequency);
        deck.setMaxFrequency(maxFrequency);

        fillDeck(deck, baseId, minStrength, maxStrength, minFrequency, maxFrequency, quantity);
        return deck;
    }

    /**
     * Finds a deck by the given id.
     *
     * @param id the id to search for
     * @return a nullable {@link Deck} instance
     */
    public Optional<Deck> findById(Long id) {
        return deckRepository.findById(id);
    }
}
