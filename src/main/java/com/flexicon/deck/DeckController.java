package com.flexicon.deck;

import com.flexicon.word.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.flexicon.deck.Deck.DECK_DIRECTORY;
import com.flexicon.deck.exception.DeckDoesNotExistException;
import com.flexicon.data.exception.FileRetrievalException;


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

    /**
     * Calls the service layer to generate a new deck.
     *
     * @param type the type of deck to generate
     * @param name an optional name for clarity
     * @param ext the extension the created file should have
     * @param base the base word for creating the card set
     * @param minStrength the minimum acceptable strength value for each relation in the card set
     * @param maxStrength the maximum acceptable strength value for each relation in the card set
     * @param minFrequency the minimum acceptable frequency value for each relation in the card set
     * @param maxFrequency the maximum acceptable frequency value for each relation in the card set
     * @param quantity the quantity of cards that should be in the set
     * @return a {@link Deck} object for the created file
     * @see Deck
     * @see DeckService#generateDeck(String, String, String, Long, Double, Double, Double, Double, int)
     */
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

    /**
     * Returns a usable deck file to the user. The deck must be generated first.
     *
     * @param id the deck id of the file to return
     * @return a downloadable file
     * @see #generateDeck(String, String, String, String, Double, Double, Double, Double, int)
     */
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> getDeckById(@PathVariable Long id) {
        Deck deck = deckService.findById(id).orElseThrow(() -> new DeckDoesNotExistException("Deck " + id + " does not exist."));
        Path path = Paths.get(DECK_DIRECTORY + deck.getFilepath());
        Resource resource;

        try {
            resource = new UrlResource(path.toUri());
        } catch (Exception e) {
            throw new FileRetrievalException("File at path " + path.toUri() + "could not be found.");
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + deck.getFilepath() + "\"")
                .body(resource);
    }
}
