package com.flexicon.deck;

import org.springframework.data.repository.CrudRepository;

/**
 * A repository for the decks table that stores file locations and information for all created decks.
 *
 * @author isaac1000000
 * @see Deck
 */
public interface DeckRepository extends CrudRepository<Deck, Long> {
}
