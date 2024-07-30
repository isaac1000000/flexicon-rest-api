package com.flexicon.rel;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import org.springframework.data.annotation.Immutable;

import com.flexicon.word.Word;

/**
 * A relation corresponding to a row from the rels dataset.
 * Shows the correspondence between two words.
 *
 * @author isaac1000000
 */
@Entity
@Table(name="rels")
@Immutable
public class Rel {

    /**
     * A compound id consisting of the base {@link Word}'s id and the target {@link Word}'s id\
     * @see RelId
     */
    @EmbeddedId
    private RelId id;

    /**
     * The number of times the words occur in close proximity during article scans
     */
    @Column(name="instances")
    private Long instances;

    /**
     * The strength of the relation relative to the base {@link Word}'s other relationships
     */
    @Column(name="strength")
    private Double strength;

    public RelId getId() {
        return id;
    }

    public Long getInstances() {
        return instances;
    }

    public Double getStrength() {
        return strength;
    }

    public Rel() { }
}
