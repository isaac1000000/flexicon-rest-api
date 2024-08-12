package com.flexicon.rel;

import com.flexicon.data.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * A service layer application for the {@link Rel} class and rel dataset.
 *
 * @author isaac1000000
 */
@Service
public class RelService {

    @Autowired
    private RelRepository relRepository;

    @Autowired
    private Mapper mapper;

    /**
     * The number of total valid entries in the last query, regardless of page size.
     */
    private Long lastResponseSize;

    /**
     * Searches the repository for acceptable relations and returns a list of {@link RelDTO} instances.
     *
     * @param baseId the id of the base word of the relation
     * @param minStrength the minimum acceptable strength value
     * @param maxStrength the maximum acceptable strength value
     * @param minFrequency the minimum acceptable frequency value
     * @param maxFrequency the maximum acceptable frequency value
     * @param index the starting page index of the returned list
     * @param quantity the number of responses to be returned
     * @param sortOn the column to sort responses on
     * @param descending true if the <code>sortOn</code> column should be in descending order
     * @return a list of relations
     */
    public List<RelDTO> findRelsByBaseIdStrengthAndFrequency(Long baseId, Double minStrength, Double maxStrength,
                                                             Double minFrequency, Double maxFrequency,
                                                             int index, int quantity, String sortOn, boolean descending) {

        // Designates number of results, starting index of page and sorting scheme
        Pageable page = PageRequest.of(index, quantity, (descending ? Sort.by(sortOn).descending() : Sort.by(sortOn)));

        Page<Rel> results = relRepository.findRelsByBaseIdStrengthAndFrequency(baseId, minStrength, maxStrength,
                minFrequency, maxFrequency, page);

        lastResponseSize = results.getTotalElements();

        return results.map(mapper::toDTO).getContent();
    }

    /**
     * Behaves like {@link #findRelsByBaseIdStrengthAndFrequency(Long, Double, Double, Double, Double, int, int, String, boolean)}
     * but assumes descending sorting on the "strength" column.
     *
     * @see #findRelsByBaseIdStrengthAndFrequency(Long, Double, Double, Double, Double, int, int, String, boolean)
     */
    public List<RelDTO> findRelsByBaseIdStrengthAndFrequency(Long baseId, Double minStrength, Double maxStrength,
                                                             Double minFrequency, Double maxFrequency,
                                                             int index, int quantity) {
        return findRelsByBaseIdStrengthAndFrequency(baseId, minStrength, maxStrength, minFrequency, maxFrequency, index,
                quantity, "strength", true);
    }

    /**
     * Behaves like {@link #findRelsByBaseIdStrengthAndFrequency(Long, Double, Double, Double, Double, int, int, String, boolean)}
     * but assumes descending sorting on the "strength" column and 1 for the quantity.
     *
     * @see #findRelsByBaseIdStrengthAndFrequency(Long, Double, Double, Double, Double, int, int, String, boolean)
     */
    public List<RelDTO> findRelByBaseIdStrengthAndFrequency(Long baseId, Double minStrength, Double maxStrength,
                                                             Double minFrequency, Double maxFrequency, int index) {
        return findRelsByBaseIdStrengthAndFrequency(baseId, minStrength, maxStrength, minFrequency, maxFrequency, index,
                1, "strength", true);
    }

    /**
     * Finds the strongest relation for the given base word within the target frequency range
     *
     * @param baseId the id of the base word of the relation
     * @param minFrequency the minimum acceptable value for the frequency of the target word
     * @param maxFrequency the maximum acceptable value for the frequency of the target word
     * @return a single {@link Rel} with the highest strength value within the given constraints
     * @see #findRelsByBaseIdStrengthAndFrequency(Long, Double, Double, Double, Double, int, int, String, boolean)
     */
    public Optional<RelDTO> findStrongestRelByBaseIdAndFrequency(Long baseId, Double minFrequency, Double maxFrequency) {
        List<RelDTO> resultAsList = findRelsByBaseIdStrengthAndFrequency(baseId, 0.0, 1.0, minFrequency, maxFrequency, 0, 1, "strength", true);
        return Optional.of(resultAsList.getFirst());
    }

    /**
     * Behaves like {@link #findStrongestRelByBaseIdAndFrequency(Long, Double, Double)} but assumes a target frequency range of 0.0 <= f <= 1.0
     *
     * @see #findStrongestRelByBaseIdAndFrequency(Long, Double, Double)
     */
    public Optional<RelDTO> findStrongestRelByBaseId(Long baseId) {
        return findStrongestRelByBaseIdAndFrequency(baseId, 0.0, 1.0);
    }

    public RelService() { }

    public Long getLastResponseSize() {
        return lastResponseSize;
    }
}
