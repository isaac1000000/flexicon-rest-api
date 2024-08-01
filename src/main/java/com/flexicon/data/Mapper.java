package com.flexicon.data;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.flexicon.rel.Rel;
import com.flexicon.rel.RelDTO;
import com.flexicon.rel.RelId;
import com.flexicon.rel.RelRepository;
import com.flexicon.data.exception.ReassignmentMappingFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class for mapping to and from DTOs.
 *
 * @author isaac1000000
 */
@Component
public class Mapper {

    @Autowired
    private RelRepository relRepository;

    /**
     * Translates a {@link Rel} instance into a {@link RelDTO} instance.
     *
     * @param rel the rel to transform
     * @return a completed DTO from <code>rel</code>
     */
    public RelDTO toDTO(Rel rel) {
        return new RelDTO(rel);
    }

    /**
     * Translates a {@link RelDTO} instance into a {@link Rel} instance.
     *
     * @param relDTO the DTO to transform
     * @return a {@link Rel} instance with the {@link RelId} of <code>relDTO</code>
     * @throws ReassignmentMappingFailureException when the id of <code>relDTO</code> does not match any rel entry
     */
    public Rel toRel(RelDTO relDTO) throws ReassignmentMappingFailureException {
        RelId id = new RelId(relDTO.getBaseId(), relDTO.getTargetId());
        try {
            return relRepository.findById(id).get();
        }
        catch (NoSuchElementException _) {
            throw new ReassignmentMappingFailureException("RelDTO instance could not be reassigned to a rel entry");
        }
    }
}
