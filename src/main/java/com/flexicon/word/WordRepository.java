package com.flexicon.word;

import com.flexicon.data.ReadOnlyRepository;

import java.util.Optional;

/**
 * A read-only repository for the words table.
 *
 * @author isaac1000000
 */
public interface WordRepository extends ReadOnlyRepository<Word, Long> {

    /**
     * Returns a {@link Word} object from the word as a string.
     *
     * @param word the target word
     * @return an <code>Optional</code> element of type {@link Word}
     */
    public Optional<Word> findByWord(String word);
}
