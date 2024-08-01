package com.flexicon.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * The REST controller for all requests related to words.
 */
@RestController
@RequestMapping("/api/words")
public class WordController {

    @Autowired
    private WordRepository wordRepository;

    /**
     * Finds a word by its hashed id.
     *
     * @param id the target id
     * @return the result as type {@link Word} if it exists
     */
    @GetMapping("/{id}")
    public Optional<Word> getWordWithId(@PathVariable Long id) {
        return wordRepository.findById(id);
    }

    /**
     * Finds a word by its readable string.
     *
     * @param word the target word
     * @return the result as type {@link Word} if it exists
     */
    @GetMapping
    public Optional<Word> getWord(@RequestParam(name="word") String word) {
        return wordRepository.findByWord(word);
    }

    public WordController() { }
}
