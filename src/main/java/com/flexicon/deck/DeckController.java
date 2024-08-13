package com.flexicon.deck;

import com.flexicon.word.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * The REST controller for all requests related to decks.
 *
 * @author isaac1000000
 * @see DeckService
 */
@RestController
@RequestMapping("/api/decks")
public class DeckController {

    @Autowired
    private DeckService deckService;

    @Autowired
    private WordRepository wordRepository;

    @PostMapping("/generate")
    @ResponseStatus(HttpStatus.CREATED)
    public Deck generateDeck(@RequestParam(name="type") String type,
                             @RequestParam(name="name", required = false) String name,
                             @RequestParam(name="ext") String ext,
                             @RequestParam(name="base") String base,
                             @RequestParam(name="nst", defaultValue="0") Double minStrength,
                             @RequestParam(name="xst", defaultValue="1") Double maxStrength,
                             @RequestParam(name="nfr", defaultValue="0") Double minFrequency,
                             @RequestParam(name="xfr", defaultValue="1") Double maxFrequency,
                             @RequestParam(name="qty", defaultValue="1") int quantity) {
        Deck deck;

        /* Looks convoluted but just fetches the word and returns the id;
        * throws an exception if the word doesn't exist.
        */
        Long baseId = wordRepository.findByWord(base).orElseThrow(() -> new IllegalArgumentException("Unexpected input for value base: " + base)).getId();

        deck = deckService.generateDeck(type, name, ext, baseId, minStrength, maxStrength, minFrequency, maxFrequency, quantity);

        return deck;
    }
}
