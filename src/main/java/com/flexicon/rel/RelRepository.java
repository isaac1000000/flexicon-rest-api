package com.flexicon.rel;

import com.flexicon.data.ReadOnlyRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * A repository for the rels table
 *
 * @author isaac1000000
 * @see Rel
 * @see RelId
 * @see ReadOnlyRepository
 */
public interface RelRepository extends ReadOnlyRepository<Rel, RelId>, PagingAndSortingRepository<Rel, RelId> {

}
