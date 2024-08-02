package com.flexicon.rel;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RelRepositoryTest {

    @Autowired
    private RelRepository relRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Rel testRel;
    private final Pageable singleItem = PageRequest.of(0, 1);
    private final Pageable allItems = Pageable.unpaged();


    @Test
    void givenRel_whenGetTargetId_thenReturnTargetId() {
        this.testRel = relRepository.findAll(singleItem).getContent().get(0);
        Long actualTargetId = testRel.getTargetId();
        Long expectedTargetId = testRel.getId().getTargetId();
        assertEquals(actualTargetId, expectedTargetId);
    }

    @Test
    void givenRel_whenGetBaseId_thenReturnBaseId() {
        this.testRel = relRepository.findAll(singleItem).getContent().get(0);
        Long actualBaseId = testRel.getBaseId();
        Long expectedBaseId = testRel.getId().getBaseId();
        assertEquals(actualBaseId, expectedBaseId);
    }

    @Test
    void givenFrequencyRange_whenFindRelsByBaseIdStrengthAndFrequency_thenResultsInExpectedRanges() {
        Page<Rel> queryResults = relRepository.findRelsByBaseIdStrengthAndFrequency(6719722671305337462L, 0.3, 0.7, 0.3, 0.7, allItems);
        Double actualStrength;
        Double actualFrequency;
        for (Rel rel : queryResults.getContent()) {
            actualStrength = rel.getStrength();
            actualFrequency = rel.getTarget().getFrequency();
            assertTrue(actualStrength >= .3 && actualStrength <= .7, "Strength out of range" + rel.getTarget().getWord());
            assertTrue(actualFrequency >= .3 && actualFrequency <= .7, "Frequency out of range" + rel.getTarget().getWord());
        }
    }

    @Test
    void givenBaseId_whenFindStrongestRel_thenReturnRelWithStrength1() {
        Page<Rel> queryResults = relRepository.findRelsByBaseIdStrengthAndFrequency(6719722671305337462L, 0.0, 1.0, 0.0, 1.0, singleItem);
        // Test is only valid if at least one rel exists for the base Id
        if (queryResults.getTotalElements() >= 1) {
            Rel rel = queryResults.getContent().get(1);
            Double actualStrength = rel.getStrength();
            Double expectedStrength = 1.0;
            assertEquals(actualStrength, expectedStrength);
        } else {
            fail("BaseId does not have any valid rels");
        }
    }
}