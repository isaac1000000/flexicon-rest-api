package com.flexicon.rel;

import com.flexicon.data.ReadOnlyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * A read-only repository for the rels table.
 *
 * @author isaac1000000
 * @see Rel
 * @see RelId
 */
public interface RelRepository extends ReadOnlyRepository<Rel, RelId>, PagingAndSortingRepository<Rel, RelId> {

    /**
     * Finds all rels starting from <code>baseId</code> that are within the passed ranges for strength and frequency.
     *
     * @param baseId the id of the word that all rels must originate from
     * @param strengthMin the minimum acceptable strength value of the rel
     * @param strengthMax the maximum acceptable strength value of the rel
     * @param frequencyMin the minimum acceptable frequency value of the rel
     * @param frequencyMax the maximum acceptable frequency value of the rel
     * @param pageable designates the paging scheme to be used
     * @return a <code>Page</code> item with type {@link Rel} representing a paged sublist of the returned rels
     */
    @Query(value="select r from Rel r" +
            " where :baseId=r.id.baseId" +
            " and r.strength between :strengthMin and :strengthMax" +
            " and r.target.frequency between :frequencyMin and :frequencyMax")
    public Page<Rel> findRelsByBaseIdStrengthAndFrequency(Long baseId, Double strengthMin, Double strengthMax,
                                                         Double frequencyMin, Double frequencyMax, Pageable pageable);

}
