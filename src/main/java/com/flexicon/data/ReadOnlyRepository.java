package com.flexicon.data;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * A data repository template for read-only datasets
 *
 * @author isaac1000000
 * @param <T> the return type of search queries
 * @param <ID> the type of the id column
 * @see Repository
 */
@NoRepositoryBean
public interface ReadOnlyRepository<T, ID> extends Repository<T, ID> {

    /**
     * Finds an optional element of type <code>T</code> by id of type <code>ID</code>
     *
     * @param id the id of type <code>ID</code> to search for
     * @return an <code>Optional</code> element of type <code>T</code>
     */
    Optional<T> findById(ID id);

}
