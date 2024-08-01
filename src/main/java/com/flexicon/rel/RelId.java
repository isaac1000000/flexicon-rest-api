package com.flexicon.rel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import com.flexicon.word.Word;

/**
 * The compound key used to index {@link Rel} instances.
 * Comprised of the base {@link Word}'s id and the target {@link Word}'s id.
 *
 * @author isaac1000000
 * @see Rel
 */
@Embeddable
public class RelId {

    /**
     * The id of the base {@link Word} of the relation.
     */
    @Column(name="baseId", insertable=false, updatable=false)
    private Long baseId;

    /**
     * The id of the target {@link Word} of the relation.
     */
    @Column(name="targetId", insertable=false, updatable=false)
    private Long targetId;

    /**
     * Tests the current instance and <code>o</code> for equality.
     *
     * @param o the object to compare the current instance to
     * @return true if the instance and <code>o</code> are equal
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RelId)) {
            return false;
        }
        RelId comp = (RelId) o;

        return this.baseId.equals(comp.getBaseId()) && this.targetId.equals(comp.getTargetId());
    }

    /**
     * A general hash function for a {@link RelId} instance.
     *
     * @return a hashed key
     */
    @Override
    public int hashCode() {
        Long x = baseId;
        Long y = targetId;

        return (int) (x < y ? y * y + x : x * x + y);
    }

    public Long getBaseId() {
        return baseId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public RelId() { }

    public RelId(Long baseId, Long targetId) {
        this.baseId = baseId;
        this.targetId = targetId;
    }
}
